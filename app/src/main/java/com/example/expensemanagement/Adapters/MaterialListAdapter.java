package com.example.expensemanagement.Adapters;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensemanagement.Domain.Material;
import com.example.expensemanagement.ViewHolders.MaterialViewHolder;

public class MaterialListAdapter extends ListAdapter<Material, MaterialViewHolder> {
    public OnMaterialClickListener onMaterialClickListener;

    public MaterialListAdapter(@NonNull DiffUtil.ItemCallback<Material> diffCallback) {
        super(diffCallback);
    }

    public interface OnMaterialClickListener {
        void onClick(Material material);
    }

    public void OnItemClickListener(OnMaterialClickListener listener) {

        this.onMaterialClickListener = listener;
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onMaterialClickListener != null && position != RecyclerView.NO_POSITION) {
                    onMaterialClickListener.onClick(current);
                }
            }
        });
        CardView c = (CardView) holder.itemView;

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
