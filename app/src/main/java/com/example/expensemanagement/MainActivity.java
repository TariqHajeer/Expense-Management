package com.example.expensemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.expensemanagement.Adapters.MaterialListAdapter;
import com.example.expensemanagement.ViewModels.MaterialViewModel;
import com.example.expensemanagement.ViewModels.UserViewModel;

public class MainActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private MaterialViewModel materialViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        Intent i;
        if (!userViewModel.userExist) {
            i = new Intent(this, WelcomeActivity.class);

        } else {
            i = new Intent(this, Login.class);
        }
        startActivity(i);

    }
}