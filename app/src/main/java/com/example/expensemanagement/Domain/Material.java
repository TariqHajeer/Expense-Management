package com.example.expensemanagement.Domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Material Table
 * name are unique
 */
@Entity(indices = {@Index(value = {"name"}, unique = true)})
public class Material {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String name;
    private String description;
    private boolean isService;

    public Material(String name, String description, boolean isService) {
        this.setName(name);
        this.setDescription(description);
        this.setIsService(isService);
    }

    public int getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String description) {
        this.description = description.trim();
    }

    public String getDescription() {
        return this.description;
    }

    public void setIsService(boolean isService) {
        this.isService = isService;
    }

    public boolean getIsService() {
        return isService;
    }
}
