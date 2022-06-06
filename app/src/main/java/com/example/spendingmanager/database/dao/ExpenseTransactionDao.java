package com.example.spendingmanager.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.spendingmanager.database.model.ExpenseTransaction;

import java.util.List;

@Dao
public interface ExpenseTransactionDao {
    @Query("SELECT * FROM expense_transaction")
    List<ExpenseTransaction> getAll();

    @Insert
    void insert(ExpenseTransaction... expenseTransactions);

    @Update
    void update(ExpenseTransaction expenseTransaction);

    @Delete
    void delete(ExpenseTransaction expenseTransaction);
}
