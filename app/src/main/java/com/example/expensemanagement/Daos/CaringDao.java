package com.example.expensemanagement.Daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;

import com.example.expensemanagement.Domain.Caring;

@Dao
public interface CaringDao {
    @Insert
    void  Insert(Caring caring);
}
