package com.example.expensemanagement.ui.caringType;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensemanagement.Adapters.CaringTypeListAdapter;
import com.example.expensemanagement.Domain.CaringType;
import com.example.expensemanagement.Helper.Callback;
import com.example.expensemanagement.ViewModels.CaringTypeViewModel;
import com.example.expensemanagement.databinding.FragmentCaringtypeBinding;

public class CaringTypeFragment extends Fragment {
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

            }
        };
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        viewModel.GetData().observe(getViewLifecycleOwner(),data->{
            adapter.submitList(data);
        });
        binding.addCaringtypeFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return  null;

    }
}
