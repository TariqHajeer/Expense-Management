package com.example.expensemanagement.ui.Reports;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.expensemanagement.Helper.Callback;
import com.example.expensemanagement.OutlayFilterResponse;
import com.example.expensemanagement.R;
import com.example.expensemanagement.ViewModels.OutlayViewModel;
import com.example.expensemanagement.databinding.FragmentDateReportBinding;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateReportFragment extends Fragment {

    private FragmentDateReportBinding binding;
    Spinner month_spinner;
    private OutlayViewModel outlayViewModel;
    private Date from = null;
    private Date to = null;
    private DateFormat dateFormat;
    private Button date_report_view_details_btn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDateReportBinding.inflate(inflater, container, false);
        date_report_view_details_btn = binding.dateReportViewDetailsBtn;
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        outlayViewModel = new ViewModelProvider(this).get(OutlayViewModel.class);
        View root = binding.getRoot();
        month_spinner = binding.monthsSpinner;
        ArrayAdapter<CharSequence> monthAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.months, android.R.layout.simple_spinner_item);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        month_spinner.setAdapter(monthAdapter);
        month_spinner.setHorizontalScrollBarEnabled(true);

        Button date_report_search_btn = binding.dateReportSearchBtn;
        EditText date_report_year_text_view = binding.dateReportYearTextView;
        TextView dateReportSumTextView = binding.dateReportSumTextView;
        TextView date_report_sum_label = binding.dateReportSumLabel;
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        date_report_year_text_view.setText(currentYear);
        date_report_search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _year = date_report_year_text_view.getText().toString();
                if (_year.isEmpty())
                    return;
                int year = Integer.parseInt(_year);
                int selectedMonth = month_spinner.getSelectedItemPosition();

                if (selectedMonth == 0) {
                    String _from = year + "-01" + "-01";
                    String _to = year + "-12" + "-31";
                    try {
                        from = dateFormat.parse(_from);
                        to = dateFormat.parse(_to);
                    } catch (ParseException e) {
                        return;
                    }
                } else {
                    String _date = year + "-" + selectedMonth + "-01";


                    try {
                        from = dateFormat.parse(_date);
                    } catch (ParseException e) {
                        return;
                    }
                    Calendar c = Calendar.getInstance();
                    c.setTime(from);
                    c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
                    to = c.getTime();
                }


                outlayViewModel.sumDateFilter(from, to, new Callback<Double>() {

                    @Override
                    public void invoke(Double obj) {

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dateReportSumTextView.setText(Double.toString(obj));
                                dateReportSumTextView.setVisibility(View.VISIBLE);
                                date_report_sum_label.setVisibility(View.VISIBLE);
                                if (obj.compareTo(0.0) != 0)
                                    date_report_view_details_btn.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                });
            }
        });
        date_report_view_details_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), OutlayFilterResponse.class);
                String _from = dateFormat.format(from);
                String _to = dateFormat.format(to);
                i.putExtra(OutlayFilterResponse.fromDate, _from);
                i.putExtra(OutlayFilterResponse.toDate, _to);
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
