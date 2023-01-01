package com.example.expensemanagement.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.expensemanagement.Daos.CaringTypeDao;
import com.example.expensemanagement.Database.Database;
import com.example.expensemanagement.Domain.CaringType;
import com.example.expensemanagement.Helper.Callback;

import java.util.List;

public class CaringTypeRepo {
    private CaringTypeDao dao;
    public   CaringTypeRepo(Application application){
        Database db= Database.getDatabase(application);
        dao= db.CaringTypeDao();

    }
    public LiveData<List<CaringType>> GetAll(){return  dao.GetAll();}
    public  void  Insert(CaringType caringType,Callback<Void> successCallback,Callback<Void> failureCallback){

        Database.databaseWriteExecutor.execute(()->{
            if(dao.Count(caringType.getName())==0){
            dao.Insert(caringType);
            successCallback.invoke(null);
            }else{
                failureCallback.invoke(null);
            }
        });
    }
    public void Delete(CaringType caringType) {
        Database.databaseWriteExecutor.execute(() -> {
            dao.Delete(caringType);
        });
    }
    public  void  Update(CaringType caringType,Callback<Void> successCallback,Callback<Void> failureCallback){
        Database.databaseWriteExecutor.execute(() -> {
            if(dao.Count(caringType.getId(),caringType.getName())==0){
            dao.Update(caringType);
            successCallback.invoke(null);
            }else{
                failureCallback.invoke(null);
            }

        });
    }

}
