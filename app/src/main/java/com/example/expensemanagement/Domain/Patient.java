package com.example.expensemanagement.Domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(indices = {@Index(value = {"name"}, unique = true)})
public class Patient {
    @PrimaryKey(autoGenerate = true)
    private  int id;
    @NotNull
    private  String name;
    private  boolean isStopped;
    public  Patient(String name ,boolean isStopped){
        this.setName(name);
        this.setStopped(isStopped);
    }
    @NotNull
    public int getId() {
        return id;
    }

    public void setId(@NotNull int id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name.trim();
    }

    @Override
    public String toString() {
        return  this.name.trim();
    }

    public boolean isStopped() {
        return isStopped;
    }

    public void setStopped(boolean stopped) {
        isStopped = stopped;
    }
}
