package com.example.expensemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import com.example.expensemanagement.ViewModels.UserViewModel;

public class MainActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        Intent i;
        if(!userViewModel.userExist){
            //go to create an acount
             i = new Intent(this, WelcomeActivity.class);

        }else{
            //go to login
            i = new Intent(this, Login.class);
        }
        finish();
        startActivity(i);

    }
}