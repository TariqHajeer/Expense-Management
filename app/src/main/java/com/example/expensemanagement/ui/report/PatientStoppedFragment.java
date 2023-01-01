package com.example.expensemanagement.ui.report;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensemanagement.Adapters.PatientAdapter;
import com.example.expensemanagement.ViewModels.PatientViewModel;
import com.example.expensemanagement.databinding.FragmentPatientStoppedBinding;

public class PatientStoppedFragment  extends Fragment {
    private FragmentPatientStoppedBinding binding;
    private PatientViewModel viewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding =FragmentPatientStoppedBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(PatientViewModel.class);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.stoppedPatientRecyclerView;
        final PatientAdapter adapter = new PatientAdapter(new PatientAdapter.PatientDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        viewModel.GetPatientByStatus(true).observe(this,data->{
            adapter.submitList(data);
        });
        return root;
    }
}
