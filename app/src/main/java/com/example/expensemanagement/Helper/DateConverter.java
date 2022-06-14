package com.example.expensemanagement.Helper;


import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverter {
    @TypeConverter
    public static Date toDate(Long m){
        return  m==null?null:new Date(m);
    }
    @TypeConverter
    public  static Long fromDate(Date date){
        return  date==null?null:date.getTime();
    }
}
