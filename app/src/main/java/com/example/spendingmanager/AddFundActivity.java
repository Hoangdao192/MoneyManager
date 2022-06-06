package com.example.spendingmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.spendingmanager.database.DatabaseHelper;
import com.example.spendingmanager.database.model.FinancialFund;

import java.util.List;

public class AddFundActivity extends AppCompatActivity {

    private EditText edtFundName, edtBalance;
    private Button btnAdd;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fund);

        databaseHelper = DatabaseHelper.getInstance(this);

        edtFundName = findViewById(R.id.edtFundName);
        edtBalance = findViewById(R.id.edtBalance);
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FinancialFund financialFund = new FinancialFund();
                financialFund.setName(edtFundName.getText().toString());
                financialFund.setBalance(Long.parseLong(edtBalance.getText().toString()));
                databaseHelper.financialFundDao().insert(financialFund);
                showAll();
            }
        });
    }

    public void showAll() {
        List<FinancialFund> financialFunds = databaseHelper.financialFundDao().getAll();
        for (int i = 0; i < financialFunds.size(); ++i) {
            System.out.println(financialFunds.get(i).toString());
        }
    }
}