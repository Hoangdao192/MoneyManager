package com.example.spendingmanager.database.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "spending_transaction",
        foreignKeys = {
                @ForeignKey(
                        entity = FinancialFund.class,
                        parentColumns = "id",
                        childColumns = "fundId",
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE
                )
        }
)
public class SpendingTransaction {
    @PrimaryKey (autoGenerate = true)
    private int id;
    private String date;
    private long amount;

    public int getFundId() {
        return fundId;
    }

    public void setFundId(int fundId) {
        this.fundId = fundId;
    }

    private String spendFor;
    private String note = "";
    private int fundId;

    public SpendingTransaction(int id, String date, long amount, String spendFor, String note, int fundId) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.spendFor = spendFor;
        this.note = note;
        this.fundId = fundId;
    }

    @Ignore
    public SpendingTransaction(String date, long amount, String spendFor, String note, int fundId) {
        this.date = date;
        this.amount = amount;
        this.spendFor = spendFor;
        this.note = note;
        this.fundId = fundId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getSpendFor() {
        return spendFor;
    }

    public void setSpendFor(String spendFor) {
        this.spendFor = spendFor;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "SpendingTransaction{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", amount=" + amount +
                ", spendFor='" + spendFor + '\'' +
                ", note='" + note + '\'' +
                ", fundId=" + fundId +
                '}';
    }
}
