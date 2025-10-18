import java.util.Calendar;

public abstract class Employee {
    private final String firstName;
    private final String lastName;
    private final String socialSecurityNumber;
    private BirthDate date;
    private static final int BONUS_RATE = 200;

    // Constructor for Employee
    public Employee(String firstname, String lastName, String socialSecurityNumber, BirthDate date) {
        this.firstName = firstname;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
        // Create a deep copy to avoid aliasing
        this.date = new BirthDate(date.GetDay(), date.GetMonth(), date.GetYear());
    }
    // Getter for first name
    public String getFirstName() {
        return firstName;
    }
    // Getter for last name
    public String getLastName() {
        return lastName;
    }
    // Getter for social security number
    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    @Override
    public String toString() {
        return String.format("%s %s%nsocial security number: %s, %s", getFirstName(), getLastName(), getSocialSecurityNumber(), date.toString());
    }
    // Checks if the employee's birth month matches the current month and returns a bonus if it does
    public int GetBirthdayBonus() {
        Calendar curDate = java.util.Calendar.getInstance();
        int month = curDate.get(Calendar.MONTH) + 1;
        int bonus = 0;
        if (month == this.date.GetMonth()) {
            System.out.println("Happy Birthday :) You got bonus! ");
            bonus = BONUS_RATE;
        }
        return bonus;
    }
    // Method to be implemented by subclasses to calculate earnings
    public abstract double earnings();
}

