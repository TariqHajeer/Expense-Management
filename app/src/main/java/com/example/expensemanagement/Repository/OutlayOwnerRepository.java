package com.example.expensemanagement.Repository;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.expensemanagement.Daos.OutlayOwnerDao;
import com.example.expensemanagement.Database.ExpenseManagementDatabase;
import com.example.expensemanagement.Domain.OutlayOwner;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class OutlayOwnerRepository {
    private OutlayOwnerDao outlayOwnerDao;
    private LiveData<List<OutlayOwner>> outlayOwners;

    public OutlayOwnerRepository(Application application) {
        ExpenseManagementDatabase db = ExpenseManagementDatabase.getDatabase(application);
        outlayOwnerDao = db.outlayOwnerDao();
        outlayOwners = outlayOwnerDao.getAll();
    }

    public LiveData<List<OutlayOwner>> getAll() {
        return outlayOwners;
    }

    public void insert(OutlayOwner outlayOwner) {
        ExpenseManagementDatabase.databaseWriteExecutor.execute(() -> {
            outlayOwnerDao.insert(outlayOwner);
        });
    }

    public void update(OutlayOwner outlayOwner) {
        ExpenseManagementDatabase.databaseWriteExecutor.execute(() -> {
            outlayOwnerDao.update(outlayOwner);
        });
    }

    public int count(@NonNull String name) throws ExecutionException, InterruptedException {
        final Future<Integer> future = ExpenseManagementDatabase.databaseWriteExecutor.submit(new Callable<Integer>() {
            @Override
            public Integer call() {
                return outlayOwnerDao.exist(name);

            }
        });
        return future.get();
    }

    public int count(@NonNull String name, @NonNull int id) throws ExecutionException, InterruptedException {
        final Future<Integer> future = ExpenseManagementDatabase.databaseWriteExecutor.submit(new Callable<Integer>() {
            @Override
            public Integer call() {
                return outlayOwnerDao.exist(name, id);
            }
        });
        return future.get();
    }
}
