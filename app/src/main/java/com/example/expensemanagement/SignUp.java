package com.example.expensemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.telephony.mbms.MbmsErrors;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.expensemanagement.Domain.User;
import com.example.expensemanagement.ViewModels.UserViewModel;

public class SignUp extends AppCompatActivity {
    private UserViewModel userViewModel;
    private EditText userNameTxt;
    private EditText passwordTxt;
    private EditText repeatPasswordTxt;
    private EditText passwordHintTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        setTitle("Sign up");
    }

    public void signUpBtnClick(View view) {
        userNameTxt = findViewById(R.id.editTextTexUserName);
        passwordHintTxt = findViewById(R.id.editTextTextPasswordHint);
        passwordTxt = findViewById(R.id.editTextTextPassword);
        repeatPasswordTxt = findViewById(R.id.editTextTextRepeatPassword);
        String name = userNameTxt.getText().toString();
        String password = passwordTxt.getText().toString();
        String repeatPassword = repeatPasswordTxt.getText().toString();
        String passwordHint = passwordHintTxt.getText().toString();
        String message = "";
        if (name == null || name.isEmpty() || name.trim().isEmpty()) {
            message += "Name is Required\n";

            ViewCompat.setBackgroundTintList(userNameTxt, ColorStateList.valueOf(Color.RED));

        }
        if (password == null || password.isEmpty() || password.trim().isEmpty()) {
            message += "Password is Required\n";
            ViewCompat.setBackgroundTintList(passwordTxt, ColorStateList.valueOf(Color.RED));
            ViewCompat.setBackgroundTintList(repeatPasswordTxt, ColorStateList.valueOf(Color.RED));
        } else if (password.compareTo(repeatPassword) != 0) {
            message += "Password and repeat password  not the same\n";
            ViewCompat.setBackgroundTintList(repeatPasswordTxt, ColorStateList.valueOf(Color.RED));
        }
        if (passwordHint == null || passwordHint.isEmpty() || passwordHint.trim().isEmpty()) {
            message += "Hint password is Required";
            ViewCompat.setBackgroundTintList(passwordHintTxt, ColorStateList.valueOf(Color.RED));
        }
        if (message != "") {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            return;
        }
        User user = new User(name, password, passwordHint);
        addUser(user);
        Intent i = new Intent(this, Login.class);
        finish();
        startActivity(i);
    }

    public void addUser(User user) {
        userViewModel.insert(user);
    }
}