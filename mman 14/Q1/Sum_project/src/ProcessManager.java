import java.util.*;
import java.util.ArrayList;
import java.util.concurrent.*;

public class ProcessManager {

    public int process(ArrayList<Integer> array) {
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        while (array.size() > 1) {
            List<Integer> arraypro = Collections.synchronizedList(new ArrayList<>());
            List<Future<?>> tasks = new ArrayList<>();

            for (int i = 0; i < array.size() - 1; i += 2) {
                final int a = array.get(i);
                final int b = array.get(i + 1);

                Future<?> task = executor.submit(() -> {
                    int result = sum(a, b);
                    arraypro.add(result);
                });

                tasks.add(task);
            }

            // If array has an odd number of elements, carry the last one over
            if (array.size() % 2 != 0) {
                arraypro.add(array.get(array.size() - 1));
            }

            // Wait for all threads to finish
            for (Future<?> task : tasks) {
                try {
                    task.get();
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                    executor.shutdownNow(); // Clean shutdown if error occurs
                    return -1; //Indicate error
                }
            }

            // Replace original array with result for next round
            array.clear();
            array.addAll(arraypro);

            System.out.println("Next round array: " + array);
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Final result
        return array.get(0);
    }
    //A method to sum numbers
    public synchronized int sum(int numOne, int numTwo) {
        int sum;
        sum = numOne + numTwo;
        return sum;
    }
    //A method to start the process
    public int processStarter(int action, ArrayList<Integer> array) {
        ArrayList<Integer> arraySub = new ArrayList<>();
        for (int i = 0; (i < (action * 2) && i < array.size()); i++) {// Action * 2 - because action use two integers
            arraySub.add(array.get(i));
        }
        return process(arraySub);
    }
}



