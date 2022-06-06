package com.example.spendingmanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spendingmanager.R;
import com.example.spendingmanager.database.model.TransactionCategory;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Recycler View adapter for quick select TransactionCategory
 * (use by Recycler View in AddTransactionActivity)
 */
public class TransactionCategoryQuickSelectAdapter extends RecyclerView.Adapter<TransactionCategoryQuickSelectAdapter.ViewHolder> {

    private List<TransactionCategory> transactionCategories;
    private Context context;

    public TransactionCategoryQuickSelectAdapter(List<TransactionCategory> transactionCategories, Context context) {
        this.transactionCategories = transactionCategories;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.transaction_category_quick_select_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TransactionCategory transactionCategory = transactionCategories.get(position);
        Map<String, Integer> drawableDictionary = new HashMap<>();
        drawableDictionary.put(context.getString(R.string.category_general), R.drawable.ic_category_general);
        drawableDictionary.put(context.getString(R.string.category_food_drink), R.drawable.ic_category_food_and_drink);
        drawableDictionary.put(context.getString(R.string.category_bill), R.drawable.ic_category_bill);
        drawableDictionary.put(context.getString(R.string.category_education), R.drawable.ic_category_education);

        if (transactionCategory.getType() == TransactionCategory.TYPE_DEFAULT) {
            holder.imgCategory.setImageDrawable(context.getDrawable(drawableDictionary.get(transactionCategory.getName())));
        } else {
            holder.imgCategory.setImageDrawable(context.getDrawable(R.drawable.ic_category_general));
        }

        holder.txtCategoryName.setText(transactionCategory.getName());
    }

    @Override
    public int getItemCount() {
        return transactionCategories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgCategory;
        private TextView txtCategoryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCategory = itemView.findViewById(R.id.imgCategory);
            txtCategoryName = itemView.findViewById(R.id.txtCategoryName);
        }
    }
}
