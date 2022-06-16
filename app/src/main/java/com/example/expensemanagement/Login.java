package com.example.expensemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.expensemanagement.Domain.User;
import com.example.expensemanagement.ViewModels.UserViewModel;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class Login extends AppCompatActivity {
    private UserViewModel userViewModel;
    private EditText userNameTxt;
    private EditText passwordTxt;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userNameTxt = findViewById(R.id.editTextTexUserName);
        passwordTxt = findViewById(R.id.editTextTextPassword);
        try {
            users = userViewModel.getUser();
        } catch (InterruptedException | ExecutionException ex) {
            try {
                throw ex;
            } catch (ExecutionException e) {
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void Login(View view) {
        String userName = userNameTxt.getText().toString();
        String password = passwordTxt.getText().toString();
        User u = users.get(0);
        String originalUsername = u.getUserName();
        String originalPassword = u.getPassword();
        if (originalPassword.compareTo(password) == 0 && originalUsername.compareTo(userName) == 0) {
            Intent i = new Intent(this,DrawerActivity.class);
            startActivity(i);
            //go to login
        } else {
            ViewCompat.setBackgroundTintList(userNameTxt, ColorStateList.valueOf(Color.RED));
            ViewCompat.setBackgroundTintList(passwordTxt, ColorStateList.valueOf(Color.RED));
            Toast.makeText(this, "Password or username not correct", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "password hint is " + u.getHint(), Toast.LENGTH_SHORT).show();
        }

    }
}