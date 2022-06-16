package com.example.expensemanagement.ui.Reports;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.expensemanagement.databinding.FragmentMaterialReportBinding;

public class MaterialReportFragment extends Fragment {
    private FragmentMaterialReportBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMaterialReportBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }
}
