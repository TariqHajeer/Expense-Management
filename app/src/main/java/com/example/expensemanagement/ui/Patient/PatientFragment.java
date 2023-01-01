package com.example.expensemanagement.ui.Patient;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensemanagement.Adapters.CaringTypeListAdapter;
import com.example.expensemanagement.Adapters.PatientAdapter;
import com.example.expensemanagement.CreateOrUpdateCaringType;
import com.example.expensemanagement.CreateOrUpdatePatient;
import com.example.expensemanagement.Domain.CaringType;
import com.example.expensemanagement.Domain.Patient;
import com.example.expensemanagement.Helper.Callback;
import com.example.expensemanagement.ViewModels.CaringTypeViewModel;
import com.example.expensemanagement.ViewModels.PatientViewModel;
import com.example.expensemanagement.databinding.FragmentCaringtypeBinding;
import com.example.expensemanagement.databinding.FragmentPatientBinding;

public class PatientFragment  extends Fragment {
    private FragmentPatientBinding binding;
    private PatientViewModel viewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPatientBinding.inflate(inflater, container, false);
        viewModel= new ViewModelProvider(this).get(PatientViewModel.class);

        View root = binding.getRoot();

        RecyclerView recyclerView = binding.patientRecyclerView;


        final PatientAdapter adapter = new PatientAdapter(new PatientAdapter.PatientDiff());
        adapter.callback= new Callback<Patient>() {
            @Override
            public void invoke(Patient obj) {
                Intent i =new Intent(getActivity(), CreateOrUpdatePatient.class);
                i.putExtra(CreateOrUpdatePatient.Extra_Id,obj.getId());
                i.putExtra(CreateOrUpdatePatient.Extra_Name,obj.getName());
                i.putExtra(CreateOrUpdatePatient.Extra_Is_Stopped,obj.isStopped());
                startActivity(i);
            }
        };
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        viewModel.GetData().observe(this,data->{
            adapter.submitList(data);
        });
        binding.addPatientFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(view.getContext(), CreateOrUpdatePatient.class);
                startActivity(i);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position= viewHolder.getAbsoluteAdapterPosition();
                Patient data= adapter.getItemAt(position);
                viewModel.Delete(data);
            }
        }).attachToRecyclerView(recyclerView);
        return  root;

    }

}
