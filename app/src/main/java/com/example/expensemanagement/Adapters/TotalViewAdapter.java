package com.example.expensemanagement.Adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensemanagement.DBViews.TotalView;
import com.example.expensemanagement.ViewHolders.TotalViewHolder;
import com.example.expensemanagement.Helper.Callback;

public class TotalViewAdapter extends ListAdapter<TotalView, TotalViewHolder> {
    public Callback<TotalView> callback;

    public TotalViewAdapter(@NonNull DiffUtil.ItemCallback<TotalView> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public TotalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return TotalViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull TotalViewHolder holder, int position) {
        TotalView current = getItem(position);
        holder.bind(current);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callback != null && position != RecyclerView.NO_POSITION) {
                    callback.invoke(current);
                }
            }
        });
    }

    public static class TotalViewDiff extends DiffUtil.ItemCallback<TotalView> {

        @Override
        public boolean areItemsTheSame(@NonNull TotalView oldItem, @NonNull TotalView newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull TotalView oldItem, @NonNull TotalView newItem) {
            return oldItem.getMaterial_id() == newItem.getMaterial_id();
        }
    }
}
