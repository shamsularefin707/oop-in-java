import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class MassiveParallelSearch {
    public static void main(String[] args) {
        // 1. Setup Constants
        final int LIST_SIZE = 50_000_000; // Boosted size to make execution time visible
        final int NUM_THREADS = 4;
        final int TARGET_VALUE = 99999;

        int[] massiveList = new int[LIST_SIZE];
        Random random = new Random();

        System.out.println("Generating " + LIST_SIZE + " random elements...");
        for (int i = 0; i < LIST_SIZE; i++) {
            massiveList[i] = random.nextInt(100_000_000) + 1;
        }

        // Inject target close to the end to force threads to do actual work
        massiveList[LIST_SIZE - 500] = TARGET_VALUE;

        AtomicInteger finalResultIndex = new AtomicInteger(-1);
        Thread[] workers = new Thread[NUM_THREADS];
        int chunkSize = LIST_SIZE / NUM_THREADS;

        System.out.println("Deploying " + NUM_THREADS + " threads...");

        // ========================================================
        // START TIMER HERE
        // ========================================================
        long startTime = System.nanoTime();

        for (int i = 0; i < NUM_THREADS; i++) {
            int startRange = i * chunkSize;
            int endRange = (i == NUM_THREADS - 1) ? LIST_SIZE : (startRange + chunkSize);

            String workerName = "SearchThread-" + (i + 1);
            ListSearcher task = new ListSearcher(massiveList, TARGET_VALUE, startRange, endRange, workerName, finalResultIndex);

            workers[i] = new Thread(task);
            workers[i].start();
        }

        // Wait for all workers to cross the finish line
        try {
            for (Thread worker : workers) {
                worker.join();
            }
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted.");
        }

        // ========================================================
        // STOP TIMER HERE (Right after all threads join back)
        // ========================================================
        long endTime = System.nanoTime();

        // Calculate differences ($1 ms = 1,000,000 ns$)
        double durationInMilliseconds = (endTime - startTime) / 1_000_000.0;

        // Print Results
        System.out.println("\n--- Performance Metrics ---");
        int finalOutcome = finalResultIndex.get();
        if (finalOutcome != -1) {
            System.out.println("Target found at index: " + finalOutcome);
        } else {
            System.out.println("Target not found.");
        }

        System.out.printf("Total Thread Execution Time: %.2f ms%n", durationInMilliseconds);
    }
}
