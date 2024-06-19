// 207083395 Yatir Zeev Gross
package geometry;

import java.util.ArrayList;
/**
 * Represents a rectangle with an upper-left point, width, and height.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    /**
     * Constructs a new geometry.Rectangle object with the specified upper-left point, width, and height.
     *
     * @param upperLeft the upper-left point of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = new Point(upperLeft.getX(), upperLeft.getY());
        this.width = width;
        this.height = height;
    }

    /**
     * Returns a (possibly empty) list of intersection points with the specified line.
     *
     * @param line the line to check for intersection with the rectangle
     * @return a list of intersection points, if there are.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> lst = new ArrayList<>();
        // Calculate the coordinates of the rectangle's corners
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point bottomLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Point bottomRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        // Create geometry.Line objects representing the sides of the rectangle
        Line upper = new Line(upperRight, this.upperLeft);
        Line bottom = new Line(bottomLeft, bottomRight);
        Line left = new Line(this.upperLeft, bottomLeft);
        Line right = new Line(upperRight, bottomRight);
        // Check for intersections with each side of the rectangle
        if (line.isIntersecting(upper)) {
            Point inter = line.intersectionWith(upper);
            // if null, maybe there is infinity intersections so checks if intersect at appropriate corners
            if (inter == null) {
                if (line.pointOnLine(upperRight)) {
                    lst.add(upperRight);
                }
                if (line.pointOnLine(this.upperLeft)) {
                    lst.add(this.upperLeft);
                }
            } else {
                lst.add(inter);
            }
        }
        if (line.isIntersecting(bottom)) {
            Point inter = line.intersectionWith(bottom);
            if (inter == null) {
                if (line.pointOnLine(bottomRight)) {
                    lst.add(bottomRight);
                }
                if (line.pointOnLine(bottomLeft)) {
                    lst.add(bottomLeft);
                }
            } else {
                lst.add(inter);
            }
        }
        if (line.isIntersecting(left)) {
            Point inter = line.intersectionWith(left);
            if (inter == null) {
                if (line.pointOnLine(this.upperLeft)) {
                    lst.add(upperRight);
                }
                if (line.pointOnLine(bottomLeft)) {
                    lst.add(this.upperLeft);
                }
            } else {
                // Add intersection point if it's not a corner of the rectangle (if it is, it already added)
                if (!inter.equals(this.upperLeft) && !inter.equals(bottomLeft)) {
                    lst.add(inter);
                }
            }
        }
        if (line.isIntersecting(right)) {
            Point inter = line.intersectionWith(right);
            if (inter == null) {
                if (line.pointOnLine(upperRight)) {
                    lst.add(upperRight);
                }
                if (line.pointOnLine(bottomRight)) {
                    lst.add(this.upperLeft);
                }
            } else {
                // Add intersection point if it's not a corner of the rectangle (if it is, it already added)
                if (!inter.equals(upperRight) && !inter.equals(bottomRight)) {
                    lst.add(inter);
                }
            }
        }
        return lst;
    }

    /**
     * Return the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * Return the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return new Point(this.upperLeft.getX(), this.upperLeft.getY());
    }
}