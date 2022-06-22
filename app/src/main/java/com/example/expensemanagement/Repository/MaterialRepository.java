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
        //الحصول على نسخة من قاعدة البيانات
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

    public void delete(Material material) {
        ExpenseManagementDatabase.databaseWriteExecutor.execute(() -> {
            materialDao.delete(material);
        });
    }

    public void update(Material material) {
        ExpenseManagementDatabase.databaseWriteExecutor.execute(() -> {
            materialDao.update(material);
        });
    }

    public int count(@NonNull String name) throws ExecutionException, InterruptedException {
        final Future<Integer> future = ExpenseManagementDatabase.databaseWriteExecutor.submit(new Callable<Integer>() {
            @Override
            public Integer call() {
                return materialDao.exist(name);
            }
        });

        return future.get();
    }

    public int count(@NonNull String name, @NonNull int id) throws ExecutionException, InterruptedException {
        final Future<Integer> future = ExpenseManagementDatabase.databaseWriteExecutor.submit(new Callable<Integer>() {
            @Override
            public Integer call() {
                return materialDao.exist(name, id);
            }
        });
        return future.get();

    }

}
