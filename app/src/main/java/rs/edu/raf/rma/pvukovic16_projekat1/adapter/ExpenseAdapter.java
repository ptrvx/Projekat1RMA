package rs.edu.raf.rma.pvukovic16_projekat1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import rs.edu.raf.rma.pvukovic16_projekat1.R;
import rs.edu.raf.rma.pvukovic16_projekat1.model.Expense;
import rs.edu.raf.rma.pvukovic16_projekat1.util.ExpenseDiffCallback;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseHolder> {

    private List<Expense> expenseList;

    public ExpenseAdapter() {
        expenseList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ExpenseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.exp_list_item, parent, false);
        return new ExpenseAdapter.ExpenseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseHolder holder, int position) {
        Expense expense = expenseList.get(position);
        holder.name.setText(expense.getName());
        holder.cat.setText(expense.getCategory().toString());
        holder.cost.setText(expense.getCost());
        holder.date.setText(expense.getDate().toString());

    }

    public void setData(List<Expense> expenses) {
        ExpenseDiffCallback callback = new ExpenseDiffCallback(expenseList, expenses);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        expenseList.clear();
        expenseList.addAll(expenses);
        result.dispatchUpdatesTo(this);
    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    public class ExpenseHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView cost;
        TextView cat;
        Button rmbtn;
        TextView date;

        public ExpenseHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.t2_tv_name);
            cost = itemView.findViewById(R.id.t2_tv_cost);
            cat = itemView.findViewById(R.id.t2_tv_cat);
            date = itemView.findViewById(R.id.t2_tv_date);
            rmbtn = itemView.findViewById(R.id.t2_btn_apply);

        }
    }
}
