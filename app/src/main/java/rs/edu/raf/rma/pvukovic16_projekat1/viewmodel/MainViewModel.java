package rs.edu.raf.rma.pvukovic16_projekat1.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import rs.edu.raf.rma.pvukovic16_projekat1.model.Sum;
import rs.edu.raf.rma.pvukovic16_projekat1.model.Category;
import rs.edu.raf.rma.pvukovic16_projekat1.model.Expense;
import rs.edu.raf.rma.pvukovic16_projekat1.util.Util;

public class MainViewModel extends ViewModel {

    MutableLiveData<List<Category>> categories = new MutableLiveData<>();
    List<Category> categoryList = new ArrayList<>();

    MutableLiveData<List<Expense>> expenses = new MutableLiveData<>();
    List<Expense> expenseList = new ArrayList<>();

    MutableLiveData<List<Sum>> sums = new MutableLiveData<>();
    List<Sum> sumList = new ArrayList<>();

    Category catFilter = null;
    String filter = "";

    public LiveData<List<Category>> getCategories() {
        return categories;
    }

    public LiveData<List<Expense>> getExpenses() {
        return expenses;
    }

    public LiveData<List<Sum>> getSums() {
        return sums;
    }

    private void updateSums(Category cat, int diff, int sign) {
        for (Sum s : sumList) {
            if (s.getCategory().getId() == cat.getId()) {
                s.update(diff * sign);
                break;
            }
        }
        Collections.sort(sumList, new Comparator<Sum>() {
            @Override
            public int compare(Sum o1, Sum o2) {
                return o2.getSum() - o1.getSum();
            }
        });
        sums.setValue(sumList);
    }

    public void addExpense(Expense x) {
        expenseList.add(x);
        expenses.setValue(expenseList);
        filterData();
        updateSums(x.getCategory(), x.getCost(), 1);
    }

    public void removeExpense(int id) {
        if (id == -1)
            return;
        for (int i = 0; i < expenseList.size(); ++i) {
            if (expenseList.get(i).getId() == id) {
                updateSums(expenseList.get(i).getCategory(), expenseList.get(i).getCost(), -1);
                expenseList.remove(i);
                break;
            }
        }
        filterData();
    }

    private void filterData() {
        List<Expense> filteredList = new ArrayList<>();
        for (Expense ex : expenseList) {
            if (ex.getName().toLowerCase().startsWith(filter)) {
                if (catFilter == null || ex.getCategory().getName().equals(catFilter.getName())) {
                    filteredList.add(ex);
                }
            }
        }
        expenses.setValue(filteredList);
    }

    public void setFilter(String filter) {
        this.filter = filter.toLowerCase();
        filterData();
    }

    public void setCatFilter(Category catFilter) {
        this.catFilter = catFilter;
        filterData();
    }

    public boolean addCategory(String newCat) {
        if (newCat.isEmpty()) {
            return false;
        }
        for (Category c : categoryList) {
            if (c.getName().equals(newCat)) {
                return false;
            }
        }
        Category newCategory = new Category(Util.generateId(), newCat);
        categoryList.add(newCategory);
        categories.setValue(categoryList);

        sumList.add(new Sum(newCategory));
        sums.setValue(sumList);
        return true;
    }

}
