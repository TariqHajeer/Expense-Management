package com.example.expensemanagement.ViewHolders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensemanagement.Domain.Material;
import com.example.expensemanagement.R;

public class MaterialViewHolder extends RecyclerView.ViewHolder {
    private final TextView name;
    private final TextView description;
    private final Switch isService;

    public MaterialViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.material_name_text_view);
        description = itemView.findViewById(R.id.material_description_text_view);
        isService = itemView.findViewById(R.id.material_is_service);
    }

    public void bind(Material material) {
        name.setText(material.getName());
        description.setText(material.getDescription());
        isService.setChecked(material.getIsService());
    }

    public static MaterialViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.material_list_item, parent, false);
        return new MaterialViewHolder(view);
    }
}
