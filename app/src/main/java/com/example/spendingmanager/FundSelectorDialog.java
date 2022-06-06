package com.example.spendingmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spendingmanager.adapter.FundRecyclerViewAdapter;
import com.example.spendingmanager.database.model.FinancialFund;

import java.util.List;

public class FundSelectorDialog {
    private AlertDialog dialog;
    private List<FinancialFund> financialFunds;
    private Context context;
    private View view;

    private RecyclerView fundRecyclerView;
    private FundRecyclerViewAdapter adapter;
    private FundRecyclerViewAdapter.OnItemClick onItemClick = null;

    public FundSelectorDialog(Context context, List<FinancialFund> financialFunds) {
        this(context, financialFunds, null);
    }

    public FundSelectorDialog(Context context, List<FinancialFund> financialFunds, FundRecyclerViewAdapter.OnItemClick onItemClick) {
        this.context = context;
        this.financialFunds = financialFunds;
        this.onItemClick = onItemClick;
        createDialog();
    }

    /**
     * Create dialog
     */
    public void createDialog() {
        /*  Init dialog view  */
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.fund_selector_dialog, null);
        //  Init recycler view
        fundRecyclerView = view.findViewById(R.id.relFundSelector);
        adapter = new FundRecyclerViewAdapter(financialFunds, onItemClick);
        fundRecyclerView.setAdapter(adapter);
        fundRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        dialog = builder.create();
    }

    public void setOnItemClick(FundRecyclerViewAdapter.OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
        this.adapter.setOnItemClick(onItemClick);
    }

    public void show() {
        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }
}
