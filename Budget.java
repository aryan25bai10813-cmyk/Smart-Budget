package smartbudget;

public class Budget {
    private double monthlyLimit;

    public Budget(double monthlyLimit) {
        setMonthlyLimit(monthlyLimit);
    }

    public double getMonthlyLimit() { return monthlyLimit; }

    public void setMonthlyLimit(double monthlyLimit) {
        if (monthlyLimit < 0) throw new IllegalArgumentException("Budget cannot be negative.");
        this.monthlyLimit = monthlyLimit;
    }

    public double remaining(double spent) {
        return monthlyLimit - spent;
    }

    public double usagePercent(double spent) {
        if (monthlyLimit == 0) return 0;
        return (spent / monthlyLimit) * 100.0;
    }
}
