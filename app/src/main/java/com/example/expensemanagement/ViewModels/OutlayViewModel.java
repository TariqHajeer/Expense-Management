package com.example.expensemanagement.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.expensemanagement.DBViews.FullOutlay;
import com.example.expensemanagement.Database.ExpenseManagementDatabase;
import com.example.expensemanagement.Domain.Outlay;
import com.example.expensemanagement.Repository.OutlayRepository;

import java.util.List;

public class OutlayViewModel extends AndroidViewModel {

    OutlayRepository repository;
    LiveData<List<FullOutlay>> outlays;

    public OutlayViewModel(@NonNull Application application) {
        super(application);
        repository = new OutlayRepository(application);
        outlays = repository.getAll();
    }

    public void insert(Outlay outlay) {
        ExpenseManagementDatabase.databaseWriteExecutor.execute(()->{
            repository.insert(outlay);
        });

    }

    public LiveData<List<FullOutlay>> getAll() {
        return outlays;
    }
}
