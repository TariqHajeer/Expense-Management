package com.example.expensemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
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

import com.example.expensemanagement.Domain.CaringType;
import com.example.expensemanagement.Helper.Callback;
import com.example.expensemanagement.ViewModels.CaringTypeViewModel;

public class CreateOrUpdateCaringType extends AppCompatActivity {
    private TextView label_Status;
    private EditText name_edit_text;
    private EditText description_edit_text;
    private Button save_btn;
    public  final  static  String key="CreateOrUpdateCaringTypeActivity.";

    public final static String Extra_Id = key+"Id";
    public final static String Extra_Name = key+"Name";
    public final static String Extra_Desc = key+"Desc";
    private CaringTypeViewModel viewModel;
    private boolean isUpdate = false;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_or_update_caring_type);
        label_Status = findViewById(R.id.caring_type_label_status);
        name_edit_text = findViewById(R.id.caring_type_name_edit_text);
        description_edit_text= findViewById(R.id.caring_type_description_edit_text);
        save_btn=  findViewById(R.id.save_caring_type_btn);
        label_Status.setText("Create a new Caring type");
        viewModel = ViewModelProviders.of(this).get(CaringTypeViewModel.class);
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Save(Bind());
            }
        });
        Intent i= getIntent();
        if(i.hasExtra(Extra_Id)){
            label_Status.setText("Update Caring type");
            isUpdate =true;
            String name = i.getStringExtra(Extra_Name);
            String desc = i.getStringExtra(Extra_Desc);
            id = i.getIntExtra(Extra_Id, 0);
            CaringType caringType = new CaringType(name,desc);
            caringType.setId(id);
            Bind(caringType);
        }
    }
    public  void  Bind(CaringType caringType){
        description_edit_text.setText(caringType.getDescription());
        name_edit_text.setText(caringType.getName());
    }
    public  CaringType Bind(){
            String name = name_edit_text.getText().toString().trim();
            String description = description_edit_text.getText().toString();
            if(description!=null)
                description = description.trim();
        CaringType caringType =new CaringType(name,description);
        if(isUpdate){
            caringType.setId(id);
        }
        return caringType;
    }
    public  void   Save(CaringType data){
        if(!isUpdate){
             Insert(data);
             return;
        }
         Update(data);
    }
    public  void Update(CaringType data){
        viewModel.Update(data, obj -> finish(),obj->new Handler(Looper.getMainLooper()).post(() -> Toast.makeText(CreateOrUpdateCaringType.this, "name is exist", Toast.LENGTH_SHORT).show()));
    }
    public  void   Insert(CaringType data){
        viewModel.Insert(data, obj -> finish(),obj->new Handler(Looper.getMainLooper()).post(() -> Toast.makeText(CreateOrUpdateCaringType.this, "name is exist", Toast.LENGTH_SHORT).show()));
    }
}