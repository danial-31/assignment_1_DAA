public class Mertics {
    private long comparisons;
    private long allocations;
    private long startTime;
    private long endTime;
    private int depth;
    private int maxDepth;

    public void reset() {
        comparisons = 0;
        allocations = 0;
        depth = 0;
        maxDepth = 0;
    }

    public void start() {
        startTime = System.nanoTime();
    }

    public void end() {
        endTime = System.nanoTime();
    }

    public void incComparisons() {
        comparisons++;
    }

    public void incAllocations() {
        allocations++;
    }

    public void enterRecursion() {
        depth++;
        if (depth > maxDepth) maxDepth = depth;
    }

    public void exitRecursion() {
        depth--;
    }

    public long getComparisons() {
        return comparisons;
    }

    public long getAllocations() {
        return allocations;
    }

    public long getTime() {
        return endTime - startTime;
    }

    public int getMaxDepth() {
        return maxDepth;
    }
}
