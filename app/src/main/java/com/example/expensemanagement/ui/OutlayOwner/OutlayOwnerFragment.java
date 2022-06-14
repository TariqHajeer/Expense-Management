package com.example.expensemanagement.ui.OutlayOwner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensemanagement.ViewModels.OutlayOwnerViewModel;
import com.example.expensemanagement.databinding.FragmentOutlayOwnerBinding;


public class OutlayOwnerFragment extends Fragment {
    private FragmentOutlayOwnerBinding binding;
    private OutlayOwnerViewModel outlayOwnerViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOutlayOwnerBinding.inflate(inflater, container, false);
        outlayOwnerViewModel = new ViewModelProvider(this).get(OutlayOwnerViewModel.class);
        View root = binding.getRoot();
        RecyclerView recyclerView = binding.outlayOwnerRecyclerView;
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
