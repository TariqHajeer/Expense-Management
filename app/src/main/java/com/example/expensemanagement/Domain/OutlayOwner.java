package com.example.expensemanagement.Domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"name"}, unique = true)})
public class OutlayOwner {
    @PrimaryKey(autoGenerate = true)
    private  int id;
    @NonNull
    private  String name;
    private String description;
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
    public void setName(@NonNull String name) {
        this.name = name.trim();
    }

    public String getName() {
        return this.name;
    }
    public void setDescription(String description) {
        if (description != null)
            this.description = description.trim();
    }

    public String getDescription() {
        return this.description;
    }
}
