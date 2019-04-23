package rs.edu.raf.rma.pvukovic16_projekat1.model;

import rs.edu.raf.rma.pvukovic16_projekat1.util.Util;

public class Sum {

    private int sum;
    private Category category;
    private int id;

    public Sum(Category category) {
        this.category = category;
        this.sum = 0;
        this.id = Util.generateId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void update (int diff) {
        this.sum = this.sum + diff;
        this.id = Util.generateId();
    }

}
