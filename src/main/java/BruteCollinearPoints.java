import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author weimin02
 * @date 2018/9/15
 * @project algorithms
 */
public class BruteCollinearPoints {
    private LineSegment[] lineSegments;

    /**
     * finds all line segments containing 4 points
     *
     * @param points
     */
    public BruteCollinearPoints(Point[] points) {
        checkNull(points);
        Point[] copy = points.clone();
        Arrays.sort(copy);
        checkDuplicate(copy);

        List<LineSegment> result = new LinkedList<>();
        for (int a = 0; a < copy.length; a++) {
            Point pointA = copy[a];

            for (int b = a + 1; b < copy.length; b++) {
                Point pointB = copy[b];
                double slopeAB = pointA.slopeTo(pointB);

                for (int c = b + 1; c < copy.length; c++) {
                    Point pointC = copy[c];
                    double slopeAC = pointA.slopeTo(pointC);

                    if (slopeAB == slopeAC) {
                        for (int d = c + 1; d < copy.length; d++) {
                            Point pointD = copy[d];
                            double slopeAD = pointA.slopeTo(pointD);
                            if (slopeAB == slopeAD) {
                                result.add(new LineSegment(pointA, pointD));
                            }
                        }
                    }
                }
            }
        }

        lineSegments = result.toArray(new LineSegment[0]);
    }

    private void checkDuplicate(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                throw new IllegalArgumentException("Array contains duplicate item");
            }
        }
    }

    private void checkNull(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("The array can't be null");
        }

        for (Point point : points) {
            if (point == null) {
                throw new IllegalArgumentException("The array contains null");
            }
        }
    }

    /**
     * the number of line segments
     *
     * @return
     */
    public int numberOfSegments() {
        return lineSegments.length;
    }

    /**
     * the line segments
     *
     * @return
     */
    public LineSegment[] segments() {
        return lineSegments;
    }

}