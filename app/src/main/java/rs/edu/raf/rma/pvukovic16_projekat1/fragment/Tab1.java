package rs.edu.raf.rma.pvukovic16_projekat1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import rs.edu.raf.rma.pvukovic16_projekat1.R;
import rs.edu.raf.rma.pvukovic16_projekat1.adapter.CategoryAdapter;
import rs.edu.raf.rma.pvukovic16_projekat1.model.Category;
import rs.edu.raf.rma.pvukovic16_projekat1.model.Expense;
import rs.edu.raf.rma.pvukovic16_projekat1.util.Util;
import rs.edu.raf.rma.pvukovic16_projekat1.viewmodel.MainViewModel;

public class Tab1 extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_1";

    private MainViewModel mainViewModel;
    private List<Category> categoryList = new ArrayList<>();
    private ArrayAdapter<Category> arrayAdapter;

    public static Tab1 newInstance() {
        Tab1 fragment = new Tab1();
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
        View root = inflater.inflate(R.layout.fragment1, container, false);

        EditText nameText = root.findViewById(R.id.t1_et_name);
        EditText costText = root.findViewById(R.id.t1_et_cost);
        Button button = root.findViewById(R.id.t1_btn_add);
        Spinner spinner = root.findViewById(R.id.t1_spinner);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                String cost = costText.getText().toString();
                Category cat = (Category)spinner.getSelectedItem();

                if (!name.equals("") && !cost.equals("") && cat != null) {
                    mainViewModel.addExpense(new Expense(Util.generateId(), name, Integer.parseInt(cost), cat));
                }

            }
        });

        arrayAdapter = new ArrayAdapter<>(root.getContext(), android.R.layout.simple_list_item_1, categoryList);
        spinner.setAdapter(arrayAdapter);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        mainViewModel.getCategories().observe(getViewLifecycleOwner(),
                new Observer<List<Category>>() {
                    @Override
                    public void onChanged(List<Category> categories) {
                        categoryList = new ArrayList<>(categories);
                        arrayAdapter.clear();
                        arrayAdapter.addAll(categoryList);
                    }
                });
    }

}
