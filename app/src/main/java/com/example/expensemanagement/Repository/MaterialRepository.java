package com.example.expensemanagement.Repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.expensemanagement.Daos.MaterialDao;
import com.example.expensemanagement.Database.ExpenseManagementDatabase;
import com.example.expensemanagement.Domain.Material;

import java.util.List;

public class MaterialRepository {
    private MaterialDao materialDao;
    private LiveData<List<Material>> materials;

    public MaterialRepository(Application application) {
        ExpenseManagementDatabase db = ExpenseManagementDatabase.getDatabase(application);
        materialDao = db.materialDao();
        materials = materialDao.getAll();
    }

    public LiveData<List<Material>> getAll() { return materials; }

    public void insert(Material material) {
        ExpenseManagementDatabase.databaseWriteExecutor.execute(() -> {
            materialDao.insert(material);
        });
    }
}
