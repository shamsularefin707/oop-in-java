import java.util.concurrent.atomic.AtomicInteger;

class ListSearcher implements Runnable {
    private final int[] dataset;
    private final int target;
    private final int start;
    private final int end;
    private final String threadName;
    private final AtomicInteger sharedResultIndex;

    public ListSearcher(int[] dataset, int target, int start, int end, String threadName, AtomicInteger sharedResultIndex) {
        this.dataset = dataset;
        this.target = target;
        this.start = start;
        this.end = end;
        this.threadName = threadName;
        this.sharedResultIndex = sharedResultIndex;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            if (sharedResultIndex.get() != -1) {
                return; // Early termination if already found
            }

            if (dataset[i] == target) {
                sharedResultIndex.compareAndSet(-1, i);
                return;
            }
        }
    }
}
