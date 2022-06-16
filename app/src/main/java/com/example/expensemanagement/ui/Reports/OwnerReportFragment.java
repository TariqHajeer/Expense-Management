package com.example.expensemanagement.ui.Reports;

import android.content.Intent;
import android.os.Bundle;
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

import com.example.expensemanagement.Domain.Outlay;
import com.example.expensemanagement.Domain.OutlayOwner;
import com.example.expensemanagement.Helper.Callback;
import com.example.expensemanagement.OutlayFilterResponse;
import com.example.expensemanagement.ViewHolders.OutlayViewHolder;
import com.example.expensemanagement.ViewModels.OutlayOwnerViewModel;
import com.example.expensemanagement.ViewModels.OutlayViewModel;
import com.example.expensemanagement.databinding.FragmentOwnerReportBinding;

public class OwnerReportFragment extends Fragment {
    private FragmentOwnerReportBinding binding;
    private Spinner owner_spinner;
    private OutlayOwnerViewModel outlayOwnerViewModel;
    private OutlayViewModel outlayViewModel;
    private TextView owner_report_sum_label;
    private TextView owner_report_sum_text_view;
    private Button date_report_view_details_btn;

    private int owner_id;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOwnerReportBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        owner_spinner = binding.reportOwnerSpinner;
        outlayOwnerViewModel = new ViewModelProvider(this).get(OutlayOwnerViewModel.class);
        outlayViewModel = new ViewModelProvider(this).get(OutlayViewModel.class);
        owner_report_sum_label = binding.ownerReportSumLabel;
        owner_report_sum_text_view = binding.ownerReportSumTextView;
        date_report_view_details_btn = binding.dateReportViewDetailsBtn;
        ArrayAdapter<OutlayOwner> outlayOwnerArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item);
        outlayOwnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        owner_spinner.setAdapter(outlayOwnerArrayAdapter);
        outlayOwnerViewModel.getAll().observe(this, outlayOwners -> {
            outlayOwnerArrayAdapter.addAll(outlayOwners);
            outlayOwnerArrayAdapter.notifyDataSetChanged();
        });
        owner_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                OutlayOwner owner = (OutlayOwner) owner_spinner.getSelectedItem();
                owner_id = owner.getId();
                setPrice(owner);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        date_report_view_details_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), OutlayFilterResponse.class);
                i.putExtra(OutlayFilterResponse.owner_id, owner_id);
                startActivity(i);
            }
        });
        return root;
    }

    public void setPrice(OutlayOwner outlay) {
        outlayViewModel.sumByOwner(outlay.getId(), new Callback<Double>() {
            @Override
            public void invoke(Double obj) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        owner_report_sum_label.setVisibility(View.VISIBLE);
                        owner_report_sum_text_view.setText(Double.toString(obj));
                        owner_report_sum_text_view.setVisibility(View.VISIBLE);
                        date_report_view_details_btn.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
    }
}
