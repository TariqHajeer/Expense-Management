package com.example.expensemanagement.Adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensemanagement.Domain.CaringType;
import com.example.expensemanagement.Domain.Patient;
import com.example.expensemanagement.Helper.Callback;
import com.example.expensemanagement.ViewHolders.PatientViewHolder;

public class PatientAdapter extends ListAdapter<Patient, PatientViewHolder> {
    public PatientAdapter(@NonNull DiffUtil.ItemCallback<Patient> diffCallback) {
        super(diffCallback);
    }

    public Callback<Patient> callback;

    @NonNull
    @Override
    public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return PatientViewHolder.Create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientViewHolder holder, int position) {
        Patient patient =getItemAt(position);
        holder.bind(patient);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(callback!=null&&position!= RecyclerView.NO_POSITION){
                    callback.invoke(patient);
                }
            }
        });
    }
    public  Patient getItemAt(int position) {
        return getItem(position);
    }
    public static class PatientDiff extends  DiffUtil.ItemCallback<Patient>{
        @Override
        public boolean areItemsTheSame(@NonNull Patient oldItem, @NonNull Patient newItem) {
            return  oldItem ==newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Patient oldItem, @NonNull Patient newItem) {
            return  oldItem.getId()== newItem.getId();
        }
    }
}
