package com.example.expensemanagement.ui.OutlayOwner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensemanagement.Adapters.OutlayOwnerListAdapter;
import com.example.expensemanagement.CreateOrUpdateOutlayOwnerActivity;
import com.example.expensemanagement.Domain.OutlayOwner;
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
        final OutlayOwnerListAdapter adapter = new OutlayOwnerListAdapter(new OutlayOwnerListAdapter.OutlayOwnerDiff());
        adapter.onOutlayOwnerCLickListener = new OutlayOwnerListAdapter.OnOutlayOwnerCLickListener() {
            @Override
            public void onClick(OutlayOwner outlayOwner) {
                Intent i = new Intent(getActivity(), CreateOrUpdateOutlayOwnerActivity.class);
                i.putExtra(CreateOrUpdateOutlayOwnerActivity.Extra_id, outlayOwner.getId());
                i.putExtra(CreateOrUpdateOutlayOwnerActivity.Extra_name, outlayOwner.getName());
                i.putExtra(CreateOrUpdateOutlayOwnerActivity.Extra_desc, outlayOwner.getDescription());
                startActivity(i);
            }
        };
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        outlayOwnerViewModel.getAll().observe(this, outlayOwners -> {
            adapter.submitList(outlayOwners);
        });
        binding.outlayOwnerFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), CreateOrUpdateOutlayOwnerActivity.class);
                startActivity(i);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
