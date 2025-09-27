public class Metrics {
    private long comparisons;
    private long allocations;
    private int maxDepth;
    private int currentDepth;
    private long startTime;
    private long endTime;

    public void reset() {
        comparisons = 0;
        allocations = 0;
        maxDepth = 0;
        currentDepth = 0;
    }

    public void start() {
        startTime = System.nanoTime();
    }

    public void end() {
        endTime = System.nanoTime();
    }

    public long getTime() {
        return endTime - startTime;
    }

    public void incComparisons() {
        comparisons++;
    }

    public void incAllocations() {
        allocations++;
    }

    public void enterRecursion() {
        currentDepth++;
        if (currentDepth > maxDepth) maxDepth = currentDepth;
    }

    public void exitRecursion() {
        currentDepth--;
    }

    public long getComparisons() {
        return comparisons;
    }

    public long getAllocations() {
        return allocations;
    }

    public int getMaxDepth() {
        return maxDepth;
    }
}
