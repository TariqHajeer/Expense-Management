package com.example.expensemanagement.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.expensemanagement.Domain.CaringType;
import com.example.expensemanagement.Helper.Callback;
import com.example.expensemanagement.Repository.CaringTypeRepo;

import java.util.List;

public class CaringTypeViewModel extends AndroidViewModel {
    private CaringTypeRepo repo;
    private  final LiveData<List<CaringType>> data ;
    public CaringTypeViewModel(@NonNull Application application) {
        super(application);
        repo= new CaringTypeRepo(application);
        data = repo.GetAll();
    }
    public LiveData<List<CaringType>> GetData(){return  data;}
    public void Insert(CaringType data, Callback<Void> successCallback, Callback<Void> failureCallback)
    {
        repo.Insert(data,successCallback,failureCallback);

    }
    public  void  Update(CaringType data,Callback<Void> successCallback,Callback<Void> failureCallback){
        repo.Update(data,successCallback,failureCallback);
    }
    public  void  Delete(CaringType data){
        repo.Delete(data);
    }
}
