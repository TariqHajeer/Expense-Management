package com.example.expensemanagement;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.expensemanagement.Adapters.OutlayListAdapter;
import com.example.expensemanagement.Domain.Material;
import com.example.expensemanagement.Domain.Outlay;
import com.example.expensemanagement.Domain.OutlayOwner;
import com.example.expensemanagement.Domain.User;
import com.example.expensemanagement.ViewModels.MaterialViewModel;
import com.example.expensemanagement.ViewModels.OutlayOwnerViewModel;
import com.example.expensemanagement.ViewModels.OutlayViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    private Calendar calendar;
    public static final String Extra_Id = "CreateOrUpdateOutlayActivity.id";
    private int id = -1;

    public void Toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_or_update_outlay);
        calendar = Calendar.getInstance();
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
        outlayOwnerViewModel.getAll().observe(this, obowners -> {
            owners.addAll(obowners);
            ownerArrayAdapter.notifyDataSetChanged();
        });
        materialViewModel = ViewModelProviders.of(this).get(MaterialViewModel.class);
        ArrayAdapter<Material> materialArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, materials);
        materialArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        material_spinner.setAdapter(materialArrayAdapter);
        materialViewModel.getMaterials().observe(this, obmaterial -> {
            materials.addAll(obmaterial);
            materialArrayAdapter.notifyDataSetChanged();

        });
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (addOrUpdate(bind()))
                        finish();
                } catch (ParseException e) {
                    Toast("please re select date");
                }
            }
        });
        Intent i = getIntent();
        if (i.hasExtra(Extra_Id)) {
            isUpdate = true;
            id = i.getIntExtra(Extra_Id, -1);
            if (id == -1)
                return;
            LiveData<Outlay> outlayLiveData = outlayViewModel.getById(id);
            outlayLiveData.observe(this, outlay -> {
                bind(outlay);
            });

        }


    }


    public void bind(Outlay outlay) {
        priceTxt.setText(Double.toString(outlay.getPrice()));
        descTxt.setText(outlay.getDescription());
        Date date = outlay.getDate();
        calendar.setTime(date);
        materialViewModel.getMaterials().observe(this, observe -> {

            for (Material m : observe) {
                if (m.getId() == outlay.getMaterial_id()) {
                    selectValue(material_spinner, m);
                    break;
                }
            }

        });
        outlayOwnerViewModel.getAll().observe(this, observe -> {
            for (OutlayOwner m : observe) {
                if (m.getId() == outlay.getOutlayOwner_id()) {
                    selectValue(material_spinner, m);
                    break;
                }
            }

        });
    }

    private void selectValue(Spinner spinner, Object value) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(value)) {
                spinner.setSelection(i);
                break;
            }
        }
    }


    public Outlay bind() throws ParseException {
        Material selectedMaterial = (Material) material_spinner.getSelectedItem();
        OutlayOwner outlayOwner = (OutlayOwner) owner_spinner.getSelectedItem();
        double price = Double.parseDouble(priceTxt.getText().toString());
        String des = descTxt.getText().toString();
        Date date = getDate();
        Outlay outlay = new Outlay(selectedMaterial.getId(), outlayOwner.getId(), price, des, date);
        if (isUpdate)
            outlay.setId(id);
        return outlay;
    }

    public Date getDate() throws ParseException {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        month++;
        String _d = year + "-" + month + "-" + day;
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd");
        Date date = dt.parse(_d);
        return date;
    }

    public boolean addOrUpdate(Outlay outlay) {
        if (!validate(outlay))
            return false;
        if (isUpdate) {
            return update(outlay);
        }
        return add(outlay);
    }

    public boolean update(Outlay outlay) {
        outlayViewModel.update(outlay);
        return true;
    }

    public boolean add(Outlay outlay) {
        outlayViewModel.insert(outlay);
        return true;
    }

    public boolean validate(Outlay outlay) {
        double price = outlay.getPrice();
        if (Double.compare(price, 0) == 0) {
            Toast.makeText(this, "price can't be zero", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void showTimePickerDialog(View v) throws ParseException {
        DialogFragment newFragment = new DatePickerFragment(calendar);
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {
        private Calendar calendar;

        public DatePickerFragment(Calendar calendar) {
            this.calendar = calendar;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(requireContext(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            calendar.set(i, i1, i2);
        }
    }

}