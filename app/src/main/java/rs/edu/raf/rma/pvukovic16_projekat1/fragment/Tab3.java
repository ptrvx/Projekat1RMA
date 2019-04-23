package rs.edu.raf.rma.pvukovic16_projekat1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import rs.edu.raf.rma.pvukovic16_projekat1.adapter.SumAdapter;
import rs.edu.raf.rma.pvukovic16_projekat1.model.Category;
import rs.edu.raf.rma.pvukovic16_projekat1.model.Expense;
import rs.edu.raf.rma.pvukovic16_projekat1.model.Sum;
import rs.edu.raf.rma.pvukovic16_projekat1.viewmodel.MainViewModel;

public class Tab3 extends Fragment {

    private MainViewModel mainViewModel;
    private SumAdapter sumAdapter;
    private List<Sum> sumList = new ArrayList<>();

    private TextView ukupnoTv;

    public static Tab3 newInstance() {
        Tab3 fragment = new Tab3();
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
        View root = inflater.inflate(R.layout.fragment3, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.t3_rv_sum_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(layoutManager);
        sumAdapter = new SumAdapter();

        recyclerView.setAdapter(sumAdapter);

        ukupnoTv = root.findViewById(R.id.t3_tv_ukupno);

        return root;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        mainViewModel.getSums().observe(getViewLifecycleOwner(),
                new Observer<List<Sum>>() {
                    @Override
                    public void onChanged(List<Sum> sums) {
                        sumList = new ArrayList<>(sums);
                        int all = 0;
                        for (Sum s: sumList) {
                            all += s.getSum();
                        }
                        ukupnoTv.setText(getString(R.string.ukupno, all));
                        sumAdapter.setData(sumList);
                    }
                });
    }


}
