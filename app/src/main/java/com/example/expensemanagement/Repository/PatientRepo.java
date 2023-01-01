package com.example.expensemanagement.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.expensemanagement.Daos.PatientDao;
import com.example.expensemanagement.Database.Database;
import com.example.expensemanagement.Database.ExpenseManagementDatabase;
import com.example.expensemanagement.Domain.Patient;
import com.example.expensemanagement.Helper.Callback;

import java.util.List;

public class PatientRepo {
    private PatientDao dao;
    public   PatientRepo(Application application){
        Database db= Database.getDatabase(application);
        dao= db.PatientDao();

    }
    public LiveData<List<Patient>> GetPatientByStatus(boolean isStopped){
        return dao.GetPatientByStatus(isStopped);
    }
    public LiveData<List<Patient>> GetAll(){return  dao.GetAll();}
    public  void  Insert(Patient data, Callback<Void> successCallback, Callback<Void> failureCallback){

        Database.databaseWriteExecutor.execute(()->{
            if(dao.Count(data.getName())==0){
                dao.Insert(data);
                successCallback.invoke(null);
            }else{
                failureCallback.invoke(null);
            }
        });
    }
    public void Delete(Patient data) {
        ExpenseManagementDatabase.databaseWriteExecutor.execute(() -> {
            dao.Delete(data);
        });
    }
    public  void  Update(Patient data,Callback<Void> successCallback,Callback<Void> failureCallback){
        ExpenseManagementDatabase.databaseWriteExecutor.execute(() -> {
            if(dao.Count(data.getId(),data.getName())==0){
                dao.Update(data);
                successCallback.invoke(null);
            }else{
                failureCallback.invoke(null);
            }

        });
    }

}
