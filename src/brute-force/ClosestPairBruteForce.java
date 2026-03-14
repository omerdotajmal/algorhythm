
public class ClosestPairBruteForce {

    static class Point {
        final double x, y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Result {
        final int i, j;
        final double dist;

        Result(int i, int j, double dist) {
            this.i = i;
            this.j = j;
            this.dist = dist;
        }

        public String toString() {
            return "Pair=(" + i + "," + j + "), dist=" + dist;
        }

        public static Result closestPair(Point[] pts) {
            int n = pts.length;
            if (n < 2)
                throw new IllegalArgumentException("Need at least 2 points.");

            int bestI = 0, bestJ = 1;
            double best = distance(pts[0], pts[1]);

            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    double d = distance(pts[i], pts[j]);
                    if (d < best) {
                        best = d;
                        bestI = i;
                        bestJ = j;
                    }
                }
            }

            return new Result(bestI, bestJ, best);
        }

        private static double distance(Point a, Point b) {
            double dx = a.x - b.x;
            double dy = a.y - b.y;
            return Math.sqrt(dx * dx + dy * dy);
        }
    }

    public static void main(String[] args) {
        Point[] pts = {
                new Point(0, 0), new Point(2, 2), new Point(3, 1), new Point(5, 4) };

        System.out.println(Result.closestPair(pts));

    }

}