package com.example.expensemanagement.ViewHolders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensemanagement.DBViews.TotalView;
import com.example.expensemanagement.R;

public class TotalViewHolder extends RecyclerView.ViewHolder {
    private TextView name;
    private TextView total;

    public TotalViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.report_list_item_material_name_text_view);
        total = itemView.findViewById(R.id.report_list_item_material_sum_text_view);

    }

    public void bind(TotalView totalView) {
        name.setText(totalView.getMaterial_name());
        total.setText(Double.toString(totalView.getSum()));
    }

    public static TotalViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.materail_with_total, parent, false);
        return new TotalViewHolder(view);
    }
}
