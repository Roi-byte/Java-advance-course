public class SalariedEmployee extends Employee {
    private double weeklySalary;
    private static final Double MIN_WEEKLY_WAGE = 0.0;
    // Constructor for the SalariedEmployee
    public SalariedEmployee(String firstName, String lastName, String socialSecurityNumber, double weeklySalary, BirthDate date) {
        super(firstName, lastName,socialSecurityNumber, date);
        // Validate that the weekly salary is non-negative
        if(weeklySalary < MIN_WEEKLY_WAGE)
            throw new IllegalArgumentException("Weekly salary must be >= 0.0");

        this.weeklySalary = weeklySalary;
    }
    // Setter method to modify the weekly salary
    public void setWeeklySalary(double weeklySalary) {
        // Validate that the weekly salary is non-negative
        if(weeklySalary < MIN_WEEKLY_WAGE)
            throw new IllegalArgumentException("Weekly salary must be >= 0.0");

        this.weeklySalary = weeklySalary;
    }
    // Getter method to retrieve the weekly salary
    public double getWeeklySalary() {
        return this.weeklySalary;
    }

    @Override
    public double earnings() {
        return getWeeklySalary() + GetBirthdayBonus();
    }

    @Override
    public String toString() {
        return String.format("salaried employee: %s%n%s: $%,.2f", super.toString(), "weekly salary", getWeeklySalary());
    }
}
