package rs.edu.raf.rma.pvukovic16_projekat1.util;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

import rs.edu.raf.rma.pvukovic16_projekat1.model.Sum;

public class SumDiffCallback extends DiffUtil.Callback {

    private List<Sum> oldList;
    private List<Sum> newList;

    public SumDiffCallback(List<Sum> oldList, List<Sum> newList) {
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
        Sum oldCat = oldList.get(oldItemPosition);
        Sum newCat = newList.get(newItemPosition);
        return oldCat == newCat || oldCat.getId() == newCat.getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Sum oldCat = oldList.get(oldItemPosition);
        Sum newCat = newList.get(newItemPosition);
        return oldCat.getCategory().getName().equals(newCat.getCategory().getName()) && (oldCat.getSum() == newCat.getSum());
    }
}
