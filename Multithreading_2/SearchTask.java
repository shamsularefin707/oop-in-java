import java.util.concurrent.atomic.AtomicInteger;

class SearchTask implements Runnable {
    private final int[] list;
    private final int target;
    private final int startIndex;
    private final int endIndex;
    private final String threadName;
    private final AtomicInteger foundIndex; // Shared across threads to report success

    public SearchTask(int[] list, int target, int startIndex, int endIndex, String threadName, AtomicInteger foundIndex) {
        this.list = list;
        this.target = target;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.threadName = threadName;
        this.foundIndex = foundIndex;
    }

    @Override
    public void run() {
        System.out.println(threadName + " checking indices " + startIndex + " to " + (endIndex - 1));

        for (int i = startIndex; i < endIndex; i++) {
            // Optimization: If another thread already found it, stop searching
            if (foundIndex.get() != -1) {
                System.out.println(threadName + " stopping early. Target already found by another thread.");
                return;
            }

            if (list[i] == target) {
                System.out.println(threadName + " FOUND the target at index: " + i);
                foundIndex.set(i); // Update the shared result safely
                return;
            }
        }
        System.out.println(threadName + " finished searching without finding the target.");
    }
}
