package com.example.expensemanagement.Repository;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.expensemanagement.Daos.UserDao;
import com.example.expensemanagement.Database.ExpenseManagementDatabase;
import com.example.expensemanagement.Domain.User;

public class UserRepository {
    private UserDao userDao;
    private boolean userExist = false;

    public UserRepository(Application application) {
        ExpenseManagementDatabase db = ExpenseManagementDatabase.getDatabase(application);
        userDao = db.userDao();
        int userCount = userDao.checkUserExist();
        if (userCount >= 1)
            userExist = true;
    }

    public LiveData<User> getUser(@NonNull String userName, @NonNull String password) {
        return userDao.getUser(userName, password);
    }
}
