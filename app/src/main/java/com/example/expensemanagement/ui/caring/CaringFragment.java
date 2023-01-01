package com.example.expensemanagement.ui.caring;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.expensemanagement.Domain.CaringType;
import com.example.expensemanagement.Domain.Patient;
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
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){
        binding = FragmentCaringBinding.inflate(inflater, container, false);
        viewModel= new ViewModelProvider(this).get(CaringViewModel.class);
        patientViewModel= new ViewModelProvider(this).get(PatientViewModel.class);
        caringTypeViewModel= new ViewModelProvider(this).get(CaringTypeViewModel.class);
        caring_patient_edit_spinner = binding.caringPatientEditSpinner;
        caring_type_edit_spinner=  binding.caringTypeEditSpinner;
        ArrayAdapter<Patient> patientArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item);
        patientArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        caring_patient_edit_spinner.setAdapter(patientArrayAdapter);

        patientViewModel.GetPatientByStatus(false).observe(this,res->{

            patientArrayAdapter.addAll(res);
            patientArrayAdapter.notifyDataSetChanged();
        });
        ArrayAdapter<CaringType> caringTypeArrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item);
        caringTypeArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        caringTypeViewModel.GetData().observe(this,res->{
            caringTypeArrayAdapter.addAll(res);
            caringTypeArrayAdapter.notifyDataSetChanged();
        });
        View root = binding.getRoot();
        return  root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
