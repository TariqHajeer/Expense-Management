package com.example.expensemanagement.Daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import com.example.expensemanagement.DBViews.FullOutlay;
import com.example.expensemanagement.Domain.Outlay;
import com.example.expensemanagement.Helper.DateConverter;

import java.util.List;

@Dao
@TypeConverters({DateConverter.class})
public interface OutlayDao {
    @Insert
    void insert(Outlay outlay);

    @Delete
    void delete(Outlay outlay);

    @Update
    void update(Outlay outlay);

    @Query("Select * from FullOutlay")
    LiveData<List<FullOutlay>> GetAll();
}
