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
import com.example.expensemanagement.DBViews.TotalView;
import com.example.expensemanagement.Daos.MaterialDao;
import com.example.expensemanagement.Daos.OutlayDao;
import com.example.expensemanagement.Daos.OutlayOwnerDao;
import com.example.expensemanagement.Daos.UserDao;
import com.example.expensemanagement.Domain.Material;
import com.example.expensemanagement.Domain.Outlay;
import com.example.expensemanagement.Domain.OutlayOwner;
import com.example.expensemanagement.Domain.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Material.class, OutlayOwner.class, User.class, Outlay.class}, views = {FullOutlay.class, TotalView.class}, version = 1, exportSchema = true)
public abstract class ExpenseManagementDatabase extends RoomDatabase {
    public abstract MaterialDao materialDao();

    public abstract OutlayOwnerDao outlayOwnerDao();

    public abstract UserDao userDao();

    public abstract OutlayDao outlayDao();

    private static final String dbName = "temp18";
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
            ExpenseManagementDatabase.databaseWriteExecutor.execute(() -> {
                ArrayList<Material> materials = new ArrayList<>();
                OutlayDao outlayDao = INSTANCE.outlayDao();
                MaterialDao mdao = INSTANCE.materialDao();
                Material electricityBill = new Material("Electricity bill", null, true);
                materials.add(electricityBill);
                mdao.insert(electricityBill);
                Material waterBill = new Material("Water bill", null, true);
                mdao.insert(waterBill);
                materials.add(waterBill);
                Material potato = new Material("Potato", null, false);
                mdao.insert(potato);
                materials.add(potato);
                Material mobile = new Material("Mobile", null, false);
                mdao.insert(mobile);
                materials.add(mobile);
                OutlayOwnerDao oodao = INSTANCE.outlayOwnerDao();
                OutlayOwner me = new OutlayOwner("Me", null);
                oodao.insert(me);

                OutlayOwner myWife = new OutlayOwner("My wife", "don't give him a lot of money");
                oodao.insert(myWife);
            });
        }
    };


}
