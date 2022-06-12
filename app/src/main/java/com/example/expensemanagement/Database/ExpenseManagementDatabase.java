package com.example.expensemanagement.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.expensemanagement.Daos.MaterialDao;
import com.example.expensemanagement.Domain.Material;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Material.class}, version = 1, exportSchema = false)
public abstract class ExpenseManagementDatabase extends RoomDatabase {
    public abstract MaterialDao materialDao();

    private static volatile ExpenseManagementDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static ExpenseManagementDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ExpenseManagementDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ExpenseManagementDatabase.class, "word_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
