package com.example.expensemanagement.Daos;

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
    void Insert(Material material);

    @Delete
    void Delete(Material material);

    @Update
    void Update(Material material);

    @Query("Select * from Material")
    LiveData<List<Material>> GetAll();
}
