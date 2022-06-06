package com.example.spendingmanager.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.spendingmanager.database.model.SpendingTransaction;

import java.util.List;

@Dao
public interface SpendingTransactionDao {
    @Query("SELECT * FROM spending_transaction")
    List<SpendingTransaction> getAll();

    @Query("SELECT DISTINCT spendFor FROM spending_transaction")
    List<String> getAllSpendFor();

    @Insert
    void insert(SpendingTransaction... spendingTransactions);

    @Delete
    void delete(SpendingTransaction spendingTransaction);
}
