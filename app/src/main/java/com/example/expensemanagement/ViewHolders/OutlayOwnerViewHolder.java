package com.example.expensemanagement.ViewHolders;

import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensemanagement.Domain.OutlayOwner;
import com.example.expensemanagement.R;

public class OutlayOwnerViewHolder extends RecyclerView.ViewHolder {
    private final TextView name;
    private final TextView description;

    public OutlayOwnerViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.outlay_owner_name_text_view);
        description = itemView.findViewById(R.id.outlay_owner_description_text_view);
    }

    public void bind(OutlayOwner outlayOwner) {
        name.setText(outlayOwner.getName());
        description.setText(outlayOwner.getDescription());
    }

    public static OutlayOwnerViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.outlay_owner_list_item, parent, false);
        return new OutlayOwnerViewHolder(view);

    }
}
