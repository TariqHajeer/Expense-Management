package com.example.expensemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.icu.text.CaseMap;
import android.os.Bundle;
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
        if (name == null || name == "") {
            Toast.makeText(this, "Name is Required",Toast.LENGTH_LONG).show();
            return;
        }
        if(password==null||password==""){
            Toast.makeText(this, "Password is Required",Toast.LENGTH_LONG).show();
            return;
        }
        if(password!=repeatPassword){
            Toast.makeText(this, "Password and repeat password  not the same ",Toast.LENGTH_LONG).show();
            repeatPasswordTxt.setBackgroundColor(0xFF00FF00);

            return;
        }
       // User user = new User();
    }

    public void addUser(User user) {

    }
}