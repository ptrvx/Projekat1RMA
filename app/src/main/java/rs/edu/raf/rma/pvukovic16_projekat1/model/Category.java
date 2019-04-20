package rs.edu.raf.rma.pvukovic16_projekat1.model;

import androidx.annotation.NonNull;

public class Category {

    private int mId;
    private String mName;

    public Category(int id, String name) {
        this.mId = id;
        this.mName = name;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    @NonNull
    @Override
    public String toString() {
        return getName();
    }
}
