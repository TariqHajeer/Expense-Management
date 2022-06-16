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
    private Spinner material_spinner;
    private MaterialViewModel materialViewModel;
    private OutlayViewModel outlayViewModel;
    private TextView material_report_sum_label;
    private TextView material_report_sum_text_view;
    private Button date_report_view_details_btn;
    private int material_id;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMaterialReportBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        material_spinner = binding.reportMaterialSpinner;
        material_report_sum_label = binding.materialReportSumLabel;
        material_report_sum_text_view = binding.materialReportSumTextView;
        date_report_view_details_btn = binding.dateReportViewDetailsBtn;
        materialViewModel = new ViewModelProvider(this).get(MaterialViewModel.class);
        outlayViewModel = new ViewModelProvider(this).get(OutlayViewModel.class);
        ArrayAdapter<Material> materialArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item);
        materialArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        material_spinner.setAdapter(materialArrayAdapter);

        materialViewModel.getMaterials().observe(this, materials -> {
            materialArrayAdapter.addAll(materials);
            materialArrayAdapter.notifyDataSetChanged();

        });
        material_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Material material = (Material) material_spinner.getSelectedItem();
                material_id = material.getId();
                setPrice(material);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        date_report_view_details_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), OutlayFilterResponse.class);
                i.putExtra(OutlayFilterResponse.material_id, material_id);
                startActivity(i);
            }
        });
        return root;
    }

    public void setPrice(Material material) {
        outlayViewModel.sumByMaterial(material.getId(), new Callback<Double>() {

            @Override
            public void invoke(Double obj) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        material_report_sum_label.setVisibility(View.VISIBLE);
                        material_report_sum_text_view.setText(Double.toString(obj));
                        material_report_sum_text_view.setVisibility(View.VISIBLE);
                        date_report_view_details_btn.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
    }
}
