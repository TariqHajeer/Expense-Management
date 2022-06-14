package com.example.expensemanagement.Adapters;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensemanagement.Domain.OutlayOwner;
import com.example.expensemanagement.ViewHolders.OutlayOwnerViewHolder;

public class OutlayOwnerListAdapter extends ListAdapter<OutlayOwner, OutlayOwnerViewHolder> {
    public OnOutlayOwnerCLickListener onOutlayOwnerCLickListener;

    public OutlayOwnerListAdapter(@NonNull DiffUtil.ItemCallback<OutlayOwner> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public OutlayOwnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return OutlayOwnerViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull OutlayOwnerViewHolder holder, int position) {
        OutlayOwner current = getItem(position);
        holder.bind(current);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onOutlayOwnerCLickListener != null && position != RecyclerView.NO_POSITION) {
                    onOutlayOwnerCLickListener.onClick(current);
                }
            }
        });
    }

    public static class OutlayOwnerDiff extends DiffUtil.ItemCallback<OutlayOwner> {

        @Override
        public boolean areItemsTheSame(@NonNull OutlayOwner oldItem, @NonNull OutlayOwner newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull OutlayOwner oldItem, @NonNull OutlayOwner newItem) {
            return oldItem.getId() == newItem.getId();
        }
    }

    public interface OnOutlayOwnerCLickListener {
        void onClick(OutlayOwner outlayOwner);
    }

}
