package com.example.expensemanagement.Domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.TypeConverters;

import com.example.expensemanagement.Helper.DateConverter;

import java.util.Date;

@Entity(
        foreignKeys = {
                @ForeignKey(parentColumns = {"id"}, childColumns = {"material_id"}, entity = Material.class),
                @ForeignKey(parentColumns = {"id"}, childColumns = {"outlayOwner_id"}, entity = OutlayOwner.class)
        })
@TypeConverters({DateConverter.class})
public class Outlay {


    public Outlay(@NonNull int material_id, @NonNull int outlayOwner_id, @NonNull double price,@NonNull String description,@NonNull Date date) {
        this.material_id = material_id;
        this.outlayOwner_id = outlayOwner_id;
        this.price = price;
        this.setDescription(description);
        this.date =date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(@NonNull int material_id) {
        this.material_id = material_id;
    }

    public int getOutlayOwner_id() {
        return outlayOwner_id;
    }

    public void setOutlayOwner_id(@NonNull int outlayOwner_id) {
        this.outlayOwner_id = outlayOwner_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(@NonNull double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description != null)
            this.description = description.trim();
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private int material_id;
    @NonNull
    private int outlayOwner_id;
    @NonNull
    private double price;
    @NonNull
    private Date date;
    private String description;


}
