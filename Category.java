package smartbudget;

public enum Category {
    FOOD, TRANSPORT, EDUCATION, ENTERTAINMENT, SHOPPING, BILLS, HEALTH, OTHER;

    public static Category fromNumber(int n) {
        Category[] values = values();
        if (n < 1 || n > values.length) return OTHER;
        return values[n - 1];
    }
}
