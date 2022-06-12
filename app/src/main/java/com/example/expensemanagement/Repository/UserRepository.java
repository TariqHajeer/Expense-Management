package com.example.expensemanagement.Repository;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.expensemanagement.Daos.UserDao;
import com.example.expensemanagement.Database.ExpenseManagementDatabase;
import com.example.expensemanagement.Domain.User;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class UserRepository {
    private UserDao userDao;
    private boolean userExist = false;

    public UserRepository(Application application) {
        ExpenseManagementDatabase db = ExpenseManagementDatabase.getDatabase(application);
        userDao = db.userDao();

    }

    public boolean userExist() throws ExecutionException, InterruptedException {
        if (userExist)
            return true;

        final Future<Integer> future = ExpenseManagementDatabase.databaseWriteExecutor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return userDao.checkUserExist();
            }
        });
        try {
            int count = future.get();
            if (count > 0)
                userExist = true;
            return userExist;
        } catch (InterruptedException | ExecutionException ex) {
            throw ex;
        }
    }

    public LiveData<User> getUser(@NonNull String userName, @NonNull String password) {
        return userDao.getUser(userName, password);
    }
}
