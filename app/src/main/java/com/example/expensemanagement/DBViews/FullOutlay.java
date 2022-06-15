package com.example.expensemanagement.DBViews;

import androidx.room.DatabaseView;
import androidx.room.TypeConverters;

import com.example.expensemanagement.Helper.DateConverter;

import java.util.Date;

@DatabaseView(
        "SELECT o.id as id,o.description as description,o.material_id as material_id,o.outlayOwner_id as outlayOwner_id,o.price as price,o.date as date ,m.name as material_name,oo.name as outlayOwner_name " +
                "From Outlay o " +
                "JOIN Material m " +
                "on m.id ==o.material_id " +
                "JOIN OutlayOwner oo on oo.id==o.outlayOwner_id"
)
@TypeConverters({DateConverter.class})
public class FullOutlay {
    private int id;
    private int material_id;
    private int outlayOwner_id;
    private Date date;
    private double price;
    private String description;
    private String material_name;
    private String outlayOwner_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(int material_id) {
        this.material_id = material_id;
    }

    public int getOutlayOwner_id() {
        return outlayOwner_id;
    }

    public void setOutlayOwner_id(int outlayOwner_id) {
        this.outlayOwner_id = outlayOwner_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public String getOutlayOwner_name() {
        return outlayOwner_name;
    }

    public void setOutlayOwner_name(String outlayOwner_name) {
        this.outlayOwner_name = outlayOwner_name;
    }
}
