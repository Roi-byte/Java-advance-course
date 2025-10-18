
import java.util.Calendar;

public class BirthDate {
    private int day;
    private int month;
    private int year;
    private static final int MIN_DAY = 1;
    private static final int MIN_MONTH = 1;
    private static final int MAX_MONTH = 12;
    private static final int DAYS_IN_SHORT_MONTH = 30;
    private static final int DAYS_IN_LONG_MONTH = 31;
    private static final int DAYS_IN_FEBRUARY_LEAP_YEAR = 29;
    private static final int DAYS_IN_FEBRUARY_NORMAL_YEAR = 28;
    private static final int FEBRUARY = 2;
    private static final int APRIL = 4;
    private static final int JUNE = 6;
    private static final int SEPTEMBER = 9;
    private static final int NOVEMBER =11;

    // Constructor with date validation, including leap year logic
    public BirthDate(int day, int month, int year) {
        Calendar curDate = java.util.Calendar.getInstance(); //checking invalid date including leap year
        if(day < MIN_DAY || month > MAX_MONTH || month < MIN_MONTH || year>curDate.get(Calendar.YEAR) || ((month == APRIL || month == JUNE || month == SEPTEMBER || month == NOVEMBER) && day > DAYS_IN_SHORT_MONTH) ||
                (month == FEBRUARY && day > DAYS_IN_FEBRUARY_LEAP_YEAR && (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))) ||
                (month == FEBRUARY && day > DAYS_IN_FEBRUARY_NORMAL_YEAR && !(((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)))
                || day > DAYS_IN_LONG_MONTH)
            throw new IllegalArgumentException("Invalid date");
        this.day = day;
        this.month = month;
        this.year = year;
    }
    // Getter for the year
    public int GetYear() {
        return this.year;
    }
    // Getter for the month
    public int GetMonth() {
        return this.month;
    }
    // Getter for the day
    public int GetDay() {
        return this.day;
    }

    // Convert birthdate to a formatted string
    public String toString() {
        return String.format("Birthday: %s.%s.%s", this.day, this.month, this.year);
    }
}


