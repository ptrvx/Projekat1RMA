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
import rs.edu.raf.rma.pvukovic16_projekat1.util.Util;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseHolder> {

    private List<Expense> expenseList;

    private OnImageClickCallback mOnImageClickCallback;
    private OnItemRemoveCallback mOnItemRemoveCallback;

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
        holder.cost.setText(Integer.toString(expense.getCost()));
        holder.date.setText(expense.getDate());

    }

    public void setData(List<Expense> expenses) {
        ExpenseDiffCallback callback = new ExpenseDiffCallback(expenseList, expenses);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        expenseList.clear();
        expenseList.addAll(expenses);
        result.dispatchUpdatesTo(this);
    }

    public void setOnImageClickCallback(OnImageClickCallback onImageClickCallback){
        mOnImageClickCallback = onImageClickCallback;
    }

    public void setOnItemRemoveCallback (OnItemRemoveCallback onItemRemoveCallback) {
        mOnItemRemoveCallback = onItemRemoveCallback;
    }

    // Callback we use when user clicks on remove
    public interface OnItemRemoveCallback {
        void onItemRemove(int id);
    }

    //Callback we use when user click on avatar avatarImage
    public interface OnImageClickCallback {
        void onImageClick(int position);
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
        Button detbtn;
        TextView date;

        public ExpenseHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.t2_tv_name);
            cost = itemView.findViewById(R.id.t2_tv_cost);
            cat = itemView.findViewById(R.id.t2_tv_cat);
            date = itemView.findViewById(R.id.t2_tv_date);

            rmbtn = itemView.findViewById(R.id.t2_btn_rm);
            detbtn = itemView.findViewById(R.id.t2_btn_details);

            rmbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        if (mOnItemRemoveCallback != null) {
                            mOnItemRemoveCallback.onItemRemove(expenseList.get(position).getId());
                        }
                    }
                }
            });

            detbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        if (mOnImageClickCallback != null) {
                            mOnImageClickCallback.onImageClick(position);
                        }
                    }
                }
            });

        }
    }
}
