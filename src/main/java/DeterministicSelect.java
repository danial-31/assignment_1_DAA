import src.main.java.Metrics;

import java.util.Arrays;

public class DeterministicSelect {
    public static int select(int[] a, int k, Metrics metrics) {
        metrics.reset();
        metrics.start();
        int result = select(a, 0, a.length - 1, k, metrics);
        metrics.end();
        return result;
    }

    private static int select(int[] a, int lo, int hi, int k, Metrics metrics) {
        if (lo == hi) return a[lo];

        int pivot = medianOfMedians(a, lo, hi, metrics);
        int pivotIndex = partition(a, lo, hi, pivot, metrics);

        if (k == pivotIndex) return a[k];
        else if (k < pivotIndex) return select(a, lo, pivotIndex - 1, k, metrics);
        else return select(a, pivotIndex + 1, hi, k, metrics);
    }

    private static int partition(int[] a, int lo, int hi, int pivot, Metrics metrics) {
        int i = lo;
        for (int j = lo; j <= hi; j++) {
            metrics.incComparisons();
            if (a[j] < pivot) {
                swap(a, i, j);
                i++;
            }
        }
        int pivotIndex = i;
        for (int j = i; j <= hi; j++) {
            metrics.incComparisons();
            if (a[j] == pivot) {
                swap(a, j, hi);
                swap(a, i, hi);
                break;
            }
        }
        return pivotIndex;
    }

    private static int medianOfMedians(int[] a, int lo, int hi, Metrics metrics) {
        int n = hi - lo + 1;
        if (n < 5) {
            Arrays.sort(a, lo, hi + 1);
            return a[lo + n / 2];
        }
        int numMedians = (int) Math.ceil(n / 5.0);
        int[] medians = new int[numMedians];
        for (int i = 0; i < numMedians; i++) {
            int subLo = lo + i * 5;
            int subHi = Math.min(subLo + 4, hi);
            Arrays.sort(a, subLo, subHi + 1);
            medians[i] = a[subLo + (subHi - subLo) / 2];
        }
        return medianOfMedians(medians, 0, medians.length - 1, metrics);
    }

    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
