package com.example.expensemanagement.Daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.expensemanagement.Domain.OutlayOwner;

import java.util.List;

@Dao
public interface OutlayOwnerDao {
    @Insert
    void insert(OutlayOwner outlayOwner);

    @Delete
    void delete(OutlayOwner outlayOwner);

    @Update
    void update(OutlayOwner outlayOwner);

    @Query("Select * from OutlayOwner")
    LiveData<List<OutlayOwner>> getAll();
}
