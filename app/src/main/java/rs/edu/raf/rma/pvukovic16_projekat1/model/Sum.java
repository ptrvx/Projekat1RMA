package rs.edu.raf.rma.pvukovic16_projekat1.model;

public class Sum {

    private int sum;
    private Category category;

    public Sum(Category category) {
        this.category = category;
        this.sum = 0;
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
    }

}
