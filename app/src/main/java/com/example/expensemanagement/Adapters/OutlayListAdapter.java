package com.example.expensemanagement.Adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensemanagement.DBViews.FullOutlay;
import com.example.expensemanagement.Domain.Outlay;
import com.example.expensemanagement.ViewHolders.OutlayViewHolder;

public class OutlayListAdapter extends ListAdapter<FullOutlay, OutlayViewHolder> {
    public OnOutlayClickListener onOutlayClickListener;

    public OutlayListAdapter(@NonNull DiffUtil.ItemCallback<FullOutlay> diffCallback) {
        super(diffCallback);
    }

    public interface OnOutlayClickListener {
        void onClick(int outlayId);
    }

    @NonNull
    @Override
    public OutlayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return OutlayViewHolder.create(parent);
    }

    public  int getId(int position){
        return getItem(position).getId();
    }

    @Override
    public void onBindViewHolder(@NonNull OutlayViewHolder holder, int position) {
        FullOutlay current = getItem(position);
        holder.bind(current);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onOutlayClickListener != null && position != RecyclerView.NO_POSITION) {
                    onOutlayClickListener.onClick(current.getId());
                }
            }
        });
    }

    public static class OutlayDiff extends DiffUtil.ItemCallback<FullOutlay> {
        @Override
        public boolean areItemsTheSame(@NonNull FullOutlay oldItem, @NonNull FullOutlay newItem) {
            return newItem == oldItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull FullOutlay oldItem, @NonNull FullOutlay newItem) {
            return oldItem.getId() == newItem.getId();
        }
    }
}
