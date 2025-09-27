import src.main.java.Metrics;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {
    public static double closest(Point[] points, Metrics metrics) {
        metrics.reset();
        metrics.start();
        Point[] sortedX = points.clone();
        Point[] sortedY = points.clone();
        Arrays.sort(sortedX, Comparator.comparingDouble(p -> p.x));
        Arrays.sort(sortedY, Comparator.comparingDouble(p -> p.y));
        double result = closestRecursive(sortedX, sortedY, metrics);
        metrics.end();
        return result;
    }

    private static double closestRecursive(Point[] px, Point[] py, Metrics metrics) {
        int n = px.length;
        if (n <= 3) return bruteForce(px, metrics);

        int mid = n / 2;
        Point midPoint = px[mid];

        Point[] leftX = Arrays.copyOfRange(px, 0, mid);
        Point[] rightX = Arrays.copyOfRange(px, mid, n);

        Point[] leftY = new Point[mid];
        Point[] rightY = new Point[n - mid];
        int li = 0, ri = 0;
        for (Point p : py) {
            if (p.x <= midPoint.x && li < mid) leftY[li++] = p;
            else rightY[ri++] = p;
        }

        metrics.enterRecursion();
        double dl = closestRecursive(leftX, leftY, metrics);
        double dr = closestRecursive(rightX, rightY, metrics);
        metrics.exitRecursion();

        double d = Math.min(dl, dr);

        Point[] strip = new Point[n];
        int j = 0;
        for (Point p : py) {
            if (Math.abs(p.x - midPoint.x) < d) strip[j++] = p;
        }
        return Math.min(d, stripClosest(strip, j, d, metrics));
    }

    private static double bruteForce(Point[] pts, Metrics metrics) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < pts.length; i++) {
            for (int j = i + 1; j < pts.length; j++) {
                metrics.incComparisons();
                double dist = distance(pts[i], pts[j]);
                if (dist < min) min = dist;
            }
        }
        return min;
    }

    private static double stripClosest(Point[] strip, int size, double d, Metrics metrics) {
        double min = d;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size && (strip[j].y - strip[i].y) < min; j++) {
                metrics.incComparisons();
                double dist = distance(strip[i], strip[j]);
                if (dist < min) min = dist;
            }
        }
        return min;
    }

    private static double distance(Point a, Point b) {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static class Point {
        public final double x, y;
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
}

