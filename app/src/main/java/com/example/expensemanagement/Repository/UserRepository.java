package com.example.expensemanagement.Repository;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.expensemanagement.Daos.UserDao;
import com.example.expensemanagement.Database.ExpenseManagementDatabase;
import com.example.expensemanagement.Domain.User;

import java.util.List;
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

    public List<User> getUser() throws ExecutionException, InterruptedException {
        final Future<List<User>> future = ExpenseManagementDatabase.databaseWriteExecutor.submit(new Callable<List<User>>(){

            @Override
            public List<User> call() throws Exception {
                return userDao.getUser();
            }
        });
        try {
            return future.get();
        }
        catch (InterruptedException | ExecutionException ex) {
            throw ex;
        }
    }

    public void insert(User user) {
        ExpenseManagementDatabase.databaseWriteExecutor.execute(() -> {
            userDao.insert(user);
        });
    }
}
