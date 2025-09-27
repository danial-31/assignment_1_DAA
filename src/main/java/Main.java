import src.main.java.Metrics;

import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        Metrics metrics = new Metrics();
        CSVWriter writer = new CSVWriter("results.csv");
        Random rand = new Random();

        int[] sizes = {100, 1000, 5000, 10000};

        for (int n : sizes) {
            int[] arr1 = randomArray(n, rand);
            int[] arr2 = arr1.clone();
            int[] arr3 = arr1.clone();

            MergeSort.sort(arr1, metrics);
            writer.write("mergesort", n, metrics);

            QuickSort.sort(arr2, metrics);
            writer.write("quicksort", n, metrics);

            int k = n / 2;
            Select.select(arr3, k, metrics);
            writer.write("select", n, metrics);

            ClosestPair.Point[] pts = randomPoints(n, rand);
            ClosestPair.closest(pts, metrics);
            writer.write("closestpair", n, metrics);
        }

        writer.close();
    }

    private static int[] randomArray(int n, Random rand) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = rand.nextInt(100000);
        return a;
    }

    private static ClosestPair.Point[] randomPoints(int n, Random rand) {
        ClosestPair.Point[] pts = new ClosestPair.Point[n];
        for (int i = 0; i < n; i++) {
            double x = rand.nextDouble() * 1000;
            double y = rand.nextDouble() * 1000;
            pts[i] = new ClosestPair.Point(x, y);
        }
        return pts;
    }
}
