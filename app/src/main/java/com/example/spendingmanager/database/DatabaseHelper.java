package com.example.spendingmanager.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.spendingmanager.R;
import com.example.spendingmanager.database.dao.DepositTransactionDao;
import com.example.spendingmanager.database.dao.FinancialFundDao;
import com.example.spendingmanager.database.dao.SpendingTransactionDao;
import com.example.spendingmanager.database.dao.TransactionCategoryDao;
import com.example.spendingmanager.database.dao.WalletDao;
import com.example.spendingmanager.database.model.DepositTransaction;
import com.example.spendingmanager.database.model.FinancialFund;
import com.example.spendingmanager.database.model.SpendingTransaction;
import com.example.spendingmanager.database.model.TransactionCategory;
import com.example.spendingmanager.database.model.Wallet;

import java.util.List;

@Database(
        entities = {
                SpendingTransaction.class,
                FinancialFund.class,
                DepositTransaction.class,
                TransactionCategory.class,
                Wallet.class
        },
        version = 6
)
public abstract class DatabaseHelper extends RoomDatabase {
    private static Context applicationContext;
    public static String DATABASE_NAME = "spending_manager";
    public static DatabaseHelper INSTANCE = null;

    public abstract SpendingTransactionDao spendingTransactionDao();
    public abstract FinancialFundDao financialFundDao();
    public abstract DepositTransactionDao depositTransactionDao();
    public abstract TransactionCategoryDao transactionCategoryDao();
    public abstract WalletDao walletDao();

    public static DatabaseHelper getInstance(Context context) {
        if (INSTANCE == null) {
            applicationContext = context.getApplicationContext();
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    DatabaseHelper.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        if (INSTANCE.transactionCategoryDao().getAll().size() == 0) {
            createDefaultTransactionCategory();
            System.out.println(INSTANCE.transactionCategoryDao().getAll().toString());
        }

        if (INSTANCE.walletDao().getAll().size() == 0) {
            createDefaultWallet();
            System.out.println(INSTANCE.walletDao().getAll().toString());
        }

        return INSTANCE;
    }

    /**
     * Create some default TransactionCategory when the database first time initialize.
     */
    private static void createDefaultTransactionCategory() {
        String[] transactionCategoryNames = {
                applicationContext.getString(R.string.category_general),
                applicationContext.getString(R.string.category_education),
                applicationContext.getString(R.string.category_bill),
                applicationContext.getString(R.string.category_food_drink)
        };
        for(int i = 0; i < transactionCategoryNames.length; ++i) {
            TransactionCategory transactionCategory = new TransactionCategory(
                    transactionCategoryNames[i], TransactionCategory.TYPE_DEFAULT
            );
            INSTANCE.transactionCategoryDao().insert(transactionCategory);
        }
    }

    /**
     * Create some default Wallet when the database first time initialize.
     */
    private static void createDefaultWallet() {
        String[] walletNames = {
                applicationContext.getString(R.string.wallet_cash)
        };
        for(int i = 0; i < walletNames.length; ++i) {
            Wallet wallet = new Wallet(
                    walletNames[i], 0
            );
            INSTANCE.walletDao().insert(wallet);
        }
    }
}
