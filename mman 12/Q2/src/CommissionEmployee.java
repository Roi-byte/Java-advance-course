public class CommissionEmployee extends Employee {
    private double grossSales; // total sales made by the employee
    private double commissionRate; // percentage of sales paid as commission
    private static final Double MIN_COMMISSION_RATE = 0.0; // Validate commission rate: must be between 0.0 and 1.0
    private static final Double MAX_COMMISSION_RATE = 1.0;
    // Constructor for CommissionEmployee
    public CommissionEmployee(String firstname, String lastName, String socialSecurityNumber, double grossSales, double commissionRate, BirthDate date) {
        super(firstname, lastName, socialSecurityNumber, date);
        // Validate commission rate: must be between 0.0 and 1.0
        if(commissionRate <= MIN_COMMISSION_RATE || commissionRate >= MAX_COMMISSION_RATE)
            throw new IllegalArgumentException("Commisiion rate must be >0.0 and <1.0");
        // Validate gross sales: cannot be negative
        if(grossSales < MIN_COMMISSION_RATE)
            throw new IllegalArgumentException("Gross sales must be >=0.0");

        this.grossSales = grossSales;
        this.commissionRate = commissionRate;
    }
    // Setter for gross sales with validation
    public void setGrossSales(double grossSales) {
        if(grossSales < MIN_COMMISSION_RATE)
            throw new IllegalArgumentException("Gross sales must be >=0.0");

        this.grossSales = grossSales;
    }
    // Getter for gross sales
    public double getGrossSales() {
        return grossSales;
    }
    // Setter for commission rate with validation
    public void setCommissionRate(double commissionRate) {
        if(commissionRate <= MIN_COMMISSION_RATE || commissionRate >= MAX_COMMISSION_RATE)
            throw new IllegalArgumentException("Commisiion rate must be >0.0 and <1.0");

        this.commissionRate = commissionRate;
    }
    // Getter for commission rate
    public double getcommissionRate() {
        return commissionRate;
    }
    // Calculate earnings: commission * sales + optional birthday bonus
    @Override
    public double earnings() {
        return getcommissionRate() * getGrossSales() + GetBirthdayBonus();
    }

    @Override
    public String toString() {
        return String.format("%s: %s%n%s: $%,.2f; %s: %.2f", "commission employee",super.toString(),"gross sales",getGrossSales(),
                "commission rate",getcommissionRate());
    }

}
