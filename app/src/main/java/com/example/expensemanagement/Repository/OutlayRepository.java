package com.example.expensemanagement.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.expensemanagement.DBViews.FullOutlay;
import com.example.expensemanagement.Daos.OutlayDao;
import com.example.expensemanagement.Database.ExpenseManagementDatabase;
import com.example.expensemanagement.Domain.Outlay;


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
        outlayDao.insert(outlay);
    }

    public void update(Outlay outlay) {
        outlayDao.update(outlay);
    }

    public void delete(Outlay outlay) {
        outlayDao.delete(outlay);
    }
}
