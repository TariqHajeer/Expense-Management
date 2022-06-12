package com.example.expensemanagement.ViewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.expensemanagement.Domain.Material;
import com.example.expensemanagement.Repository.MaterialRepository;

import java.util.List;

public class MaterialViewModel extends AndroidViewModel {
    private MaterialRepository materialRepository;
    private final LiveData<List<Material>> materials;

    public MaterialViewModel(@NonNull Application application) {
        super(application);
        Log.i("ViewModel","asdasdasd");
        materialRepository = new MaterialRepository(application);
        materials = materialRepository.getAll();
    }

    public LiveData<List<Material>> getMaterials() {
        Log.i("ViewModel","asdasdasd");
        return materials;
    }

    public void inset(Material material) {
        Log.i("ViewModel","asdasdasd");
        materialRepository.insert(material);
    }
}
