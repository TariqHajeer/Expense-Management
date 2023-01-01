package com.example.expensemanagement.ViewHolders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.expensemanagement.Domain.Patient;
import com.example.expensemanagement.R;

public class PatientViewHolder extends RecyclerView.ViewHolder {
    private TextView name;
    private Switch isStopped;
    private PatientViewHolder(@NonNull View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.patient_name_text_view);
        isStopped =itemView.findViewById(R.id.patient_is_stopped);
    }
    public void bind(@NonNull Patient data){
        name.setText(data.getName());
        isStopped.setChecked(data.isStopped());
    }
    public static  PatientViewHolder Create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.patient_list_itmes, parent, false);
        return new  PatientViewHolder(view);
    }
}
