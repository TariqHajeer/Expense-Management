package com.example.expensemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expensemanagement.Domain.Material;
import com.example.expensemanagement.ViewModels.MaterialViewModel;

import java.util.concurrent.ExecutionException;

public class CreateOrUpdateMaterialActivity extends AppCompatActivity {
    private TextView material_label_Status;
    private EditText material_name_edit_text;
    private EditText material_description_edit_text;
    private Switch material_service_edit_switch;
    private Button save_material_btn;
    public final static String Extra_Id = "CreateOrUpdateMaterialActivity.Id";
    public final static String Extra_Name = "CreateOrUpdateMaterialActivity.Name";
    public final static String Extra_Desc = "CreateOrUpdateMaterialActivity.Desc";
    public final static String Extra_Is_Service = "CreateOrUpdateMaterialActivity.Is_Service";
    private MaterialViewModel materialViewModel;
    private boolean isUpdate = false;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_or_update_material);
        material_label_Status = findViewById(R.id.material_label_Status);
        material_name_edit_text = findViewById(R.id.material_name_edit_text);
        material_description_edit_text = findViewById(R.id.material_description_edit_text);
        material_service_edit_switch = findViewById(R.id.material_service_edit_switch);
        save_material_btn = findViewById(R.id.save_material_btn);
        material_label_Status.setText("Create a new material");
        materialViewModel = ViewModelProviders.of(this).get(MaterialViewModel.class);
        save_material_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (addOrUpdate(bind()))
                    finish();
            }
        });
        Intent i = getIntent();
        if (i.hasExtra(Extra_Id)) {
            material_label_Status.setText("Update material");
            isUpdate = true;
            String name = i.getStringExtra(Extra_Name);
            String desc = i.getStringExtra(Extra_Desc);
            boolean isService = i.getBooleanExtra(Extra_Is_Service, false);
            id = i.getIntExtra(Extra_Id, 0);
            Material material = new Material(name, desc, isService);
            bind(material);
        }

    }

    public void bind(Material material) {
        material_name_edit_text.setText(material.getName());
        material_description_edit_text.setText(material.getDescription());
        material_service_edit_switch.setChecked(material.getIsService());
    }

    public Material bind() {
        String name = material_name_edit_text.getText().toString();
        String desc = material_description_edit_text.getText().toString();
        boolean service = material_service_edit_switch.isChecked();
        Material material = new Material(name, desc, service);
        if (isUpdate) {
            material.setId(id);
        }
        return material;
    }

    public boolean addOrUpdate(Material material) {

        if (isUpdate) {
            return update(material);
        }
        return add(material);
    }

    public boolean update(Material material) {
        boolean valid = false;

        try {
            valid = validateMaterial(material);
        } catch (Exception ex) {
            Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
        }
        if (!valid)
            return false;
        materialViewModel.update(material);
        return true;
    }

    public boolean add(Material material) {
        boolean valid = false;
        try {
            valid = validateMaterial(material);
        } catch (InterruptedException | ExecutionException ex) {
            Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
        }
        if (valid) {
            materialViewModel.inset(material);
            return true;
        }
        return false;
    }


    public boolean validateMaterial(Material material) throws ExecutionException, InterruptedException {
        if (material.getName().trim().isEmpty()) {
            Toast.makeText(this, R.string.nameRequired, Toast.LENGTH_SHORT).show();
            ViewCompat.setBackgroundTintList(material_name_edit_text, ColorStateList.valueOf(Color.RED));
            return false;
        }
        if (!isUpdate) {
            boolean result = validateNewMaterial(material);
            if (!result) {
                Toast.makeText(this, R.string.nameRepeated, Toast.LENGTH_SHORT).show();
                ViewCompat.setBackgroundTintList(material_name_edit_text, ColorStateList.valueOf(Color.RED));
            }
            return result;
        }
        boolean result = validOldMaterial(material);
        if (!result) {
            Toast.makeText(this, R.string.nameRepeated, Toast.LENGTH_SHORT).show();
            ViewCompat.setBackgroundTintList(material_name_edit_text, ColorStateList.valueOf(Color.RED));
            return  false;
        }
        return true;
    }


    public boolean validOldMaterial(Material material) throws ExecutionException, InterruptedException {
        Integer count = materialViewModel.count(material.getName(), material.getId());
        return count == 0;
    }

    public boolean validateNewMaterial(Material material) throws ExecutionException, InterruptedException {
        Integer count = materialViewModel.count(material.getName());
        return count == 0;
    }
}