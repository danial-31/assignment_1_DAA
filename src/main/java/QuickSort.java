import src.main.java.InsertionSort;
import src.main.java.Metrics;

import java.util.Random;

public class QuickSort {
    private static final Random rand = new Random();
    private static final int CUTOFF = 16;

    public static void sort(int[] a, Metrics metrics) {
        metrics.reset();
        metrics.start();
        shuffle(a);
        sort(a, 0, a.length - 1, metrics);
        metrics.end();
    }

    private static void sort(int[] a, int lo, int hi, Metrics metrics) {
        while (lo < hi) {
            if (hi - lo <= CUTOFF) {
                InsertionSort.sort(a, lo, hi);
                return;
            }

            metrics.enterRecursion();
            int pivotIndex = lo + rand.nextInt(hi - lo + 1);
            int pivot = a[pivotIndex];
            swap(a, pivotIndex, hi);
            int p = partition(a, lo, hi, pivot, metrics);
            metrics.exitRecursion();

            if (p - lo < hi - p) {
                sort(a, lo, p - 1, metrics);
                lo = p + 1;
            } else {
                sort(a, p + 1, hi, metrics);
                hi = p - 1;
            }
        }
    }

    private static int partition(int[] a, int lo, int hi, int pivot, Metrics metrics) {
        int i = lo;
        for (int j = lo; j < hi; j++) {
            metrics.incComparisons();
            if (a[j] < pivot) {
                swap(a, i, j);
                i++;
            }
        }
        swap(a, i, hi);
        return i;
    }

    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private static void shuffle(int[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            swap(a, i, j);
        }
    }
}
