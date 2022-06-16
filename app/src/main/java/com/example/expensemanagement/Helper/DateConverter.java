package com.example.expensemanagement.Helper;


import androidx.room.TypeConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {
    @TypeConverter
    public static Date toDate(String m) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.parse(m);

    }

    @TypeConverter
    public static String fromDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(date);
    }
}
