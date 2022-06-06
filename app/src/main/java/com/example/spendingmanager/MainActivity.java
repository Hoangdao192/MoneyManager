package com.example.spendingmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.spendingmanager.database.DatabaseHelper;
import com.example.spendingmanager.database.model.FinancialFund;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = DatabaseHelper.getInstance(this);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        FloatingActionButton flbAddTransaction = findViewById(R.id.flbAddTransaction);
        flbAddTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTransactionActivity.class);
                startActivity(intent);
            }
        });

        Button addButton = findViewById(R.id.btnAdd);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTransactionActivity.class);
                startActivity(intent);
            }
        });

        Button addFundButton = findViewById(R.id.btnAddFund);
        addFundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddFundActivity.class);
                startActivity(intent);
            }
        });

        Button viewFundButton = findViewById(R.id.btnViewFund);
        viewFundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<FinancialFund> financialFunds = databaseHelper.financialFundDao().getAll();
                for (int i = 0; i < financialFunds.size(); ++i) {
                    System.out.println(financialFunds.get(i).toString());
                }
            }
        });
    }

    public void showAll() {
        System.out.println(databaseHelper.financialFundDao().getTotalBalance());
    }
}