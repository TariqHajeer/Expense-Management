package com.example.expensemanagement.Daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.expensemanagement.Domain.CaringType;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@Dao
public interface CaringTypeDao {
    @Insert
    void  Insert(CaringType caringType);
    @Update
    void  Update (CaringType caringType);
    @Delete
    void  Delete(CaringType caringType);
    @Query("Select * from CaringType")
    LiveData<List<CaringType>> GetAll();
    @Query("Select Count(*) from CaringType where name=:name")
    int Count(@NotNull String name );
    @Query("Select Count(*) from CaringType where name=:name and id !=:id")
    int Count(@NotNull int id ,@NotNull String name );
}
