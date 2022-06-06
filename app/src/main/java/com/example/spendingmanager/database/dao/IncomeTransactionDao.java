package com.example.spendingmanager.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.spendingmanager.database.model.IncomeTransaction;

import java.util.List;

@Dao
public interface IncomeTransactionDao {
    @Query("SELECT * FROM income_transaction")
    List<IncomeTransaction> getAll();

    @Insert
    void insert(IncomeTransaction... incomeTransactions);

    @Update
    void update(IncomeTransaction incomeTransaction);

    @Delete
    void delete(IncomeTransaction incomeTransaction);
}
