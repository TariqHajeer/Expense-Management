package com.example.expensemanagement.Domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"userName"}, unique = true)})
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String userName;
    @NonNull
    private String password;
    @NonNull
    private String hint;

    public void setId(@NonNull int id) {
        this.id = id;
    }

    @NonNull
    public int getId() {
        return this.id;
    }

    public void setUserName(@NonNull String userName) {
        this.userName = userName.trim();
    }

    @NonNull
    public String getUserName() {
        return this.userName;
    }

    public void setPassword(@NonNull String password) {
        this.password = password.trim();
    }

    @NonNull
    public String getPassword() {
        return this.password;
    }

    public void setHint(@NonNull String hint) {
        this.hint = hint;
    }

    @NonNull
    public String getHint() {
        return hint;
    }
}
