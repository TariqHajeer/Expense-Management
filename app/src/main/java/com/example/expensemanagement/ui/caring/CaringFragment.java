package com.example.expensemanagement.ui.caring;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.expensemanagement.Domain.Caring;
import com.example.expensemanagement.Domain.CaringType;
import com.example.expensemanagement.Domain.Patient;
import com.example.expensemanagement.Helper.Callback;
import com.example.expensemanagement.ViewModels.CaringTypeViewModel;
import com.example.expensemanagement.ViewModels.CaringViewModel;
import com.example.expensemanagement.ViewModels.PatientViewModel;
import com.example.expensemanagement.databinding.FragmentCaringBinding;

public class CaringFragment extends Fragment {
    private FragmentCaringBinding binding;
    private CaringViewModel viewModel;
    private Spinner caring_patient_edit_spinner;
    private Spinner caring_type_edit_spinner;
    private PatientViewModel patientViewModel;
    private CaringTypeViewModel caringTypeViewModel;
    private TimePicker timePicker;
    private EditText desc ;
    private Button save_caring ;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){
        binding = FragmentCaringBinding.inflate(inflater, container, false);
        viewModel= new ViewModelProvider(this).get(CaringViewModel.class);
        patientViewModel= new ViewModelProvider(this).get(PatientViewModel.class);
        caringTypeViewModel= new ViewModelProvider(this).get(CaringTypeViewModel.class);
        caring_patient_edit_spinner = binding.caringPatientEditSpinner;
        caring_type_edit_spinner=  binding.caringTypeEditSpinner;
        desc = binding.caringDescTextEdit;
        save_caring =binding.saveCaring;
        ArrayAdapter<Patient> patientArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item);
        patientArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        timePicker =binding.timePicker;
        caring_patient_edit_spinner.setAdapter(patientArrayAdapter);

        patientViewModel.GetPatientByStatus(false).observe(this,res->{
            patientArrayAdapter.addAll(res);
            patientArrayAdapter.notifyDataSetChanged();
        });
        ArrayAdapter<CaringType> caringTypeArrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item);
        caringTypeArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        caring_type_edit_spinner.setAdapter(caringTypeArrayAdapter);
        caringTypeViewModel.GetData().observe(this,res->{
            caringTypeArrayAdapter.addAll(res);
            caringTypeArrayAdapter.notifyDataSetChanged();
        });
        View root = binding.getRoot();
//        caring_patient_edit_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Patient p = adapterView.
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        })
        save_caring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bind();
            }
        });
        return  root;
    }
    public Caring Bind(){
        Caring caring =new Caring();
        caring.setDescription(desc.getText().toString());
        Patient p= (Patient) caring_patient_edit_spinner.getSelectedItem();
        caring.setPatient_id(p.getId());
        CaringType c = (CaringType) caring_type_edit_spinner.getSelectedItem();
        caring.setCaringType_id(c.getId());
        int h= timePicker.getCurrentHour();
        int m= timePicker.getCurrentMinute();
        String time =h+":"+m;
        caring.setTime(time);
        viewModel.Insert(caring, new Callback<Void>() {
            @Override
            public void invoke(Void obj) {
                new Handler(Looper.getMainLooper()).post(()->{
                    Toast.makeText(getActivity(),"Done",Toast.LENGTH_SHORT).show();
                    desc.setText("");
                });
            }
        });


        return  caring;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
