package com.example.expensemanagement.Domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(indices = {@Index(value = {"name"}, unique = true)})
public class CaringType {
    @PrimaryKey(autoGenerate = true)
    private  int id;
    @NotNull
    private  String name;
    private  String description;
    public  CaringType(String name ,String description){
        this.setName(name);
        this.setDescription(description);
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
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(description!=null)
            this.description = description.trim();
    }

    @Override
    public String toString() {
        return  this.name.trim();
    }


}
