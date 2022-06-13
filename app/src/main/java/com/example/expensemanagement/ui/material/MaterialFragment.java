package com.example.expensemanagement.ui.material;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.expensemanagement.ViewModels.MaterialViewModel;
import com.example.expensemanagement.databinding.FragmentGalleryBinding;

public class MaterialFragment extends Fragment {
    private FragmentGalleryBinding binding;
    private MaterialViewModel materialViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
       // materialViewModel = new ViewModelProvider(this).get(MaterialViewModel.class);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
