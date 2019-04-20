package rs.edu.raf.rma.pvukovic16_projekat1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import rs.edu.raf.rma.pvukovic16_projekat1.R;
import rs.edu.raf.rma.pvukovic16_projekat1.model.Category;
import rs.edu.raf.rma.pvukovic16_projekat1.util.CategoryDiffCallback;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {

    private List<Category> categoryList;

    public CategoryAdapter() {
        categoryList = new ArrayList<>();
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cat_list_item, parent, false);
        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.text.setText(category.getName());
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public void setData(List<Category> categories) {
        CategoryDiffCallback callback = new CategoryDiffCallback(categoryList, categories);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        categoryList.clear();
        categoryList.addAll(categories);
        result.dispatchUpdatesTo(this);
    }


    public class CategoryHolder extends RecyclerView.ViewHolder {
        TextView text;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.t4_tv_cat_item);
        }
    }
}
