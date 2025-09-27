public class MergeSort {
    private static final int CUTOFF = 16;

    public static void sort(int[] a, Mertics metrics) {
        metrics.reset();
        metrics.start();
        int[] aux = new int[a.length];
        metrics.incAllocations();
        sort(a, aux, 0, a.length - 1, metrics);
        metrics.end();
    }

    private static void sort(int[] a, int[] aux, int lo, int hi, Mertics metrics) {
        if (hi - lo <= CUTOFF) {
            InsertionSort.sort(a, lo, hi);
            return;
        }
        metrics.enterRecursion();
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid, metrics);
        sort(a, aux, mid + 1, hi, metrics);
        merge(a, aux, lo, mid, hi, metrics);
        metrics.exitRecursion();
    }

    private static void merge(int[] a, int[] aux, int lo, int mid, int hi, Mertics metrics) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
            metrics.incAllocations();
        }
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else {
                metrics.incComparisons();
                if (aux[j] < aux[i]) a[k] = aux[j++];
                else a[k] = aux[i++];
            }
        }
    }
}
12
