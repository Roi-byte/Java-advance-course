//Hi, most of the methods are according to the book, but I did still add comments just in case
//Thank you for reading and have fun testing :)
public class Main {
    public static void main(String[] args) {
        // Create different BirthDate objects for each employee
        BirthDate Johndate = new BirthDate(1, 1, 1990);
        BirthDate Karendate = new BirthDate(8, 3, 1995);
        BirthDate Suedate = new BirthDate(2, 12, 1996);
        BirthDate Bobdate = new BirthDate(10, 12, 1995);
        BirthDate Mayadate = new BirthDate(17, 11, 2000);

        // Create instances of different employee types with different data mostly according to the book
        SalariedEmployee salariedEmployee = new SalariedEmployee("John", "Smith", "111-11-1111", 800.00, Johndate);
        HourlyEmployee hourlyEmployee = new HourlyEmployee("Karen", "Price", "222-22-2222", 16.75, 40, Karendate);
        CommissionEmployee commissionEmployee = new CommissionEmployee("Sue", "Jones", "333-33-3333", 10000, .06, Suedate);
        BasePlusCommissionEmployee basePlusCommissionEmployee = new BasePlusCommissionEmployee("Bob", "Lewis", "444-44-4444", 5000, .04, 300, Bobdate);
        PieceWorker pieceWorkerEmployee = new PieceWorker("Maya", "Tal", "555-55-5555", Mayadate, 17);


        // Create an array of Employee objects to store different types of employees also mostly according to the book
        Employee[] employees = new Employee[5];
        employees[0] = salariedEmployee;
        employees[1] = hourlyEmployee;
        employees[2] = commissionEmployee;
        employees[3] = basePlusCommissionEmployee;
        employees[4] = pieceWorkerEmployee;

        // Print a header to indicate polymorphic processing of employees
        System.out.printf("Employees processed polymorphicaly: %n%n");
        // Loop through each employee and process them
        for (Employee currentEmployee : employees) {
            System.out.println(currentEmployee);

            if (currentEmployee instanceof BasePlusCommissionEmployee) {
                BasePlusCommissionEmployee employee = (BasePlusCommissionEmployee) currentEmployee;
                employee.setBaseSalary(1.10 * employee.getBaseSalary());
                System.out.printf("new base salary with 10%% increase is: $%,.2f%n", employee.getBaseSalary());
            }

            System.out.printf("earned $%,.2f%n%n", currentEmployee.earnings());
        }
        for (int j = 0; j < employees.length; j++)
            System.out.printf("Employee %d is %s%n", j, employees[j].getClass().getName());
    }
}