package com.example.expensemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProviders;

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

    private MaterialViewModel materialViewModel;

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
                if (addMaterial(bind()))
                    finish();
            }
        });

    }

    public Material bind() {
        String name = material_name_edit_text.getText().toString();
        String desc = material_description_edit_text.getText().toString();
        boolean service = material_service_edit_switch.isChecked();
        Material material = new Material(name, desc, service);
        return material;
    }

    public boolean addMaterial(Material material) {
        boolean valid = false;
        try {
            valid = validateMaterial(material);
        } catch (InterruptedException | ExecutionException ex) {
            Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
        }
        if (valid) {
            materialViewModel.inset(material);
            return true;

        } else {
            Toast.makeText(this, R.string.nameRepeated, Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public boolean validateMaterial(Material material) throws ExecutionException, InterruptedException {
        try {
            if (material.getId() == 0) {
                return validateNewMaterial(material);
            }
            return false;
        } catch (InterruptedException | ExecutionException ex) {
            throw ex;
        }
    }

    public boolean validateNewMaterial(Material material) throws ExecutionException, InterruptedException {
        try {
            Integer count = materialViewModel.count(material.getName());
            return count == 0;
        } catch (InterruptedException | ExecutionException ex) {
            throw ex;
        }

    }
}