package smartbudget;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

public class ExpenseManager {
    private final List<Expense> expenses;
    private final DataStore store;
    private int nextId = 1;

    public ExpenseManager(DataStore store) {
        this.store = store;
        this.expenses = store.load();
        for (Expense e : expenses) nextId = Math.max(nextId, e.getId() + 1);
    }

    public Expense add(String title, double amount, Category category,
                       LocalDate date, String note) {
        if (title == null || title.isBlank()) throw new IllegalArgumentException("Title is required.");
        if (amount <= 0) throw new IllegalArgumentException("Amount must be greater than zero.");
        Expense e = new Expense(nextId++, title.trim(), amount, category, date, note);
        expenses.add(e);
        store.save(expenses);
        return e;
    }

    public boolean delete(int id) {
        boolean removed = expenses.removeIf(e -> e.getId() == id);
        if (removed) store.save(expenses);
        return removed;
    }

    public Expense find(int id) {
        return expenses.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    public List<Expense> all() {
        return new ArrayList<>(expenses);
    }

    public List<Expense> byMonth(YearMonth month) {
        return expenses.stream()
                .filter(e -> YearMonth.from(e.getDate()).equals(month))
                .sorted(Comparator.comparing(Expense::getDate).reversed())
                .collect(Collectors.toList());
    }

    public double total(List<Expense> list) {
        return list.stream().mapToDouble(Expense::getAmount).sum();
    }

    public Map<Category, Double> categoryTotals(List<Expense> list) {
        Map<Category, Double> map = new EnumMap<>(Category.class);
        for (Expense e : list)
            map.merge(e.getCategory(), e.getAmount(), Double::sum);
        return map;
    }
}
