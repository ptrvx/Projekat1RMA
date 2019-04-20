package rs.edu.raf.rma.pvukovic16_projekat1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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
import rs.edu.raf.rma.pvukovic16_projekat1.adapter.CategoryAdapter;
import rs.edu.raf.rma.pvukovic16_projekat1.model.Category;
import rs.edu.raf.rma.pvukovic16_projekat1.viewmodel.MainViewModel;

public class Tab4 extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_4";

    private MainViewModel mainViewModel;
    private CategoryAdapter categoryAdapter;
    private List<Category> categoryList;


    public static Tab4 newInstance() {
        Tab4 fragment = new Tab4();
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
        View root = inflater.inflate(R.layout.fragment4, container, false);

        EditText editText = root.findViewById(R.id.t4_et_name);
        Button button = root.findViewById(R.id.t4_btn_add_cat);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newCat = editText.getText().toString();
                if (!newCat.isEmpty()) {
                    if (mainViewModel.addCategory(newCat)) {
                        editText.setText("");
                    }
                }
            }
        });

        RecyclerView recyclerView = root.findViewById(R.id.t4_rv_cat_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter();

        recyclerView.setAdapter(categoryAdapter);

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
                        categoryAdapter.setData(categoryList);
                    }
                });
    }

}
