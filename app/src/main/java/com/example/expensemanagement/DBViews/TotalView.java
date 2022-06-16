package com.example.expensemanagement.DBViews;

import androidx.room.DatabaseView;

@DatabaseView(
        "SELECT material_id,isService,material_name,sum(price) as sum FROM FullOutlay group by material_id,isService,material_name "
)
public class TotalView {

    public int getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(int material_id) {
        this.material_id = material_id;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public boolean isService() {
        return isService;
    }

    public void setService(boolean service) {
        isService = service;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    private String material_name;
    private int material_id;
    private double sum;
    private boolean isService;
}
