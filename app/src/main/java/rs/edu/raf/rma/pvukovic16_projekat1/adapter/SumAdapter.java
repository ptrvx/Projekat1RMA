package rs.edu.raf.rma.pvukovic16_projekat1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import rs.edu.raf.rma.pvukovic16_projekat1.R;
import rs.edu.raf.rma.pvukovic16_projekat1.model.Category;
import rs.edu.raf.rma.pvukovic16_projekat1.model.Expense;
import rs.edu.raf.rma.pvukovic16_projekat1.model.Sum;
import rs.edu.raf.rma.pvukovic16_projekat1.util.CategoryDiffCallback;
import rs.edu.raf.rma.pvukovic16_projekat1.util.SumDiffCallback;

public class SumAdapter extends RecyclerView.Adapter<SumAdapter.SumHolder> {

    private List<Sum> sumList;

    public SumAdapter() {
        sumList = new ArrayList<>();
    }

    @NonNull
    @Override
    public SumHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.sum_list_item, parent, false);
        return new SumHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SumHolder holder, int position) {
        Sum sum = sumList.get(position);
        holder.nameTv.setText(sum.getCategory().getName());
        holder.sumTv.setText(String.valueOf(sum.getSum()));
    }

    @Override
    public int getItemCount() {
        return sumList.size();
    }

    public void setData(List<Sum> sums) {
        SumDiffCallback callback = new SumDiffCallback(sumList, sums);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        sumList.clear();
        sumList.addAll(sums);
        result.dispatchUpdatesTo(this);
    }


    public class SumHolder extends RecyclerView.ViewHolder {
        TextView nameTv;
        TextView sumTv;

        public SumHolder (@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.t3_tv_name);
            sumTv = itemView.findViewById(R.id.t3_tv_sum);
        }
    }
}
