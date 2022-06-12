package com.example.expensemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.expensemanagement.Adapters.MaterialListAdapter;
import com.example.expensemanagement.Database.ExpenseManagementDatabase;
import com.example.expensemanagement.ViewModels.MaterialViewModel;

public class MainActivity extends AppCompatActivity {
    private MaterialViewModel materialViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        final MaterialListAdapter adapter = new MaterialListAdapter(new MaterialListAdapter.MaterialDiff());

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        materialViewModel = new ViewModelProvider(this).get(MaterialViewModel.class);
        materialViewModel.getMaterials().observe(this, materials -> {
            adapter.submitList(materials);
        });
        int x = materialViewModel.count("Water bill");
        Toast.makeText(this, Integer.toString(x), Toast.LENGTH_SHORT).show();
    }
}