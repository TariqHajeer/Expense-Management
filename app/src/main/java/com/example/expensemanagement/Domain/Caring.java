package com.example.expensemanagement.Domain;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        foreignKeys = {
                @ForeignKey(parentColumns = {"id"}, childColumns = {"patient_id"}, entity = Patient.class,onDelete = CASCADE),
                @ForeignKey(parentColumns = {"id"}, childColumns = {"caringType_id"}, entity = CaringType.class,onDelete = CASCADE )
        })
public class Caring {
    @PrimaryKey(autoGenerate = true)
    private  int id;
    private  int patient_id;
    private  int caringType_id;
    private  String time;
    private  String description;

    public void setId(int id) {
        this.id = id;
    }
    public int getId(){
        return  id;
    }
    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }
    public int getPatient_id() {
        return patient_id;
    }
    public void setCaringType_id(int caringType_id) {
        this.caringType_id = caringType_id;
    }

    public  int getCaringType_id(){
        return  caringType_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public  void setTime(String time){
        this.time =time;
    }
    public String getTime(){
        return  time;
    }

    public String getDescription() {
        return description;
    }
}
