package com.example.expensemanagement.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.expensemanagement.Daos.CaringTypeDao;
import com.example.expensemanagement.Daos.MaterialDao;
import com.example.expensemanagement.Daos.OutlayDao;
import com.example.expensemanagement.Daos.OutlayOwnerDao;
import com.example.expensemanagement.Daos.PatientDao;
import com.example.expensemanagement.Domain.CaringType;
import com.example.expensemanagement.Domain.Material;
import com.example.expensemanagement.Domain.OutlayOwner;
import com.example.expensemanagement.Domain.Patient;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@androidx.room.Database(entities = {CaringType.class, Patient.class}, version = 1, exportSchema = true)
public abstract class Database extends RoomDatabase {

    public abstract CaringTypeDao CaringTypeDao();
    public abstract PatientDao PatientDao();
    private static final String dbName = "database3";
    /*
    volatile this word to force thread work in the original instance on the memory
     */
    private static volatile Database INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public static Database getDatabase(final Context context) {
        if (INSTANCE == null) {
            /*
            synchronized to prevent multiple thread
            and singleton pattern to prevent multiple instance creation
             */
            synchronized (Database.class) {
                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    Database.class, dbName)
                            .fallbackToDestructiveMigration()
                            .addCallback(databaseCallBack)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback databaseCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}
