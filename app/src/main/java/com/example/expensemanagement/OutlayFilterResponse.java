package com.example.expensemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


import java.util.Date;

public class OutlayFilterResponse extends AppCompatActivity {
    public final static String reportType = "OutlayFilterResponse.type";
    public final static String fromDate = "OutlayFilterResponse.fromDate";
    public final static String toDate = "OutlayFilterResponse.toDate";
    public final static String material_id = "OutlayFilterResponse.material_id";
    public final static String owner_id = "OutlayFilterResponse.owner_id";
    private Button date_report_search_btn = findViewById(R.id.date_report_search_btn);
    private Button date_report_view_details_btn = findViewById(R.id.date_report_view_details_btn);
    private Spinner months_spinner = findViewById(R.id.months_spinner);
    private EditText date_report_year_text_view = findViewById(R.id.date_report_year_text_view);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outlay_filter_response);
    }
}