package com.example.spendingmanager.database.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "financial_fund")
public class FinancialFund {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private long balance;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FinancialFund{" +
                "id=" + id +
                ", balance=" + balance +
                ", name='" + name + '\'' +
                '}';
    }
}
