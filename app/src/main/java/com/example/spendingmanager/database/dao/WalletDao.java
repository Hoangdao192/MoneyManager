package com.example.spendingmanager.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.spendingmanager.database.model.Wallet;

import java.util.List;

@Dao
public interface WalletDao {
    @Query("SELECT * FROM wallet")
    List<Wallet> getAll();

    @Insert
    void insert(Wallet... wallets);

    @Update
    void update(Wallet wallet);

    @Delete
    void delete(Wallet wallet);
}
