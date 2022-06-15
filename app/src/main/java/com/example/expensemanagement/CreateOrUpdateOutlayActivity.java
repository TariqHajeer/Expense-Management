package com.example.expensemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.expensemanagement.Adapters.OutlayListAdapter;
import com.example.expensemanagement.Domain.Material;
import com.example.expensemanagement.Domain.Outlay;
import com.example.expensemanagement.Domain.OutlayOwner;
import com.example.expensemanagement.Domain.User;
import com.example.expensemanagement.ViewModels.MaterialViewModel;
import com.example.expensemanagement.ViewModels.OutlayOwnerViewModel;
import com.example.expensemanagement.ViewModels.OutlayViewModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateOrUpdateOutlayActivity extends AppCompatActivity {
    private Spinner owner_spinner;
    private Spinner material_spinner;
    private OutlayOwnerViewModel outlayOwnerViewModel;
    private MaterialViewModel materialViewModel;
    private EditText priceTxt;
    private EditText descTxt;
    private Button save_btn;
    private boolean isUpdate = false;
    private OutlayViewModel outlayViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_or_update_outlay);
        owner_spinner = findViewById(R.id.owner_spinner);
        material_spinner = findViewById(R.id.material_spinner);
        priceTxt = findViewById(R.id.priceTxt);
        descTxt = findViewById(R.id.descTxt);
        save_btn = findViewById(R.id.save_outlay_btn);

        outlayViewModel = ViewModelProviders.of(this).get(OutlayViewModel.class);
        List<OutlayOwner> owners = new ArrayList<>();
        List<Material> materials = new ArrayList<>();

        ArrayAdapter<OutlayOwner> ownerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, owners);
        ownerArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        owner_spinner.setAdapter(ownerArrayAdapter);
        outlayOwnerViewModel = ViewModelProviders.of(this).get(OutlayOwnerViewModel.class);
        outlayOwnerViewModel.getAll().observe(this, lowners -> {
            owners.addAll(lowners);
            ownerArrayAdapter.notifyDataSetChanged();
        });
        materialViewModel = ViewModelProviders.of(this).get(MaterialViewModel.class);
        ArrayAdapter<Material> materialArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, materials);
        materialArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        material_spinner.setAdapter(materialArrayAdapter);
        materialViewModel.getMaterials().observe(this, immaterial -> {
            materials.addAll(immaterial);
            materialArrayAdapter.notifyDataSetChanged();

        });
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (addOrUpdate(bind()))
                    finish();
            }
        });


    }

    public Outlay bind() {
        Material selectedMaterial = (Material) material_spinner.getSelectedItem();
        OutlayOwner outlayOwner = (OutlayOwner) owner_spinner.getSelectedItem();
        double price = Double.parseDouble(priceTxt.getText().toString());
        String des = descTxt.getText().toString();
        Date date = getDate();
        Outlay outlay = new Outlay(selectedMaterial.getId(), outlayOwner.getId(), price, des, date);
        return outlay;
    }

    public Date getDate() {
        return new Date();
    }

    public boolean addOrUpdate(Outlay outlay) {
        if (!validate(outlay))
            return false;
        if (isUpdate)
            return false;
        return add(outlay);
    }

    public boolean add(Outlay outlay) {
        outlayViewModel.insert(outlay);
        return  true;
    }

    public boolean validate(Outlay outlay) {
        if (isUpdate)
            return false;
        return validateNewOutlay(outlay);
    }

    public boolean validateNewOutlay(Outlay outlay) {
        double price = outlay.getPrice();
        if (Double.compare(price, 0) == 0) {
            Toast.makeText(this, "price can't be zero", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}