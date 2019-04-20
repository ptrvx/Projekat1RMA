package rs.edu.raf.rma.pvukovic16_projekat1.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import rs.edu.raf.rma.pvukovic16_projekat1.model.Category;
import rs.edu.raf.rma.pvukovic16_projekat1.model.Expense;
import rs.edu.raf.rma.pvukovic16_projekat1.util.Util;

public class MainViewModel extends ViewModel {

    MutableLiveData<List<Category>> categories = new MutableLiveData<>();
    List<Category> categoryList = new ArrayList<>();

    MutableLiveData<List<Expense>> expenses = new MutableLiveData<>();
    List<Expense> expenseList = new ArrayList<>();

    public MainViewModel() {

    }

    public LiveData<List<Category>> getCategories() {
        return categories;
    }

    public LiveData<List<Expense>> getExpenses() {
        return expenses;
    }

    public void addExpense(Expense x) {
        expenseList.add(x);
        expenses.setValue(expenseList);
    }

    public boolean addCategory(String newCat) {
        if (newCat.isEmpty()) { return false; }
        for (Category c : categoryList) {
            if (c.getName().equals(newCat)) {
                return false;
            }
        }
        categoryList.add(0, new Category(Util.generateId(), newCat));
        categories.setValue(categoryList);
        return true;
    }

}
