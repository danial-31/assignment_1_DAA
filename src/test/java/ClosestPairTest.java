

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClosestPairTest {
    static class Point {
        double x, y;
        Point(double x, double y) { this.x = x; this.y = y; }
    }

    double closestPair(Point[] points) {
        double minDist = Double.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double dist = Math.hypot(points[i].x - points[j].x, points[i].y - points[j].y);
                minDist = Math.min(minDist, dist);
            }
        }
        return minDist;
    }

    @Test
    void testClosestPair() {
        Point[] points = { new Point(0,0), new Point(1,1), new Point(2,2), new Point(5,5) };
        assertEquals(Math.sqrt(2), closestPair(points), 1e-9);
    }
}
