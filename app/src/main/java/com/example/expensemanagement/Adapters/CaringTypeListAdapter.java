package com.example.expensemanagement.Adapters;

import android.view.View;
import android.view.ViewGroup;

import com.example.expensemanagement.Domain.Material;
import com.example.expensemanagement.Helper.Callback;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensemanagement.Domain.CaringType;
import com.example.expensemanagement.ViewHolders.CaringTypeViewHolder;

public class CaringTypeListAdapter extends ListAdapter<CaringType, CaringTypeViewHolder> {
    public CaringTypeListAdapter(@NonNull DiffUtil.ItemCallback<CaringType> diffCallback) {
        super(diffCallback);
    }
    public  Callback<CaringType> caringTypeOnClickListener;
    @NonNull
    @Override
    public CaringTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return  CaringTypeViewHolder.Create(parent);
    }
    @Override
    public void onBindViewHolder(@NonNull CaringTypeViewHolder holder, int position) {
        CaringType caringType = getItem(position);
        holder.bind(caringType);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(caringTypeOnClickListener!=null&&position!= RecyclerView.NO_POSITION){
                    caringTypeOnClickListener.invoke(caringType);
                }
            }
        });
    }
    public CaringType getItemAt(int position) {
        return getItem(position);
    }
    public static class CaringTypeDiff extends  DiffUtil.ItemCallback<CaringType>{

        @Override
        public boolean areItemsTheSame(@NonNull CaringType oldItem, @NonNull CaringType newItem) {
            return  oldItem==newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull CaringType oldItem, @NonNull CaringType newItem) {
            return  oldItem.getId()==newItem.getId();
        }
    }
}
