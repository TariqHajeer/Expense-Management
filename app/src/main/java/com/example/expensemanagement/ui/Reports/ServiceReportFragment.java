package com.example.expensemanagement.ui.Reports;

import android.content.Intent;
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
import com.example.expensemanagement.DBViews.TotalView;
import com.example.expensemanagement.Helper.Callback;
import com.example.expensemanagement.OutlayFilterResponse;
import com.example.expensemanagement.ViewModels.OutlayViewModel;
import com.example.expensemanagement.databinding.FragmentServiceReportBinding;

public class ServiceReportFragment extends Fragment {
    private FragmentServiceReportBinding binding;
    private OutlayViewModel outlayViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentServiceReportBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        RecyclerView recyclerView = binding.materialReportRecyclerView;
        outlayViewModel = new ViewModelProvider(this).get(OutlayViewModel.class);
        TotalViewAdapter adapter = new TotalViewAdapter(new TotalViewAdapter.TotalViewDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        outlayViewModel.getTotal(true).observe(this, totalViews -> {
            adapter.submitList(totalViews);
            adapter.notifyDataSetChanged();
        });
        adapter.callback = new Callback<TotalView>() {
            @Override
            public void invoke(TotalView obj) {
                Intent i = new Intent(getActivity(), OutlayFilterResponse.class);
                i.putExtra(OutlayFilterResponse.material_id, obj.getMaterial_id());
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
