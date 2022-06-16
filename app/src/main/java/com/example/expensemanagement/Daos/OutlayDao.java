package com.example.expensemanagement.Daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import com.example.expensemanagement.DBViews.FullOutlay;
import com.example.expensemanagement.DBViews.TotalView;
import com.example.expensemanagement.Domain.Outlay;
import com.example.expensemanagement.Helper.DateConverter;

import java.util.Date;
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

    @Query("Select * from FullOutlay order by date desc")
    LiveData<List<FullOutlay>> getAll();

    @Query("Select * from Outlay where id=:id")
    LiveData<Outlay> getById(int id);

    @Query("Select * from FullOutlay where date >= :from and date <= :to ")
    LiveData<List<FullOutlay>> getDateFilter(Date from, Date to);

    @Query("Select Sum(price) from FullOutlay where date >= :from and date<= :to ")
    double sumDateFilter(Date from, Date to);

    @Query("Select * from FullOutlay where material_id=:material_id ")
    LiveData<List<FullOutlay>> getByMaterial(int material_id);

    @Query("Select * from FullOutlay where outlayOwner_id =:owner_id ")
    LiveData<List<FullOutlay>> getByOwner(int owner_id);

    @Query("Select sum(price) from FullOutlay where material_id =:material_id")
    double sumByMaterial(int material_id);

    @Query("Select sum(price) from FullOutlay where outlayOwner_id =:owner_id")
    double sumByOwner(int owner_id);

    @Query("Select * from TotalView where isService=:isService")
    LiveData<List<TotalView>> getTotal(boolean isService);

}
