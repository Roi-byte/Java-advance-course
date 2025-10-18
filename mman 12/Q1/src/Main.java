import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Get two polynomials from the user input
        Polynom p1 = GetInput();
        Polynom p2 = GetInput();
        //call print method for both polynoms
        print(p1, p2);


    }
    // Method to get polynomial input from the user with some validation, per instruction we can assume that input number is legal
    public static Polynom GetInput() {
        Scanner numsCount = new Scanner(System.in);
        // Ask user to enter the size of the polynomial
        System.out.println("Enter polynom size:");
        int count = numsCount.nextInt();
        numsCount.nextLine(); // throw away the newline.
        // Validate that the size is greater than 0
        if (count < 1){
            System.out.println("Size is to small, size can not be smaller than 1");
            System.exit(1);
        }
        // Arrays to store numbers and powers for the polynomial terms
        double[] num = new double[count];
        System.out.println("Enter polynom numbers:");
        Scanner numScanner = new Scanner(numsCount.nextLine());
        for (int i = 0; i < count; i++) {
            if (numScanner.hasNextInt()) {
                num[i] = numScanner.nextInt();
            } else {
                // If there are not enough numbers provided, print an error and exit
                System.out.println("Not enough numbers provided");
                System.exit(1);
            }
        }
        int[] powres = new int[count];
        System.out.println("Enter polynom powres:");
        Scanner squareScanner = new Scanner(numsCount.nextLine());
        for (int i = 0; i < count; i++) {
            if (squareScanner.hasNextInt()) {
                powres[i] = squareScanner.nextInt();
            } else {
                // If there are not enough powers provided, print an error and exit
                System.out.println("Not enough powres provided");
                System.exit(1);
            }
        }
        // Try to create a new polynomial object from the provided coefficients and powers
        try {
            return new Polynom(num, powres);
        } catch (Exception ex) {
            // If there is an exception during polynomial creation, exit the program
            System.exit(1);
            return null;
        }
    }
    // Method to print the results of all operations between two polynomials per instruction
    public static void print(Polynom p1, Polynom p2)
    {
        System.out.println("The first polynom (name: p1) is  (" + p1 + ")");
        System.out.println("The second polynom (name: p2) is  (" + p2 + ")\n");

        System.out.println("Now we check if p1 and p2 are equals:");
        if (p1.equals(p2)) {
            System.out.println( p1+ " and " +p2 +" are equals\n");
        } else {
            System.out.println( p1+ " and " +p2 +" are  not equals\n");
        }

        System.out.println("Now we check what is the result of p1 plus p2:");
        Polynom p3 = p1.Plus(p2);
        System.out.println("(" + p1 + ") + (" + p2 + ") = " + p3 + "\n");

        System.out.println("Now we check what is the result of p1 minus p2:");
        Polynom p4 = p1.Minus(p2);
        System.out.println("(" + p1 + ") - (" + p2 + ") = " + p4 + "\n");

        System.out.println("Now we check what is the cut of p1:");
        Polynom p5 = p1.Cut();
        System.out.println("Cut of " + p1 + " is: " + p5 + "\n");

        System.out.println("Now check what is the cut of p2:");
        Polynom p6 = p2.Cut();
        System.out.println("Cut of " + p2 + " is: " + p6);
    }
}
