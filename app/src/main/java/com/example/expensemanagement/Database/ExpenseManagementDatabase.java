package com.example.expensemanagement.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.expensemanagement.Daos.MaterialDao;
import com.example.expensemanagement.Domain.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Material.class}, version = 1, exportSchema = false)
public abstract class ExpenseManagementDatabase extends RoomDatabase {
    public abstract MaterialDao materialDao();

    private static final String dbName = "ExpenseManagementDatabase";
    private static volatile ExpenseManagementDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static ExpenseManagementDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ExpenseManagementDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ExpenseManagementDatabase.class, dbName)
                            .fallbackToDestructiveMigration()
                            .addCallback(callback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new SeedMaterialAsyncTask(INSTANCE.materialDao()).execute();
        }
    };

    private static class SeedMaterialAsyncTask extends AsyncTask<Void, Void, Void> {

        private MaterialDao materialDao;

        public SeedMaterialAsyncTask(MaterialDao materialDao) {
            this.materialDao = materialDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Material electricityBill = new Material("Electricity bill", null, true);
            materialDao.insert(electricityBill);
            Material waterBill = new Material("Water bill", null, true);
            materialDao.insert(waterBill);
            return null;
        }
    }
}
