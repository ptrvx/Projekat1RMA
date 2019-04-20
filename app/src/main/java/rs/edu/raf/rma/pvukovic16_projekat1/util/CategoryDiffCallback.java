package rs.edu.raf.rma.pvukovic16_projekat1.util;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

import rs.edu.raf.rma.pvukovic16_projekat1.model.Category;

public class CategoryDiffCallback extends DiffUtil.Callback {

    private List<Category> oldList;
    private List<Category> newList;

    public CategoryDiffCallback(List<Category> oldList, List<Category> newList) {
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
        Category oldCat = oldList.get(oldItemPosition);
        Category newCat = newList.get(newItemPosition);
        return oldCat.getId() == newCat.getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Category oldCat = oldList.get(oldItemPosition);
        Category newCat = newList.get(newItemPosition);
        return oldCat.getName().equals(newCat.getName());
    }
}
