package com.example.expensemanagement.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.expensemanagement.Domain.Caring;
import com.example.expensemanagement.Helper.Callback;
import com.example.expensemanagement.Repository.CaringRepo;

public class CaringViewModel extends AndroidViewModel {
    private CaringRepo repo;
    public CaringViewModel(@NonNull Application application) {
        super(application);
        repo = new CaringRepo(application);
    }
    public void Insert(Caring caring, Callback<Void> callback){
        repo.Insert(caring,callback);
    }
}
