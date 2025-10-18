import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
// Main method that starts the UI interaction by asking the user to enter the array size and process size
public class Main {

    public static void main(String[] args) {
        int n, m, result;
        Scanner scan = new Scanner (System.in);
        ArrayList<Integer> n_array = new ArrayList<>();
        while (true) {
            System.out.println("Please enter size of array n:");
            n = scan.nextInt();
            if (n > 0)
                break;
            System.out.println("Array cannot be size 0 or lower :( \nPlease reenter a new size of an array");
        }
        for (int i = 0; i < n; i++) {//Filling arraylist randomly
            n_array.add(random());
        }
        while (true) {
        System.out.println("Please enter size of process:");
        m = scan.nextInt();
            if (m > 0)
                break;
            System.out.println("Process cannot be size 0 or lower :( \nPlease reenter the size of Process");
        }
        System.out.println("Values accepted! Array size: " + n + ", Process size: " + m);
        System.out.println("All elements: " + n_array);
        scan.close();
        ProcessManager manager = new ProcessManager();
        result = manager.processStarter(m, n_array);
        System.out.println("Final result: " + result + "\nI hope you like the program :)");
    }

    //A method to return a random int
    public static int random(){
        Random rand = new Random();
        return rand.nextInt(100) + 1;
    }

}


