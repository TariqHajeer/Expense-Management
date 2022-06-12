package com.example.expensemanagement.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.expensemanagement.Domain.User;
import com.example.expensemanagement.Repository.UserRepository;

import java.util.concurrent.ExecutionException;

public class UserViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    public boolean userExist = false;

    public UserViewModel(@NonNull Application application) throws ExecutionException, InterruptedException {
        super(application);
        userRepository = new UserRepository(application);
        try {
            userExist = userRepository.userExist();
        } catch (InterruptedException | ExecutionException ex) {
            throw ex;
        }
    }
    public  void insert(User user){
        userRepository.insert(user);
    }

}
