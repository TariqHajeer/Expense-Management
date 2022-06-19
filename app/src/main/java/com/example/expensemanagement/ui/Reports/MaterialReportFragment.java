package com.example.expensemanagement.ui.Reports;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Printer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensemanagement.Adapters.TotalViewAdapter;
import com.example.expensemanagement.DBViews.TotalView;
import com.example.expensemanagement.Domain.Material;
import com.example.expensemanagement.Helper.Callback;
import com.example.expensemanagement.OutlayFilterResponse;
import com.example.expensemanagement.ViewModels.MaterialViewModel;
import com.example.expensemanagement.ViewModels.OutlayViewModel;
import com.example.expensemanagement.databinding.FragmentMaterialReportBinding;

import java.util.ArrayList;
import java.util.List;

public class MaterialReportFragment extends Fragment {
    private FragmentMaterialReportBinding binding;
    private OutlayViewModel outlayViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMaterialReportBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        RecyclerView recyclerView = binding.materialReportRecyclerView;
        outlayViewModel = new ViewModelProvider(this).get(OutlayViewModel.class);
        TotalViewAdapter adapter = new TotalViewAdapter(new TotalViewAdapter.TotalViewDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        outlayViewModel.getTotal(false).observe(this,totalViews -> {
            adapter.submitList(totalViews);
            adapter.notifyDataSetChanged();
        });
        adapter.callback =new Callback<TotalView>() {
            @Override
            public void invoke(TotalView obj) {
                Intent i =new Intent(getActivity(),OutlayFilterResponse.class);
                i.putExtra(OutlayFilterResponse.material_id,obj.getMaterial_id());
                startActivity(i);
            }
        };
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
