package com.example.expensemanagement.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.expensemanagement.Domain.CaringType;
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
    public void Insert(CaringType data){
        repo.Insert(data);
    }
    public  void  Update(CaringType data){
        repo.Update(data);
    }
    public  void  Delete(CaringType data){
        repo.Delete(data);
    }
}
