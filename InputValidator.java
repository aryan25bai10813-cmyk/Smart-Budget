package smartbudget;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputValidator {
    private final Scanner sc;

    public InputValidator(Scanner sc) {
        this.sc = sc;
    }

    public String text(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("Input cannot be empty.");
        }
    }

    public double positiveAmount(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double x = Double.parseDouble(sc.nextLine().trim());
                if (x > 0) return x;
            } catch (NumberFormatException ignored) {}
            System.out.println("Enter a valid positive number.");
        }
    }

    public int integer(String prompt) {
        while (true) {
            System.out.print(prompt);
            try { return Integer.parseInt(sc.nextLine().trim()); }
            catch (NumberFormatException ex) { System.out.println("Enter a valid integer."); }
        }
    }

    public LocalDate date(String prompt) {
        while (true) {
            System.out.print(prompt + " (YYYY-MM-DD): ");
            try { return LocalDate.parse(sc.nextLine().trim()); }
            catch (DateTimeParseException ex) { System.out.println("Invalid date."); }
        }
    }

    public YearMonth month(String prompt) {
        while (true) {
            System.out.print(prompt + " (YYYY-MM): ");
            try { return YearMonth.parse(sc.nextLine().trim()); }
            catch (DateTimeParseException ex) { System.out.println("Invalid month."); }
        }
    }
}
