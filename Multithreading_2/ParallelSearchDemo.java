import java.util.concurrent.atomic.AtomicInteger;

public class ParallelSearchDemo {
    public static void main(String[] args) {
        // 1. Create a mock list of data
        int[] dataList = {12, 45, 78, 92, 56, 34, 88, 23, 11, 67, 99, 41};
        int targetValue = 23; // The number we want to find

        // Use AtomicInteger to pass a thread-safe reference for the result
        // -1 means "not found yet"
        AtomicInteger foundIndex = new AtomicInteger(-1);

        int midpoint = dataList.length / 2;

        // 2. Set up two tasks splitting the workload in half
        Runnable firstHalfSearch = new SearchTask(dataList, targetValue, 0, midpoint, "Thread-Left", foundIndex);
        Runnable secondHalfSearch = new SearchTask(dataList, targetValue, midpoint, dataList.length, "Thread-Right", foundIndex);

        // 3. Wrap tasks in Thread objects
        Thread thread1 = new Thread(firstHalfSearch);
        Thread thread2 = new Thread(secondHalfSearch);

        System.out.println("Starting parallel search for value: " + targetValue + "...\n");

        // 4. Fire them up concurrently
        thread1.start();
        thread2.start();

        // 5. Wait for both threads to finish execution
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 6. Print the final outcome
        System.out.println("\n--- Search Completed ---");
        if (foundIndex.get() != -1) {
            System.out.println("Target " + targetValue + " successfully located at index: " + foundIndex.get());
        } else {
            System.out.println("Target " + targetValue + " was not found in the list.");
        }
    }
}
