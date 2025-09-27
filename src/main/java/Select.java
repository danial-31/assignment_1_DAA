import src.main.java.Metrics;

public class Select {
    public static int select(int[] a, int k, Metrics metrics) {
        metrics.reset();
        metrics.start();
        int res = select(a, 0, a.length - 1, k, metrics);
        metrics.end();
        return res;
    }

    private static int select(int[] a, int lo, int hi, int k, Metrics metrics) {
        while (lo <= hi) {
            if (lo == hi) return a[lo];
            int pivot = medianOfMedians(a, lo, hi, metrics);
            int pivotIndex = partition(a, lo, hi, pivot, metrics);
            int rank = pivotIndex - lo;
            if (k == rank) return a[pivotIndex];
            else if (k < rank) hi = pivotIndex - 1;
            else {
                k = k - rank - 1;
                lo = pivotIndex + 1;
            }
        }
        return -1;
    }

    private static int partition(int[] a, int lo, int hi, int pivot, Metrics metrics) {
        int i = lo;
        int j = hi;
        while (i <= j) {
            while (i <= hi) {
                metrics.incComparisons();
                if (a[i] >= pivot) break;
                i++;
            }
            while (j >= lo) {
                metrics.incComparisons();
                if (a[j] <= pivot) break;
                j--;
            }
            if (i <= j) {
                swap(a, i, j);
                i++;
                j--;
            }
        }
        return i - 1;
    }

    private static int medianOfMedians(int[] a, int lo, int hi, Metrics metrics) {
        int n = hi - lo + 1;
        if (n < 5) {
            insertionSort(a, lo, hi, metrics);
            return a[lo + n / 2];
        }
        int numMedians = 0;
        for (int i = lo; i <= hi; i += 5) {
            int subHi = Math.min(i + 4, hi);
            insertionSort(a, i, subHi, metrics);
            int median = a[i + (subHi - i) / 2];
            a[lo + numMedians] = median;
            numMedians++;
        }
        return medianOfMedians(a, lo, lo + numMedians - 1, metrics);
    }

    private static void insertionSort(int[] a, int lo, int hi, Metrics metrics) {
        for (int i = lo + 1; i <= hi; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= lo) {
                metrics.incComparisons();
                if (a[j] > key) {
                    a[j + 1] = a[j];
                    j--;
                } else break;
            }
            a[j + 1] = key;
        }
    }

    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
