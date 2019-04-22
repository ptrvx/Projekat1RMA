package rs.edu.raf.rma.pvukovic16_projekat1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import rs.edu.raf.rma.pvukovic16_projekat1.R;
import rs.edu.raf.rma.pvukovic16_projekat1.activity.DetailsActivity;
import rs.edu.raf.rma.pvukovic16_projekat1.adapter.CategoryAdapter;
import rs.edu.raf.rma.pvukovic16_projekat1.adapter.ExpenseAdapter;
import rs.edu.raf.rma.pvukovic16_projekat1.model.Category;
import rs.edu.raf.rma.pvukovic16_projekat1.model.Expense;
import rs.edu.raf.rma.pvukovic16_projekat1.viewmodel.MainViewModel;

public class Tab2 extends Fragment {

    private MainViewModel mainViewModel;
    private ExpenseAdapter expenseAdapter;
    private List<Expense> expenseList;

    private ArrayAdapter<Category> arrayAdapter;
    private List<Category> categoryList = new ArrayList<>();

    private static final int DETAILS_ACTIVITY_REQUEST_CODE = 0;

    public static Tab2 newInstance() {
        Tab2 fragment = new Tab2();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment2, container, false);

        EditText searchText = root.findViewById(R.id.t2_et_search);
        Spinner spinner = root.findViewById(R.id.t2_spinner);
        Button apply = root.findViewById(R.id.t2_btn_apply);

        arrayAdapter = new ArrayAdapter<>(root.getContext(), android.R.layout.simple_list_item_1, categoryList);
        spinner.setAdapter(arrayAdapter);

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String filter = searchText.getText().toString();
                mainViewModel.setFilter(filter);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category cat = (Category)spinner.getSelectedItem();
                mainViewModel.setCatFilter(cat);
            }
        });

        RecyclerView recyclerView = root.findViewById(R.id.t2_rv_exp_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(layoutManager);
        expenseAdapter = new ExpenseAdapter();

        expenseAdapter.setOnItemRemoveCallback(new ExpenseAdapter.OnItemRemoveCallback() {
            @Override
            public void onItemRemove(int id) {
                mainViewModel.removeExpense(id);
            }
        });

        expenseAdapter.setOnImageClickCallback(new ExpenseAdapter.OnImageClickCallback() {
            @Override
            public void onImageClick(int position) {
                Expense expense = expenseList.get(position);

                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("expenseId", expense.getId());
                intent.putExtra("expenseName", expense.getName());
                intent.putExtra("expenseCost", expense.getCost());
                intent.putExtra("expenseDate", expense.getDate());
                intent.putExtra("expenseCategory", expense.getCategory().getName());

                startActivityForResult(intent, DETAILS_ACTIVITY_REQUEST_CODE);
            }
        });

        recyclerView.setAdapter(expenseAdapter);

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == DETAILS_ACTIVITY_REQUEST_CODE) {
            if (resultCode == 1) {
                mainViewModel.removeExpense(data.getIntExtra("expenseId", -1));
            }
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        mainViewModel.getExpenses().observe(getViewLifecycleOwner(),
                new Observer<List<Expense>>() {
                    @Override
                    public void onChanged(List<Expense> expenses) {
                        expenseList = new ArrayList<>(expenses);
                        expenseAdapter.setData(expenseList);
                    }
                });

        mainViewModel.getCategories().observe(getViewLifecycleOwner(), new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                categoryList = new ArrayList<>(categories);
                arrayAdapter.clear();
                arrayAdapter.addAll(categoryList);
            }
        });
    }

}
