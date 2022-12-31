package com.example.expensemanagement.Daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.expensemanagement.Domain.Patient;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@Dao
public interface PatientDao {
    @Insert
    void  Insert(Patient patient);
    @Update
    void  Update (Patient patient);
    @Delete
    void  Delete(Patient patient);
    @Query("Select * from CaringType")
    LiveData<List<Patient>> GetAll();
    @Query("Select Count(*) from Patient where name=:name")
    int Count(@NotNull String name );
    @Query("Select Count(*) from Patient where name=:name and id !=:id")
    int Count(@NotNull int id ,@NotNull String name );
}
