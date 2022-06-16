package com.example.expensemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


import com.example.expensemanagement.Adapters.OutlayListAdapter;
import com.example.expensemanagement.Adapters.OutlayOwnerListAdapter;
import com.example.expensemanagement.DBViews.FullOutlay;
import com.example.expensemanagement.ViewModels.OutlayViewModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OutlayFilterResponse extends AppCompatActivity {
    public final static String fromDate = "OutlayFilterResponse.fromDate";
    public final static String toDate = "OutlayFilterResponse.toDate";
    public final static String material_id = "OutlayFilterResponse.material_id";
    public final static String owner_id = "OutlayFilterResponse.owner_id";
    private OutlayListAdapter adapter;
    private OutlayViewModel outlayViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outlay_filter_response);
        outlayViewModel = ViewModelProviders.of(this).get(OutlayViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.report_outlay_recycler_view);
        adapter = new OutlayListAdapter(new OutlayListAdapter.OutlayDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getOutlayByFilter();
    }

    private void getOutlayByFilter() {
        Intent i = getIntent();
        if (i.hasExtra(material_id)) {
            int m = i.getIntExtra(material_id, -1);
            outlayViewModel.getByMaterial(m).observe(this, fullOutlays -> {
                adapter.submitList(fullOutlays);
                adapter.notifyDataSetChanged();
            });
            return;
        }
        if (i.hasExtra(owner_id)) {
            int m = i.getIntExtra(owner_id, -1);
            outlayViewModel.getByOwner(m).observe(this, fullOutlays -> {
                adapter.submitList(fullOutlays);
                adapter.notifyDataSetChanged();
            });
            return;
        }
        if (i.hasExtra(fromDate) && i.hasExtra(toDate)) {
            String _from = i.getStringExtra(fromDate);
            String _to = i.getStringExtra(toDate);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date from = null;
            Date to = null;
            try {
                from = dateFormat.parse(_from);
                to = dateFormat.parse(_to);
            } catch (Exception ex) {
                return;
            }
            outlayViewModel.getByDateFilter(from, to).observe(this, fullOutlays -> {
                adapter.submitList(fullOutlays);
                adapter.notifyDataSetChanged();
            });
        }

    }
}