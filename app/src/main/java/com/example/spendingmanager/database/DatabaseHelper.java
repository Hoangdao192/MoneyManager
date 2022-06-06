package com.example.spendingmanager.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.spendingmanager.database.dao.DepositTransactionDao;
import com.example.spendingmanager.database.dao.FinancialFundDao;
import com.example.spendingmanager.database.dao.SpendingTransactionDao;
import com.example.spendingmanager.database.model.DepositTransaction;
import com.example.spendingmanager.database.model.FinancialFund;
import com.example.spendingmanager.database.model.SpendingTransaction;

import java.util.List;

@Database(entities = {SpendingTransaction.class, FinancialFund.class, DepositTransaction.class}, version = 4)
public abstract class DatabaseHelper extends RoomDatabase {
    public static String DATABASE_NAME = "spending_manager";
    public static DatabaseHelper INSTANCE = null;

    public abstract SpendingTransactionDao spendingTransactionDao();
    public abstract FinancialFundDao financialFundDao();
    public abstract DepositTransactionDao depositTransactionDao();

    public static DatabaseHelper getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    DatabaseHelper.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        if (INSTANCE.financialFundDao().getAll().size() == 0) {
            FinancialFund financialFund = new FinancialFund();
            financialFund.setName("Tiền mặt");
            financialFund.setBalance(0);
            INSTANCE.financialFundDao().insert(financialFund);
        }
        System.out.println(INSTANCE.financialFundDao().getAll().size());
        return INSTANCE;
    }
}
