package com.example.expensemanagement.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.expensemanagement.Domain.OutlayOwner;
import com.example.expensemanagement.Repository.OutlayOwnerRepository;

import java.util.List;

public class OutlayOwnerViewModel extends AndroidViewModel {
    private OutlayOwnerRepository outlayOwnerRepository;
    private LiveData<List<OutlayOwner>> outlayOwners;

    public OutlayOwnerViewModel(@NonNull Application application) {
        super(application);
        outlayOwnerRepository = new OutlayOwnerRepository(application);
        outlayOwners = outlayOwnerRepository.getAll();
    }

    public LiveData<List<OutlayOwner>> getAll() {
        return outlayOwners;
    }

    public void insert(OutlayOwner outlayOwner) {
        outlayOwnerRepository.insert(outlayOwner);
    }

    public void update(OutlayOwner outlayOwner) {
        outlayOwnerRepository.update(outlayOwner);
    }

}
