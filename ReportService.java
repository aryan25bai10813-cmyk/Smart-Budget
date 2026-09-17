package smartbudget;

import java.time.YearMonth;
import java.util.*;

public class ReportService {
    private final ExpenseManager manager;

    public ReportService(ExpenseManager manager) {
        this.manager = manager;
    }

    public void printMonthlyReport(YearMonth month, Budget budget) {
        List<Expense> list = manager.byMonth(month);
        double total = manager.total(list);

        System.out.println("\n===== MONTHLY REPORT: " + month + " =====");
        System.out.printf("Transactions : %d%n", list.size());
        System.out.printf("Total spent  : %.2f%n", total);

        if (budget != null) {
            double remaining = budget.remaining(total);
            System.out.printf("Budget       : %.2f%n", budget.getMonthlyLimit());
            System.out.printf("Remaining    : %.2f%n", remaining);
            System.out.printf("Usage        : %.1f%%%n", budget.usagePercent(total));
            if (remaining < 0)
                System.out.println("WARNING: Monthly budget exceeded.");
        }

        System.out.println("\nCategory breakdown:");
        manager.categoryTotals(list).entrySet().stream()
                .sorted(Map.Entry.<Category, Double>comparingByValue().reversed())
                .forEach(e -> System.out.printf("  %-15s %.2f%n", e.getKey(), e.getValue()));

        if (!list.isEmpty()) {
            Expense largest = Collections.max(list, Comparator.comparingDouble(Expense::getAmount));
            System.out.printf("%nLargest expense: %s (%.2f)%n",
                    largest.getTitle(), largest.getAmount());
        }
    }
}
