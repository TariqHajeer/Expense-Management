package com.example.expensemanagement.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.expensemanagement.DBViews.FullOutlay;
import com.example.expensemanagement.DBViews.TotalView;
import com.example.expensemanagement.Daos.OutlayDao;
import com.example.expensemanagement.Database.ExpenseManagementDatabase;
import com.example.expensemanagement.Domain.Outlay;
import com.example.expensemanagement.Helper.Callback;


import java.util.Date;
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
        return outlayDao.getByIdLive(id);
    }

    public void update(Outlay outlay) {
        ExpenseManagementDatabase.databaseWriteExecutor.execute(() -> {
            outlayDao.update(outlay);
        });

    }

    public void deleteById(int id) {
        ExpenseManagementDatabase.databaseWriteExecutor.execute(() -> {
            outlayDao.delete(outlayDao.getById(id));
        });
    }

    public void delete(Outlay outlay) {
        ExpenseManagementDatabase.databaseWriteExecutor.execute(() -> {
            outlayDao.delete(outlay);
        });
    }

    public LiveData<List<FullOutlay>> getByDateFilter(Date from, Date to) {
        return outlayDao.getDateFilter(from, to);
    }

    public void sumDateFilter(Date from, Date to, Callback<Double> callback) {
        ExpenseManagementDatabase.databaseWriteExecutor.execute(() -> {
            double sum = outlayDao.sumDateFilter(from, to);
            callback.invoke(sum);
        });
    }

    public void sumByMaterial(int material_id, Callback<Double> callback) {
        ExpenseManagementDatabase.databaseWriteExecutor.execute(() -> {
            double sum = outlayDao.sumByMaterial(material_id);
            callback.invoke(sum);
        });
    }

    public void sumByOwner(int owner_id, Callback<Double> callback) {
        ExpenseManagementDatabase.databaseWriteExecutor.execute(() -> {
            callback.invoke(outlayDao.sumByOwner(owner_id));
        });
    }

    public LiveData<List<TotalView>> getTotal(boolean isService) {
        return outlayDao.getTotal(isService);
    }

    public LiveData<List<FullOutlay>> getByMaterial(int material_id) {
        return outlayDao.getByMaterial(material_id);
    }

    public LiveData<List<FullOutlay>> getByOwner(int owner_id) {
        return outlayDao.getByOwner(owner_id);
    }
}
