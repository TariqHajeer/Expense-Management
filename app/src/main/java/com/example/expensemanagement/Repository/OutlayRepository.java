package com.example.expensemanagement.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.expensemanagement.DBViews.FullOutlay;
import com.example.expensemanagement.Daos.OutlayDao;
import com.example.expensemanagement.Database.ExpenseManagementDatabase;
import com.example.expensemanagement.Domain.Outlay;
import com.example.expensemanagement.Helper.Callback;


import java.util.List;

public class OutlayRepository {
    private OutlayDao outlayDao;
    private LiveData<List<FullOutlay>> outlays;

    public OutlayRepository(Application application) {
        ExpenseManagementDatabase db = ExpenseManagementDatabase.getDatabase(application);
        outlayDao = db.outlayDao();
        outlays = outlayDao.getAll();
    }

    public LiveData<List<FullOutlay>> getAll() {
        return outlays;
    }

    public void insert(Outlay outlay) {
        ExpenseManagementDatabase.databaseWriteExecutor.execute(() -> {
            outlayDao.insert(outlay);
        });
    }
    public LiveData<Outlay> getById(int id) {
        return outlayDao.getById(id);
    }

    public void update(Outlay outlay) {
        ExpenseManagementDatabase.databaseWriteExecutor.execute(()->{
            outlayDao.update(outlay);
        });

    }

    public void delete(Outlay outlay) {
        ExpenseManagementDatabase.databaseWriteExecutor.execute(()->{
            outlayDao.delete(outlay);
        });

    }
}