package rs.edu.raf.rma.pvukovic16_projekat1.util;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

import rs.edu.raf.rma.pvukovic16_projekat1.model.Expense;

public class ExpenseDiffCallback extends DiffUtil.Callback {

    private List<Expense> oldList;
    private List<Expense> newList;

    public ExpenseDiffCallback(List<Expense> oldList, List<Expense> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        Expense oldExp = oldList.get(oldItemPosition);
        Expense newExp = newList.get(newItemPosition);
        return oldExp.getId() == newExp.getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Expense oldExp = oldList.get(oldItemPosition);
        Expense newExp = newList.get(newItemPosition);
        return oldExp.getName().equals(newExp.getName()) && oldExp.getCost() == newExp.getCost() && oldExp.getCategory().getName().equals(newExp.getCategory().getName());
    }
}
