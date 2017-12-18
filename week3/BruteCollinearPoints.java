import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;

public class BruteCollinearPoints {
  private LineSegment[] segments;
  private int numberOfSegments;

  public BruteCollinearPoints(Point[] points) {
    if (points == null) throw new IllegalArgumentException("Illegal Argument");
    numberOfSegments = 0;
    segments = new LineSegment[points.length / 4];
    for (int i = 0; i < points.length; i++) {
      if (points[i] == null) throw new IllegalArgumentException("Illegal Argument");
      for (int j = i + 1; j < points.length; j++) {
        if (points[j] == null || points[j].compareTo(points[i]) == 0) throw new IllegalArgumentException("Illegal Argument");
        for (int k = j + 1; k < points.length; k++) {
          if (points[k] == null || points[k].compareTo(points[i]) == 0 || points[k].compareTo(points[j]) == 0) throw new IllegalArgumentException("Illegal Argument");
          for (int l = k + 1; l < points.length; l++) {
            if (points[l] == null || points[l].compareTo(points[i]) == 0 || points[l].compareTo(points[j]) == 0 || points[l].compareTo(points[k]) == 0) throw new IllegalArgumentException("Illegal Argument");
            if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k]) && 
                points[i].slopeTo(points[j]) == points[i].slopeTo(points[l]) && 
                points[i].slopeTo(points[k]) == points[i].slopeTo(points[l])) {
              segments[++numberOfSegments] = new LineSegment(points[i], points[l]);
            }
          }          
        }
      }
    }
  }
  public int numberOfSegments() {
    return numberOfSegments;
  }

  public LineSegment[] segments() { return segments; }

  public static void main(String[] args) {
    
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
    
        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();
    
        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}