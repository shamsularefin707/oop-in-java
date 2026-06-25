// 2. Main class to manage and start the threads
public class MultithreadingDemo {
    public static void main(String[] args) {
        System.out.println("Main thread STARTING. [Thread: " + Thread.currentThread().getName() + "]\n");

        // Create the tasks
        Runnable task1 = new CounterTask("Task-A");
        Runnable task2 = new CounterTask("Task-B");

        // Pass the tasks into Thread objects
        Thread thread1 = new Thread(task1, "Worker-1");
        Thread thread2 = new Thread(task2, "Worker-2");

        // Start the threads (This invokes the run() method asynchronously)
        thread1.start();
        thread2.start();

        // Wait for the threads to finish before closing the main thread
        try {
            thread1.join(); // Main thread pauses until thread1 finishes
            thread2.join(); // Main thread pauses until thread2 finishes
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nMain thread ENDING. All worker threads are done.");
    }
}