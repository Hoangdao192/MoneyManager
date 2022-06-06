package com.example.spendingmanager.database.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "transaction_category")
/**
 * Define the TransactionCategory property and transaction_category table's structure in Database
 */
public class TransactionCategory {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;

    public TransactionCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Ignore
    public TransactionCategory(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
