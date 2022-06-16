package com.example.expensemanagement.ui.Reports;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensemanagement.Adapters.TotalViewAdapter;
import com.example.expensemanagement.ViewModels.OutlayViewModel;
import com.example.expensemanagement.databinding.FragmentMaterialReportBinding;

public class ServiceReportFragment extends Fragment {
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
        outlayViewModel.getTotal(true).observe(this,totalViews -> {
            adapter.submitList(totalViews);
            adapter.notifyDataSetChanged();
        });
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
