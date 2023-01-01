package com.example.expensemanagement.ViewHolders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensemanagement.Domain.CaringType;
import com.example.expensemanagement.R;

public class CaringTypeViewHolder  extends RecyclerView.ViewHolder{
    private TextView name;
    private  TextView description;
    private CaringTypeViewHolder(@NonNull View itemView) {
        super(itemView);
        name =itemView.findViewById(R.id.caringType_name_text_view);
        description = itemView.findViewById(R.id.caringType_description_text_view);
    }
    public void bind(@NonNull CaringType data){
        name.setText(data.getName());
        description.setText(data.getDescription());

    }
    public static CaringTypeViewHolder Create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.caringtype_list_items, parent, false);
        return new CaringTypeViewHolder(view);
    }
}
