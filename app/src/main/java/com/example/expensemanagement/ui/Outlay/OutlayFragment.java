package com.example.expensemanagement.ui.Outlay;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.expensemanagement.Adapters.OutlayListAdapter;
import com.example.expensemanagement.CreateOrUpdateOutlayActivity;
import com.example.expensemanagement.DBViews.FullOutlay;
import com.example.expensemanagement.Domain.Outlay;
import com.example.expensemanagement.ViewModels.OutlayViewModel;
import com.example.expensemanagement.databinding.FragmentOutlayBinding;
import com.example.expensemanagement.databinding.FragmentOutlayOwnerBinding;

import java.util.List;

public class OutlayFragment extends Fragment {
    private FragmentOutlayBinding binding;
    private OutlayViewModel outlayViewModel;
    private List<FullOutlay> fullOutlays;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentOutlayBinding.inflate(inflater, container, false);
        outlayViewModel = new ViewModelProvider(this).get(OutlayViewModel.class);
        View root = binding.getRoot();
        RecyclerView recyclerView = binding.outlayRecyclerView;
        binding.addOutlayFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), CreateOrUpdateOutlayActivity.class);
                startActivity(i);
            }
        });
        final OutlayListAdapter adapter = new OutlayListAdapter(new OutlayListAdapter.OutlayDiff());
        adapter.onOutlayClickListener = new OutlayListAdapter.OnOutlayClickListener() {
            @Override
            public void onClick(int outlayId) {
                Intent i = new Intent(getActivity(), CreateOrUpdateOutlayActivity.class);
                i.putExtra(CreateOrUpdateOutlayActivity.Extra_Id, outlayId);
                startActivity(i);
            }
        };
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        outlayViewModel.getAll().observe(getActivity(), outlays -> {
            fullOutlays = outlays;
            adapter.submitList(fullOutlays);
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAbsoluteAdapterPosition();

                FullOutlay deleted = adapter.getItemByPosition(position);
                fullOutlays.remove(deleted);
                adapter.notifyDataSetChanged();
                outlayViewModel.deleteById(deleted.getId());

            }
        }).attachToRecyclerView(recyclerView);
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
