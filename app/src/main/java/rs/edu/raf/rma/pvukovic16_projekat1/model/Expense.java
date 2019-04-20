package rs.edu.raf.rma.pvukovic16_projekat1.model;

import java.time.Instant;
import java.util.Date;

public class Expense {

    private String name;
    private int cost;
    private Category category;
    private Date date;
    private int id;

    public Expense(int id, String name, int cost, Category category) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.category = category;
        this.date = new Date();
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public Category getCategory() {
        return category;
    }
}