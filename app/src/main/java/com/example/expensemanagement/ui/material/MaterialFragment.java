package com.example.expensemanagement.ui.material;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensemanagement.Adapters.MaterialListAdapter;
import com.example.expensemanagement.Domain.User;
import com.example.expensemanagement.ViewModels.MaterialViewModel;
import com.example.expensemanagement.databinding.FragmentGalleryBinding;
import com.example.expensemanagement.databinding.FragmentMaterialBinding;

import java.util.List;

public class MaterialFragment extends Fragment {
    private FragmentMaterialBinding binding;
    private MaterialViewModel materialViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMaterialBinding.inflate(inflater, container, false);
        materialViewModel = new ViewModelProvider(this).get(MaterialViewModel.class);
        View root = binding.getRoot();
        RecyclerView recyclerView = binding.materialRecyclerView;
        final MaterialListAdapter adapter = new MaterialListAdapter(new MaterialListAdapter.MaterialDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        materialViewModel.getMaterials().observe(this, materials -> {
            adapter.submitList(materials);
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
