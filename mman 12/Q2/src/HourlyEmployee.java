public class HourlyEmployee extends Employee {
    private double wage;
    private double hours;
    private static final Double MIN_HOURS = 0.0;
    private static final Double MAX_HOURS_WEEK = 168.0;// 168 = hours in a week
    private static final Double MIN_WAGE = 0.0;
    // Constructor for HourlyEmployee
    public HourlyEmployee(String firstname, String lastName, String socialSecurityNumber,double wage, double hours, BirthDate date) {
        // Call the superclass constructor to initialize common fields
        super(firstname,lastName, socialSecurityNumber,date);
        // Validate that wage is non-negative
        if(wage<MIN_WAGE)
            throw new IllegalArgumentException("Hourly wage must be >= 0.0");
        // Validate that hours worked is between 0 and 168 (maximum hours in a week)
        if(hours<MIN_HOURS || hours>MAX_HOURS_WEEK)
            throw new IllegalArgumentException("Hours worked must be >=0.0 and <=168.0");

        this.wage  = wage;
        this.hours = hours;
    }
    // Setter for wage with validation
    public void setWage(double wage) {
        // Ensure wage is non-negative
        if(wage<MIN_WAGE)
            throw new IllegalArgumentException("Hourly wage must be >= 0.0");

        this.wage=wage;
    }
    // Getter for wage
    public double getWage() {
        return wage;
    }
    // Setter for hours worked with validation
    public void setHours(double hours) {
        if(hours<MIN_HOURS || hours>MAX_HOURS_WEEK)
            throw new IllegalArgumentException("Hours worked must be >=0.0 and <=168.0");

        this.hours = hours;
    }
    // Getter for hours worked
    public double getHours() {
        return hours;
    }

    @Override
    public double earnings() {
        if(getHours() <= 40)
            return getWage() * getHours() + (0.25 * GetBirthdayBonus());//returning weekly so bonus is divided by 4
        else
            return 40 * getWage() + (getHours() - 40) * getWage() *1.5 + (0.25 * GetBirthdayBonus());//returning weekly so bonus is divided by 4
    }

    @Override
    public String toString() {
        return String.format("hourly employee: %s%n%s: $%,.2f; %s: %,.2f",super.toString(), "horly wage",getWage(),"hours worked", getHours());
    }

}
