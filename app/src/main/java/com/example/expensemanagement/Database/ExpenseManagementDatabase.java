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

import com.example.expensemanagement.DBViews.FullOutlay;
import com.example.expensemanagement.Daos.MaterialDao;
import com.example.expensemanagement.Daos.OutlayDao;
import com.example.expensemanagement.Daos.OutlayOwnerDao;
import com.example.expensemanagement.Daos.UserDao;
import com.example.expensemanagement.Domain.Material;
import com.example.expensemanagement.Domain.Outlay;
import com.example.expensemanagement.Domain.OutlayOwner;
import com.example.expensemanagement.Domain.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Material.class, OutlayOwner.class, User.class, Outlay.class},views = {FullOutlay.class}, version = 5, exportSchema = true)
public abstract class ExpenseManagementDatabase extends RoomDatabase {
    public abstract MaterialDao materialDao();

    public abstract OutlayOwnerDao outlayOwnerDao();

    public abstract UserDao userDao();

    public abstract OutlayDao outlayDao();

    private static final String dbName = "temp2";
    /*
    volatile this word to force thread work in the original instance on the memory
     */
    private static volatile ExpenseManagementDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ExpenseManagementDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            /*
            synchronized to prevent multiple thread
            and singleton pattern to prevent multiple instance creation
             */
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
            outlayOwnerSeed();
        }
    };

    private static void outlayOwnerSeed() {
        databaseWriteExecutor.execute(() -> {

            OutlayOwnerDao dao = INSTANCE.outlayOwnerDao();
            OutlayOwner me = new OutlayOwner("Me", null);
            dao.insert(me);

            OutlayOwner myWife = new OutlayOwner("My wife", "don't give him a lot of money");
            dao.insert(myWife);
        });
    }

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
