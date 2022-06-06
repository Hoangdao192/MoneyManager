package com.example.spendingmanager.database.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "income_transaction",
        foreignKeys = {
                @ForeignKey(
                        entity = Wallet.class,
                        parentColumns = "id",
                        childColumns = "walletId",
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE
                )
        }
)
public class IncomeTransaction {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int walletId;
    private long timestamp;
    private long amount;
    private String note = "";
}
