package com.example.expensemanagement.ViewHolders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensemanagement.DBViews.FullOutlay;
import com.example.expensemanagement.Domain.Outlay;
import com.example.expensemanagement.R;

public class OutlayViewHolder extends RecyclerView.ViewHolder {
    private TextView materialNameTxt;
    private TextView ownerNameTxt;
    private TextView priceTxt;
    private TextView descriptionTxt;
    private TextView dateTxt;

    public OutlayViewHolder(@NonNull View itemView) {

        super(itemView);

        materialNameTxt = itemView.findViewById(R.id.outlay_material_name_text_view);

    }

    public void bind(FullOutlay outlay) {

        materialNameTxt.setText(outlay.getMaterial_name());
    }

    public static OutlayViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.outlay_list_item, parent, false);
        return new OutlayViewHolder(view);

    }

}
