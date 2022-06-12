package com.example.expensemanagement.Repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.expensemanagement.Daos.MaterialDao;
import com.example.expensemanagement.Database.ExpenseManagementDatabase;
import com.example.expensemanagement.Domain.Material;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MaterialRepository {
    private MaterialDao materialDao;
    private LiveData<List<Material>> materials;

    public MaterialRepository(Application application) {
        ExpenseManagementDatabase db = ExpenseManagementDatabase.getDatabase(application);
        materialDao = db.materialDao();
        materials = materialDao.getAll();
    }

    public LiveData<List<Material>> getAll() {
        return materials;
    }

    public void insert(Material material) {
        ExpenseManagementDatabase.databaseWriteExecutor.execute(() -> {
            materialDao.insert(material);
        });
    }

    public int count(@NonNull String name) {
        final Future<Integer> future = ExpenseManagementDatabase.databaseWriteExecutor.submit(new MyInfoCallable(name, materialDao));
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException ex) {
            return -1;
        }
    }

    public int count(@NonNull String name, @NonNull int id) {
        return materialDao.exist(name, id);
    }

    private static class MyInfoCallable implements Callable<Integer> {
        String name;
        MaterialDao materialDao;

        public MyInfoCallable(String name, MaterialDao materialDao) {
            this.name = name;
            this.materialDao = materialDao;
        }

        @Override
        public Integer call() throws Exception {
            return this.materialDao.exist(name);
        }
    }
}
