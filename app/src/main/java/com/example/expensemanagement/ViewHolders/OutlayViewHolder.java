package com.example.expensemanagement.ViewHolders;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OutlayViewHolder extends RecyclerView.ViewHolder {
    private TextView materialNameTxt;
    private TextView ownerNameTxt;
    private TextView priceTxt;
    private TextView descriptionTxt;
    private TextView dateTxt;

    public OutlayViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}
