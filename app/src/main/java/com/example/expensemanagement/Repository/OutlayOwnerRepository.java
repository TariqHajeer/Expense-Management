package com.example.expensemanagement.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.expensemanagement.Daos.OutlayOwnerDao;
import com.example.expensemanagement.Database.ExpenseManagementDatabase;
import com.example.expensemanagement.Domain.OutlayOwner;

import java.util.List;

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
}
