package com.example.expensemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expensemanagement.Domain.OutlayOwner;
import com.example.expensemanagement.ViewModels.OutlayOwnerViewModel;

import java.util.concurrent.ExecutionException;

public class CreateOrUpdateOutlayOwner extends AppCompatActivity {
    private TextView outlayOwner_label_Status;
    private EditText outlayOwner_name_edit_text;
    private EditText outlayOwner_description_edit_text;
    private Button save_outlayOwner_btn;
    private boolean isUpdate = false;
    public final static String Extra_id = "CreateOrUpdateOutlayOwner.id";
    public final static String Extra_name = "CreateOrUpdateOutlayOwner.name";
    public final static String Extra_desc = "CreateOrUpdateOutlayOwner.desc";
    private int id = 0;

    private OutlayOwnerViewModel outlayOwnerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_or_update_outlay_owner);
        outlayOwner_description_edit_text = findViewById(R.id.outlay_owner_description_edit_text);
        outlayOwner_name_edit_text = findViewById(R.id.outlay_owner_name_edit_text);
        save_outlayOwner_btn = findViewById(R.id.save_outlay_owner_btn);
        outlayOwner_label_Status = findViewById(R.id.outlay_owner_label_Status);
        outlayOwnerViewModel = ViewModelProviders.of(this).get(OutlayOwnerViewModel.class);
        outlayOwner_label_Status.setText("Create a new outlay owner ");
        save_outlayOwner_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (createOrUpdate(bind()))
                    finish();
            }
        });
        Intent i = getIntent();
        if (i.hasExtra(Extra_id)) {
            isUpdate = true;
            outlayOwner_label_Status.setText("Update outlay owner");
            String name = i.getStringExtra(Extra_name);
            String desc = i.getStringExtra(Extra_desc);
            id = i.getIntExtra(Extra_id, 0);
            OutlayOwner outlayOwner = new OutlayOwner(name, desc);
            outlayOwner.setId(id);
            bind(outlayOwner);
        }
    }

    public void bind(OutlayOwner outlayOwner) {
        outlayOwner_name_edit_text.setText(outlayOwner.getName());
        outlayOwner_description_edit_text.setText(outlayOwner.getDescription());
    }

    public OutlayOwner bind() {
        String name = outlayOwner_name_edit_text.getText().toString();
        String desc = outlayOwner_description_edit_text.getText().toString();
        OutlayOwner outlayOwner = new OutlayOwner(name, desc);
        if (isUpdate) {
            outlayOwner.setId(id);
        }
        return outlayOwner;
    }

    public boolean createOrUpdate(OutlayOwner outlayOwner) {
        if (isUpdate)
            return update(outlayOwner);
        return create(outlayOwner);
    }

    public boolean validateNew(OutlayOwner outlayOwner) {
        try {
            int count = outlayOwnerViewModel.count(outlayOwner.getName());
            if (count == 0) {
                Toast.makeText(this, R.string.nameRepeated, Toast.LENGTH_SHORT).show();
            }
            return count == 0;
        } catch (ExecutionException | InterruptedException ex) {
            Toast.makeText(this, R.string.error, Toast.LENGTH_LONG);

        }
        return false;

    }

    public boolean validateOld(OutlayOwner outlayOwner) {
        try {
            int count = outlayOwnerViewModel.count(outlayOwner.getName(), outlayOwner.getId());
            if (count == 0) {
                Toast.makeText(this, R.string.nameRepeated, Toast.LENGTH_SHORT).show();
            }
            return count == 0;
        } catch (ExecutionException | InterruptedException ex) {
            Toast.makeText(this, R.string.error, Toast.LENGTH_LONG);

        }
        return false;
    }

    public boolean validate(OutlayOwner outlayOwner) {
        if (outlayOwner.getName().trim().isEmpty()) {
            Toast.makeText(this, R.string.nameRequired, Toast.LENGTH_SHORT).show();
            ViewCompat.setBackgroundTintList(outlayOwner_name_edit_text, ColorStateList.valueOf(Color.RED));
            return false;
        }
        if (isUpdate)
            return validateOld(outlayOwner);
        return validateNew(outlayOwner);
    }

    public boolean update(OutlayOwner outlayOwner) {
        if (!validate(outlayOwner))
            return false;
        outlayOwnerViewModel.update(outlayOwner);
        return true;

    }

    public boolean create(OutlayOwner outlayOwner) {
        if (!validate(outlayOwner))
            return false;
        outlayOwnerViewModel.insert(outlayOwner);
        return true;
    }
}