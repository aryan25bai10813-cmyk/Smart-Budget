package smartbudget;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Expense {
    private int id;
    private String title;
    private double amount;
    private Category category;
    private LocalDate date;
    private String note;

    public Expense(int id, String title, double amount, Category category,
                   LocalDate date, String note) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.note = note == null ? "" : note;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public double getAmount() { return amount; }
    public Category getCategory() { return category; }
    public LocalDate getDate() { return date; }
    public String getNote() { return note; }

    public void setTitle(String title) { this.title = title; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setCategory(Category category) { this.category = category; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setNote(String note) { this.note = note == null ? "" : note; }

    public String toCsv() {
        return id + "|" + clean(title) + "|" + amount + "|" + category + "|" + date + "|" + clean(note);
    }

    private String clean(String s) {
        return s.replace("|", "/").replace("\n", " ");
    }

    public static Expense fromCsv(String line) {
        String[] p = line.split("\\|", -1);
        if (p.length != 6) throw new IllegalArgumentException("Invalid record");
        return new Expense(Integer.parseInt(p[0]), p[1], Double.parseDouble(p[2]),
                Category.valueOf(p[3]), LocalDate.parse(p[4]), p[5]);
    }

    @Override
    public String toString() {
        return String.format("%-4d %-22s %10.2f %-15s %-12s %s",
                id, title, amount, category, date.format(DateTimeFormatter.ISO_DATE), note);
    }
}
