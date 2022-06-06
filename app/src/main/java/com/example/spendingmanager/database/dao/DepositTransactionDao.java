package com.example.spendingmanager.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.spendingmanager.database.model.DepositTransaction;

import java.util.List;

@Dao
public interface DepositTransactionDao {
    @Query("SELECT * FROM deposit_transaction")
    List<DepositTransaction> getAll();

    @Insert
    void insert(DepositTransaction... depositTransactions);

    @Delete
    void delete(DepositTransaction depositTransaction);

    @Update
    void update(DepositTransaction depositTransaction);
}
