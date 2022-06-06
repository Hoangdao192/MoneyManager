package com.example.spendingmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.spendingmanager.adapter.FundRecyclerViewAdapter;
import com.example.spendingmanager.database.DatabaseHelper;
import com.example.spendingmanager.database.model.FinancialFund;
import com.example.spendingmanager.database.model.SpendingTransaction;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.List;

public class AddTransactionActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;

    private RelativeLayout relDateSelect;
    private RelativeLayout relTimeSelect;
    private EditText edtAmountInput;
    private TextInputEditText tiEdtNote;
    private FloatingActionButton saveButton;

    private void initViews() {
        relDateSelect = findViewById(R.id.relDateSelect);
        relTimeSelect = findViewById(R.id.relTimeSelect);
        edtAmountInput = findViewById(R.id.edtAmountInput);
        tiEdtNote = findViewById(R.id.tiEdtNote);
        saveButton = findViewById(R.id.flbSave);
    }

    private void initEventHandle() {
        relDateSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddTransactionActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DATE)
                );
                datePickerDialog.show();
            }
        });
        relTimeSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                System.out.println(calendar.get(Calendar.HOUR) + " " + calendar.get(Calendar.HOUR_OF_DAY));
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        AddTransactionActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {

                            }
                        },
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        true
                );
                timePickerDialog.show();
            }
        });
    }

    private void showAll() {
        List<SpendingTransaction> spendingTransactionList = databaseHelper.spendingTransactionDao().getAll();
        for (int i = 0; i < spendingTransactionList.size(); ++i) {
            System.out.println(spendingTransactionList.get(i).toString());
        }
    }

    private void addSpendingTransaction(
            String date,
            long amount,
            String spendFor,
            String note,
            FinancialFund financialFund
    ) {
        //  Add new spending transaction
        SpendingTransaction spendingTransaction = new SpendingTransaction(
                date,
                amount,
                spendFor,
                note,
                financialFund.getId()
        );
        databaseHelper.spendingTransactionDao().insert(spendingTransaction);

        //  Update fund balance
        long newBalance = financialFund.getBalance() - amount;
        financialFund.setBalance(newBalance);
        databaseHelper.financialFundDao().update(financialFund);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        databaseHelper = DatabaseHelper.getInstance(this);

        initViews();
        initEventHandle();
    }
}