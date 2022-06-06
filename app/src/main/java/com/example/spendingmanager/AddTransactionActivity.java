package com.example.spendingmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.spendingmanager.adapter.TransactionCategoryQuickSelectAdapter;
import com.example.spendingmanager.database.DatabaseHelper;
import com.example.spendingmanager.database.model.FinancialFund;
import com.example.spendingmanager.database.model.SpendingTransaction;
import com.example.spendingmanager.database.model.TransactionCategory;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.List;

public class AddTransactionActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;

    private RelativeLayout relDateSelect;
    private TextView txtDate;
    private RelativeLayout relTimeSelect;
    private TextView txtTime;
    private EditText edtAmountInput;
    private TextInputEditText tiEdtNote;
    private FloatingActionButton saveButton;

    private RecyclerView rlvTransactionCategory;

    private void initViews() {
        rlvTransactionCategory = findViewById(R.id.rlvTransactionCategory);
        TransactionCategoryQuickSelectAdapter adapter = new TransactionCategoryQuickSelectAdapter(
                databaseHelper.transactionCategoryDao().getAll(),
                this
        );
        rlvTransactionCategory.setAdapter(adapter);
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(this);
        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        rlvTransactionCategory.setLayoutManager(flexboxLayoutManager);

        relDateSelect = findViewById(R.id.relDateSelect);
        txtDate = findViewById(R.id.txtDate);
        txtTime = findViewById(R.id.txtTime);
        relTimeSelect = findViewById(R.id.relTimeSelect);
        edtAmountInput = findViewById(R.id.edtAmountInput);
        tiEdtNote = findViewById(R.id.tiEdtNote);
        saveButton = findViewById(R.id.flbSave);
        //  Set current date time
        Calendar calendar = Calendar.getInstance();
        setTimeText(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
        setDateText(calendar.get(Calendar.DATE), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));
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
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                setDateText(day, month + 1, year);
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
                            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                                setTimeText(hour, minute);
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

    private void setTimeText(int hour, int minute) {
        String time = "";
        if (hour < 10) {
            time += "0";
        }
        time += hour + ":";
        if (minute < 10) {
            time += "0";
        }
        time += minute;
        txtTime.setText(time);
    }

    private void setDateText(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        String weekDayString = "";
        //  Convert day of week to string
        switch (dayOfWeek) {
            case 1: weekDayString = "Chủ nhật"; break;
            case 2: weekDayString = "Thứ Hai"; break;
            case 3: weekDayString = "Thứ Ba"; break;
            case 4: weekDayString = "Thứ Tư"; break;
            case 5: weekDayString = "Thứ Năm"; break;
            case 6: weekDayString = "Thứ Sáu"; break;
            case 7: weekDayString = "Thứ Bảy"; break;
        }
        //  Convert month to string
        String monthString = "";
        switch (month) {
            case 1: monthString = "Tháng 1"; break;
            case 2: monthString = "Tháng 2"; break;
            case 3: monthString = "Tháng 3"; break;
            case 4: monthString = "Tháng 4"; break;
            case 5: monthString = "Tháng 5"; break;
            case 6: monthString = "Tháng 6"; break;
            case 7: monthString = "Tháng 7"; break;
            case 8: monthString = "Tháng 8"; break;
            case 9: monthString = "Tháng 9"; break;
            case 10: monthString = "Tháng 10"; break;
            case 11: monthString = "Tháng 11"; break;
            case 12: monthString = "Tháng 12"; break;
        }

        String dateStringFormat = weekDayString + ", " + day + " " + monthString + ", " + year;
        txtDate.setText(dateStringFormat);
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

    /**
     * Get all TransactionCategory and display it as View
     */
    private void loadAllTransactionCategory() {
        List<TransactionCategory> transactionCategories = databaseHelper.transactionCategoryDao().getAll();
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