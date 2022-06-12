package com.example.expensemanagement.ViewHolders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensemanagement.Domain.Material;
import com.example.expensemanagement.R;
import com.example.expensemanagement.ViewModels.MaterialViewModel;

public class MaterialViewHolder extends RecyclerView.ViewHolder {
//    private final TextView name;
//    private final TextView description;

    public MaterialViewHolder(@NonNull View itemView) {
        super(itemView);
//        name = itemView.findViewById(R.id.nameTextView);
//        description = itemView.findViewById(R.id.descriptionTextVew);
    }

    public void bind(Material material) {
//        name.setText(material.getName());
//        description.setText(material.getDescription());
    }

    public static MaterialViewHolder create(ViewGroup parent) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.recyclerview_item, parent, false);
//        return new MaterialViewHolder(view);
        return  null;
    }
}
