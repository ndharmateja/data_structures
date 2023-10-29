package algos.geometry.convex_hull;

import java.util.Arrays;
import java.util.Scanner;

import data_structures.stack.LinkedStack;
import data_structures.stack.Stack;
import edu.princeton.cs.algs4.Point2D;

public class ConvexHull {
    /**
     * Computes the convex hull points
     * 
     * @param points
     * @return the hull points starting from the point with the lowest
     *         y-coordinate and goes counter clockwise. the innermost point in the
     *         stack is that first point.
     */
    public static Stack<Point2D> convexHull(Point2D[] points) {
        Stack<Point2D> hull = new LinkedStack<>();

        // Sort the points by y coordinates first
        // and then by the polar angle wrt to the point
        // with the lowest y coordinate
        Arrays.sort(points, Point2D.Y_ORDER);
        Arrays.sort(points, points[0].polarOrder());

        // First two points are part of the hull
        hull.push(points[0]);
        hull.push(points[1]);

        for (int i = 2; i < points.length; i++) {
            // Remove all the points that form a cw turn
            // with the new point
            Point2D curr = points[i];
            Point2D top = hull.pop();
            while (Point2D.ccw(hull.peek(), top, curr) <= 0) {
                top = hull.pop();
            }

            // Retain the points that form a ccw turn
            // hull.peek(), top and curr form a ccw turn
            // So we retain them
            hull.push(top);
            hull.push(curr);
        }

        return hull;
    }

    public static void main(String[] args) {
        // read the n points from std in
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Point2D[] points = new Point2D[n];
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            points[i] = new Point2D(x, y);
        }
        in.close();

        // Get the stack of the convex hull
        // The innermost point in the stack is the first point
        Stack<Point2D> hull = convexHull(points);

        // Put points from stack in reverse order into the array
        // So the first point of the array is the first point
        // of the hull
        Point2D[] hullArray = new Point2D[hull.size()];
        int i = hullArray.length - 1;
        for (Point2D point : hull) {
            hullArray[i--] = point;
        }

        // Print all the points in the hull
        // Starting from the lowest y-coordinate point in the hull
        for (Point2D point2d : hullArray) {
            System.out.print(point2d + " -> ");
        }
        System.out.println(hullArray[0]);
    }
}
