package rs.edu.raf.rma.pvukovic16_projekat1.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import rs.edu.raf.rma.pvukovic16_projekat1.adapter.CategoryAdapter;
import rs.edu.raf.rma.pvukovic16_projekat1.model.Category;
import rs.edu.raf.rma.pvukovic16_projekat1.viewmodel.MainViewModel;

public class Tab3 extends Fragment {

    private MainViewModel mainViewModel;
    private CategoryAdapter categoryAdapter;
    private List<Category> categoryList;


    public static Tab3 newInstance() {
        Tab3 fragment = new Tab3();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);


    }


}
