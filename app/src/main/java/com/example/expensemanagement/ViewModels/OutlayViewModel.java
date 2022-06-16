package com.example.expensemanagement.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.expensemanagement.DBViews.FullOutlay;
import com.example.expensemanagement.Domain.Outlay;
import com.example.expensemanagement.Helper.Callback;
import com.example.expensemanagement.Repository.OutlayRepository;

import java.util.Date;
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
        repository.insert(outlay);

    }

    public void update(Outlay outlay) {
        repository.update(outlay);
    }

    public LiveData<Outlay> getById(int id) {
        return repository.getById(id);
    }

    public void delete(Outlay outlay) {
        repository.delete(outlay);
    }

    public LiveData<List<FullOutlay>> getAll() {
        return outlays;
    }

    public void sumDateFilter(Date from, Date to, Callback<Double> callback) {
        repository.sumDateFilter(from, to, callback);
    }

    public void sumByMaterial(int material_id, Callback<Double> callback) {
        repository.sumByMaterial(material_id, callback);
    }

    public void sumByOwner(int owner_id, Callback<Double> callback) {
        repository.sumByOwner(owner_id, callback);
    }
}
