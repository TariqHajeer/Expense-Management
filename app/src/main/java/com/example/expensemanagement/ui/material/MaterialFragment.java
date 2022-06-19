package com.example.expensemanagement.ui.material;

import android.content.Intent;
import android.hardware.lights.Light;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensemanagement.Adapters.MaterialListAdapter;
import com.example.expensemanagement.CreateOrUpdateMaterialActivity;
import com.example.expensemanagement.Domain.Material;
import com.example.expensemanagement.R;
import com.example.expensemanagement.ViewModels.MaterialViewModel;
import com.example.expensemanagement.databinding.FragmentMaterialBinding;

public class MaterialFragment extends Fragment {
    private FragmentMaterialBinding binding;
    private MaterialViewModel materialViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMaterialBinding.inflate(inflater, container, false);
        materialViewModel = new ViewModelProvider(this).get(MaterialViewModel.class);

        View root = binding.getRoot();

        RecyclerView recyclerView = binding.materialRecyclerView;


        final MaterialListAdapter adapter = new MaterialListAdapter(new MaterialListAdapter.MaterialDiff());
        adapter.onMaterialClickListener = new MaterialListAdapter.OnMaterialClickListener() {
            @Override
            public void onClick(Material material) {
                Intent i = new Intent(getActivity(), CreateOrUpdateMaterialActivity.class);
                i.putExtra(CreateOrUpdateMaterialActivity.Extra_Id, material.getId());
                i.putExtra(CreateOrUpdateMaterialActivity.Extra_Name, material.getName());
                i.putExtra(CreateOrUpdateMaterialActivity.Extra_Desc, material.getDescription());
                i.putExtra(CreateOrUpdateMaterialActivity.Extra_Is_Service, material.getIsService());

                startActivity(i);
            }
        };
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        materialViewModel.getMaterials().observe(this, materials -> {
            adapter.submitList(materials);
        });

        binding.addMaterialFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), CreateOrUpdateMaterialActivity.class);
                startActivity(i);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position= viewHolder.getAbsoluteAdapterPosition();
                Material material= adapter.getItemAt(position);
                materialViewModel.delete(material);
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
