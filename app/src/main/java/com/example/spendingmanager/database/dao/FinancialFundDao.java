package com.example.spendingmanager.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.spendingmanager.database.model.FinancialFund;

import java.util.List;

@Dao
public interface FinancialFundDao {
    @Query("SELECT * FROM financial_fund")
    List<FinancialFund> getAll();

    @Query("SELECT * FROM financial_fund WHERE id = :id")
    FinancialFund getById(int id);

    @Insert
    void insert(FinancialFund... financialFunds);

    @Delete
    void delete(FinancialFund financialFund);

    @Query("SELECT SUM(balance) FROM financial_fund")
    long getTotalBalance();

    @Update
    void update(FinancialFund financialFund);
}
