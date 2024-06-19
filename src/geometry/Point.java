//207083395 Yatir Zeev Gross
package geometry;
/**
The geometry.Point class represents a point in two-dimensional space with x and y coordinates.
 */
public class Point {
    private static final double THRESHOLD = 0.00001;
    private double x;
    private double y;

    /**
     * Constructs a new geometry.Point object with the given x and y coordinates.
     * @param x The x coordinate of the point
     * @param y The y coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the distance between this point and another point.
     * @param other The other point to calculate the distance to.
     * @return The distance between the two points
     */
    public double distance(Point other) {
        double diffX = Math.abs(this.x - other.getX());
        double diffY = Math.abs(this.y - other.getY());
        return Math.sqrt(diffX * diffX + diffY * diffY);
    }

    /**
     * Compares two double values using the threshold.
     * @param d1 The first double value
     * @param d2 The second double value
     * @return 0 if the difference between the two values is less than the threshold, otherwise returns d1-d2.
     */
    public static double cmpDouble(double d1, double d2) {
        if (Math.abs(d1 - d2) < THRESHOLD) {
            return 0;
        }
        return d1 - d2;
    }

    /**
     * Checks if this point is equal to another point.
     * @param other The other point to compare to
     * @return True if the x and y coordinates of the two points are equal, false otherwise
     */
    public boolean equals(Point other) {
        return cmpDouble(this.x, other.getX()) == 0 && cmpDouble(this.y, other.getY()) == 0;
    }

    /**
     * Returns the x coordinate of the point.
     * @return The x coordinate of the point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y coordinate of the point.
     * @return The y coordinate of the point
     */
    public double getY() {
        return this.y;
    }
}
