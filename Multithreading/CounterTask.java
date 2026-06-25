class CounterTask implements Runnable {
    private final String taskName;

    public CounterTask(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        // The code inside this method executes in a separate thread
        for (int i = 1; i <= 3; i++) {
            System.out.println(taskName + " is executing step: " + i
                    + " [Thread: " + Thread.currentThread().getName() + "]");

            try {
                // Pause the thread for 500 milliseconds to simulate work
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(taskName + " was interrupted.");
            }
        }
        System.out.println(taskName + " has FINISHED.");
    }
}
