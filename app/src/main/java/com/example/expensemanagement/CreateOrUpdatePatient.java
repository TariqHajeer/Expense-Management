package com.example.expensemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expensemanagement.Domain.Patient;
import com.example.expensemanagement.ViewModels.PatientViewModel;

public class CreateOrUpdatePatient extends AppCompatActivity {

    private TextView label_Status;
    private EditText name_edit_text;
    private Switch isStoppedSwitch;
    private Button save_btn;
    public  final  static  String key="CreateOrUpdatePatient.";

    public final static String Extra_Id = key+"Id";
    public final static String Extra_Name = key+"Name";
    public final static String Extra_Is_Stopped = key+"isStopped";
    private PatientViewModel viewModel;
    private boolean isUpdate = false;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_or_update_patient);
        label_Status = findViewById(R.id.patient_label_status);
        name_edit_text = findViewById(R.id.patient_name_edit_text);
        isStoppedSwitch= findViewById(R.id.patient_isStopped_edit);
        save_btn=  findViewById(R.id.save_patient_btn);
        label_Status.setText("Create a new Patient");
        viewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Save(Bind());
            }
        });
        Intent i= getIntent();
        if(i.hasExtra(Extra_Id)){
            label_Status.setText("Update Patient");
            isUpdate =true;
            String name = i.getStringExtra(Extra_Name);
            boolean isStopped = i.getBooleanExtra(Extra_Is_Stopped,false);
            id = i.getIntExtra(Extra_Id, 0);
            Patient patient = new Patient(name,isStopped);
            patient.setId(id);
            Bind(patient);
        }
    }
    public  void  Bind(Patient patient){
        name_edit_text.setText(patient.getName());
        isStoppedSwitch.setChecked(patient.isStopped());
    }
    public  Patient Bind(){
        String name = name_edit_text.getText().toString();
        boolean isStopped =isStoppedSwitch.isChecked();
        Patient p =new Patient(name,isStopped);
        if(isUpdate)
            p.setId(id);
        return  p;
    }
    public  void  Save(Patient patient){
     if(isUpdate){
         viewModel.Update(patient, obj -> finish(),obj->new Handler(Looper.getMainLooper()).post(() -> Toast.makeText(CreateOrUpdatePatient.this, "name is exist", Toast.LENGTH_SHORT).show()));
     }else{
         viewModel.Insert(patient, obj -> finish(),obj->new Handler(Looper.getMainLooper()).post(() -> Toast.makeText(CreateOrUpdatePatient.this, "name is exist", Toast.LENGTH_SHORT).show()));
     }
    }
}