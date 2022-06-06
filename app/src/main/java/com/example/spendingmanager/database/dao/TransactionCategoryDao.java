package com.example.spendingmanager.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.spendingmanager.database.model.TransactionCategory;

import java.util.List;

@Dao
/**
 * Interact with the transaction_category table in SQLite Database
 */
public interface TransactionCategoryDao {
    @Query("SELECT * FROM transaction_category")
    List<TransactionCategory> getAll();

    @Insert
    void insert(TransactionCategory... transactionCategories);

    @Update
    void update(TransactionCategory transactionCategory);

    @Delete
    void delete(TransactionCategory transactionCategory);
}
