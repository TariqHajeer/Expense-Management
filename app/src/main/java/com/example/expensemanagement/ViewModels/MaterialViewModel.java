package com.example.expensemanagement.ViewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.expensemanagement.Domain.Material;
import com.example.expensemanagement.Repository.MaterialRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MaterialViewModel extends AndroidViewModel {
    private MaterialRepository materialRepository;
    private final LiveData<List<Material>> materials;

    public MaterialViewModel(@NonNull Application application) {
        super(application);
        materialRepository = new MaterialRepository(application);
        materials = materialRepository.getAll();
    }

    public LiveData<List<Material>> getMaterials() {
        return materials;
    }

    public void inset(Material material) {
        materialRepository.insert(material);
    }

    public void delete(Material material) {
        materialRepository.delete(material);
    }

    public void update(Material material) {
        materialRepository.update(material);
    }

    public int count(@NonNull String name) throws ExecutionException, InterruptedException {
        try {
            return materialRepository.count(name);
        } catch (InterruptedException | ExecutionException ex) {
            throw ex;
        }
    }
}
