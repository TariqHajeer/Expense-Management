package com.example.expensemanagement.ui.Patient;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensemanagement.Adapters.CaringTypeListAdapter;
import com.example.expensemanagement.CreateOrUpdateCaringType;
import com.example.expensemanagement.Domain.CaringType;
import com.example.expensemanagement.Helper.Callback;
import com.example.expensemanagement.ViewModels.CaringTypeViewModel;
import com.example.expensemanagement.databinding.FragmentCaringtypeBinding;

public class PatientFragment  extends Fragment {
    private FragmentCaringtypeBinding binding;
    private CaringTypeViewModel viewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCaringtypeBinding.inflate(inflater, container, false);
        viewModel= new ViewModelProvider(this).get(CaringTypeViewModel.class);

        View root = binding.getRoot();

        RecyclerView recyclerView = binding.caringTypeRecyclerView;


        final CaringTypeListAdapter adapter = new CaringTypeListAdapter(new CaringTypeListAdapter.CaringTypeDiff());
        adapter.caringTypeOnClickListener= new Callback<CaringType>() {
            @Override
            public void invoke(CaringType obj) {
                Intent i =new Intent(getActivity(), CreateOrUpdateCaringType.class);
                i.putExtra(CreateOrUpdateCaringType.Extra_Id,obj.getId());
                i.putExtra(CreateOrUpdateCaringType.Extra_Name,obj.getName());
                i.putExtra(CreateOrUpdateCaringType.Extra_Desc,obj.getDescription());
                startActivity(i);
            }
        };
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        viewModel.GetData().observe(this,data->{
            adapter.submitList(data);
        });
        binding.addCaringtypeFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(view.getContext(), CreateOrUpdateCaringType.class);
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
                CaringType caringType= adapter.getItemAt(position);
                viewModel.Delete(caringType);
            }
        }).attachToRecyclerView(recyclerView);
        return  root;

    }

}
