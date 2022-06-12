package com.example.expensemanagement.Adapters;

import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.expensemanagement.Domain.Material;
import com.example.expensemanagement.ViewHolders.MaterialViewHolder;

public class MaterialListAdapter extends ListAdapter<Material, MaterialViewHolder> {
    public MaterialListAdapter(@NonNull DiffUtil.ItemCallback<Material> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public MaterialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return MaterialViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialViewHolder holder, int position) {
        Material current = getItem(position);
        holder.bind(current);
    }

    public static class MaterialDiff extends DiffUtil.ItemCallback<Material> {

        @Override
        public boolean areItemsTheSame(@NonNull Material oldItem, @NonNull Material newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Material oldItem, @NonNull Material newItem) {
            return newItem.getId() == oldItem.getId();
        }
    }
}
