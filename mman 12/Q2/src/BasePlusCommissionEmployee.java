public class BasePlusCommissionEmployee extends CommissionEmployee {
    private double baseSalary; //base salary
    private static final double MIN_SALARY = 0.0;
    public BasePlusCommissionEmployee(String firstname, String lastName, String socialSecurityNumber, double grossSales, double commissionRate
            ,double baseSalary, BirthDate date) {
        // Call superclass constructor to set common employee details
        super(firstname, lastName, socialSecurityNumber,grossSales, commissionRate, date);
        // Validate base salary
        if(baseSalary < MIN_SALARY)
            throw new IllegalArgumentException("Base salary must be >= 0.0");
        this.baseSalary = baseSalary;
    }
    // Setter for base salary with validation
    public void setBaseSalary(double baseSalary) {
        // Validate base salary
        if(baseSalary < MIN_SALARY)
            throw new IllegalArgumentException("Base salary must be >= 0.0");

        this.baseSalary = baseSalary;
    }
    // Getter for base salary
    public double getBaseSalary() {
        return baseSalary;
    }

    @Override
    public double earnings() {
        return getBaseSalary() + super.earnings();
    }

    @Override
    public String toString() {
        return String.format("%s %s; %s: $%,.2f", "base-salaried",super.toString(),"base salary",getBaseSalary());
    }

}
