package com.example.expensemanagement.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.expensemanagement.Domain.Patient;
import com.example.expensemanagement.Helper.Callback;
import com.example.expensemanagement.Repository.PatientRepo;

import java.util.List;

public class PatientViewModel extends AndroidViewModel {
    private PatientRepo repo;
    private final LiveData<List<Patient>> data;

    public PatientViewModel(@NonNull Application application) {
        super(application);
        repo = new PatientRepo(application);
        data = repo.GetAll();
    }
    public LiveData<List<Patient>> GetPatientByStatus(boolean isStopped){
        return repo.GetPatientByStatus(isStopped);
    }

    public LiveData<List<Patient>> GetData() {
        return data;
    }

    public void Insert(Patient data, Callback<Void> successCallback, Callback<Void> failureCallback) {
        repo.Insert(data, successCallback, failureCallback);

    }

    public void Update(Patient data, Callback<Void> successCallback, Callback<Void> failureCallback) {
        repo.Update(data, successCallback, failureCallback);
    }

    public void Delete(Patient data) {
        repo.Delete(data);

    }
}
