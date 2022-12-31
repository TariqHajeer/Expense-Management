package com.example.expensemanagement.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.expensemanagement.Daos.CaringTypeDao;
import com.example.expensemanagement.Database.Database;
import com.example.expensemanagement.Database.ExpenseManagementDatabase;
import com.example.expensemanagement.Domain.CaringType;

import java.util.List;

public class CaringTypeRepo {
    private CaringTypeDao dao;
    public   CaringTypeRepo(Application application){
        Database db= Database.getDatabase(application);
        dao= db.CaringTypeDao();

    }
    public LiveData<List<CaringType>> GetAll(){return  dao.GetAll();}
    public  void  Insert(CaringType caringType){
        Database.databaseWriteExecutor.execute(()->{
            dao.Insert(caringType);
        });
    }
    public void Delete(CaringType caringType) {
        ExpenseManagementDatabase.databaseWriteExecutor.execute(() -> {
            dao.Delete(caringType);
        });
    }
    public  void  Update(CaringType caringType){
        ExpenseManagementDatabase.databaseWriteExecutor.execute(() -> {
            dao.Update(caringType);
        });
    }
}
