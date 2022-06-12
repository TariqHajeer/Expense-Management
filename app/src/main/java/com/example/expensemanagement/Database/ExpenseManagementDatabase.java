package com.example.expensemanagement.Database;

import android.content.Context;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.expensemanagement.Daos.MaterialDao;
import com.example.expensemanagement.Daos.OutlayOwnerDao;
import com.example.expensemanagement.Domain.Material;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Material.class}, version = 2, exportSchema = false)
public abstract class ExpenseManagementDatabase extends RoomDatabase {
    public abstract MaterialDao materialDao();
    public  abstract OutlayOwnerDao outlayOwnerDao();
    private static final String dbName = "ExpenseManagementDatabase.db";
    private static volatile ExpenseManagementDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ExpenseManagementDatabase getDatabase(final Context context) {
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

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            materialSeed();
        }
    };

    private static void materialSeed() {
        databaseWriteExecutor.execute(() -> {
            // Populate the database in the background.
            // If you want to start with more words, just add them.
            MaterialDao dao = INSTANCE.materialDao();
            Material electricityBill = new Material("Electricity bill", null, true);
            dao.insert(electricityBill);
            Material waterBill = new Material("Water bill", null, true);
            dao.insert(waterBill);
        });
    }

}
