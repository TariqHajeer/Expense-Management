package com.example.expensemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.expensemanagement.ViewModels.UserViewModel;

public class WelcomeActivity extends AppCompatActivity {
    private Button loginBtn;
    private UserViewModel userViewModel;

    private void ToastMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        loginBtn = findViewById(R.id.login_btn);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

    }

    public void signUp(View view) {
        Intent i = new Intent(this, SignUp.class);
        startActivity(i);
    }
}   