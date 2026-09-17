package smartbudget;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

public class SmartBudgetApp {
    private static final Scanner SC = new Scanner(System.in);
    private static final InputValidator INPUT = new InputValidator(SC);
    private static final ExpenseManager MANAGER =
            new ExpenseManager(new DataStore("data/expenses.txt"));
    private static Budget budget = new Budget(10000);

    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("       SMARTBUDGET - JAVA APP");
        System.out.println("======================================");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = INPUT.integer("Choose an option: ");
            try {
                switch (choice) {
                    case 1 -> addExpense();
                    case 2 -> viewExpenses();
                    case 3 -> deleteExpense();
                    case 4 -> setBudget();
                    case 5 -> report();
                    case 6 -> search();
                    case 0 -> running = false;
                    default -> System.out.println("Invalid option.");
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        System.out.println("Thank you for using SmartBudget.");
    }

    private static void printMenu() {
        System.out.println("\n1. Add Expense");
        System.out.println("2. View Expenses");
        System.out.println("3. Delete Expense");
        System.out.println("4. Set Monthly Budget");
        System.out.println("5. Monthly Report");
        System.out.println("6. Search by Month");
        System.out.println("0. Exit");
    }

    private static void addExpense() {
        String title = INPUT.text("Title: ");
        double amount = INPUT.positiveAmount("Amount: ");
        Category category = chooseCategory();
        LocalDate date = INPUT.date("Date");
        System.out.print("Note (optional): ");
        String note = SC.nextLine().trim();
        Expense e = MANAGER.add(title, amount, category, date, note);
        System.out.println("Expense added with ID " + e.getId());
    }

    private static Category chooseCategory() {
        Category[] values = Category.values();
        System.out.println("Categories:");
        for (int i = 0; i < values.length; i++)
            System.out.println((i + 1) + ". " + values[i]);
        int n = INPUT.integer("Category: ");
        if (n < 1 || n > values.length) throw new IllegalArgumentException("Invalid category.");
        return values[n - 1];
    }

    private static void viewExpenses() {
        List<Expense> list = MANAGER.all();
        if (list.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }
        System.out.printf("%-4s %-22s %10s %-15s %-12s %s%n",
                "ID", "TITLE", "AMOUNT", "CATEGORY", "DATE", "NOTE");
        for (Expense e : list) System.out.println(e);
        System.out.printf("%nTotal: %.2f%n", MANAGER.total(list));
    }

    private static void deleteExpense() {
        int id = INPUT.integer("Expense ID to delete: ");
        System.out.println(MANAGER.delete(id) ? "Expense deleted." : "Expense not found.");
    }

    private static void setBudget() {
        double value = INPUT.positiveAmount("Monthly budget: ");
        budget.setMonthlyLimit(value);
        System.out.printf("Budget set to %.2f%n", value);
    }

    private static void report() {
        YearMonth month = INPUT.month("Report month");
        new ReportService(MANAGER).printMonthlyReport(month, budget);
    }

    private static void search() {
        YearMonth month = INPUT.month("Search month");
        List<Expense> list = MANAGER.byMonth(month);
        if (list.isEmpty()) {
            System.out.println("No expenses found for " + month);
            return;
        }
        for (Expense e : list) System.out.println(e);
        System.out.printf("Month total: %.2f%n", MANAGER.total(list));
    }
}
