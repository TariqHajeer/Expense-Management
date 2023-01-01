package com.example.expensemanagement.Repository;

import android.app.Application;

import com.example.expensemanagement.Daos.CaringDao;
import com.example.expensemanagement.Database.Database;
import com.example.expensemanagement.Domain.Caring;
import com.example.expensemanagement.Helper.Callback;

public class CaringRepo {
    private CaringDao dao;
    public  CaringRepo(Application application){
        Database db =Database.getDatabase(application);
        dao= db.caringDao();
    }
    public  void  Insert(Caring caring, Callback<Void> callback){
        Database.databaseWriteExecutor.execute(()->{
                dao.Insert(caring);
            callback.invoke(null);
        });
    }
}
