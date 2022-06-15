package com.example.expensemanagement.Daos;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.expensemanagement.Domain.Material;

import java.util.List;

@Dao
public interface MaterialDao {
    @Insert
    void insert(Material material);

    @Delete
    void delete(Material material);

    @Update
    void update(Material material);

    @Query("Select * from Material")
    LiveData<List<Material>> getAll();

    @Query("Select Count(*) from Material where name=:name")
    public int exist(@NonNull String name);

    @Query("Select Count(*) from Material where name=:name and id!=:id")
    public int exist(@NonNull String name,@NonNull int id);


}
