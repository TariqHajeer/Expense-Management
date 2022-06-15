package com.example.expensemanagement.Adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.expensemanagement.Domain.Outlay;
import com.example.expensemanagement.ViewHolders.OutlayViewHolder;

public class OutlayAdapter extends ListAdapter<Outlay, OutlayViewHolder> {
    public OutlayAdapter(@NonNull DiffUtil.ItemCallback<Outlay> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public OutlayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull OutlayViewHolder holder, int position) {

    }
}
