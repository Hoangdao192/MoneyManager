package com.example.spendingmanager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spendingmanager.R;
import com.example.spendingmanager.database.model.FinancialFund;

import java.util.List;

public class FundRecyclerViewAdapter extends RecyclerView.Adapter<FundRecyclerViewAdapter.ViewHolder> {

    private List<FinancialFund> financialFunds;
    private OnItemClick onItemClick = null;

    public FundRecyclerViewAdapter(List<FinancialFund> financialFundList) {
        this.financialFunds = financialFundList;
    }

    public FundRecyclerViewAdapter(List<FinancialFund> financialFundList, OnItemClick onItemClick) {
        this.financialFunds = financialFundList;
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fund_selector_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtFundName.setText(financialFunds.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return financialFunds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtFundName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            if (onItemClick != null) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onItemClick.onClick(financialFunds.get(getAdapterPosition()));
                    }
                });
            }
            txtFundName = itemView.findViewById(R.id.txtFundName);
        }
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
        notifyDataSetChanged();
    }

    public interface OnItemClick {
        void onClick(FinancialFund financialFund);
    }
}
