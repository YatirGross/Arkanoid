// 207083395 Yatir Zeev Gross
package geometry;

import java.util.List;

/**
 * Represents a line with in two dimensions by two points, start/end.
 */
public class Line {
    private static final int INTER_AT_EDGE = 1;
    private static final int INFINITY_INTERS = 2;
    private static final int NO_INTERSECTION = 3;
    private static final double THRESHOLD = 0.00001;
    private Point start;
    private Point end;

    /**
     * Constructs a line from two points.
     *
     * @param start the starting point of the line
     * @param end   the ending point of the line
     */
    public Line(Point start, Point end) {
        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
    }

    /**
     * Constructs a line from four coordinates.
     *
     * @param x1 the x-coordinate of the starting point
     * @param y1 the y-coordinate of the starting point
     * @param x2 the x-coordinate of the ending point
     * @param y2 the y-coordinate of the ending point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Compares two doubles and returns 0 if they are within a threshold of each other.
     *
     * @param d1 the first double
     * @param d2 the second double
     * @return 0 if the doubles are within a threshold of each other, otherwise the difference between them
     */
    private double cmpDouble(double d1, double d2) {
        if (Math.abs(d1 - d2) < THRESHOLD) {
            return 0;
        }
        return d1 - d2;
    }

    /**
     * Returns the length of the line.
     *
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        double midX = (this.start.getX() + this.end.getX()) / 2.0;
        double midY = (this.start.getY() + this.end.getY()) / 2.0;
        return new Point(midX, midY);
    }

    /**
     * Returns the starting point of the line.
     *
     * @return the starting point of the line
     */
    public Point start() {
        return new Point(this.start.getX(), this.start.getY());
    }

    /**
     * Returns the ending point of the line.
     *
     * @return the ending point of the line
     */
    public Point end() {
        return new Point(this.end.getX(), this.end.getY());
    }

    /**
     * Calculates the slope of a line.
     *
     * @param line the line to calculate the slope of
     * @return the slope of the line, or 0 if the line is vertical
     */
    private double getA(Line line) {
        if (cmpDouble(line.start().getX(), line.end().getX()) == 0) {
            return 0;
        }
        return (line.start().getY() - line.end().getY())
                / (line.start().getX() - line.end().getX());
    }

    /**
     * Calculates the y-intercept of the line segment.
     *
     * @param line the line segment
     * @return the y-intercept of the line segment
     */
    private double getB(Line line) {
        double aInter = getA(line);
        return line.start.getY() - aInter * line.start.getX();
    }

    /**
     * Calculates the intersection point between two line segments.
     *
     * @param l1 the first line segment
     * @param l2 the second line segment
     * @return the intersection point between the two line segments, or null if they do not intersect
     */
    private Point getFuncInter(Line l1, Line l2) {
        double a1 = getA(l1);
        double a2 = getA(l2);
        double b1 = getB(l1);
        double b2 = getB(l2);
        double interX, interY;
        // Check for vertical line segments
        if (cmpDouble(l1.start().getX(), l1.end().getX()) == 0) {
            interX = l1.start().getX();
            interY = a2 * interX + b2;
        } else if (cmpDouble(l2.start().getX(), l2.end().getX()) == 0) {
            interX = l2.start().getX();
            interY = a1 * interX + b1;
        } else {
            // Check for parallel line segments
            if (cmpDouble(a1, a2) == 0) {
                if (cmpDouble(b1, b2) == 0) {
                    int ans = l1.sameEquation(l2);
                    if (ans == INTER_AT_EDGE) {
                        return l1.getInterWhenSameEquation(l2);
                    }
                }
                return null;
            }
            // Calculate intersection point
            interX = (b2 - b1) / (a1 - a2);
            interY = a1 * interX + b1;
        }
        return new Point(interX, interY);
    }

    /**
     * Returns the intersection point with another line that has the same equation as this line.
     * and we know there is one intersection point.
     *
     * @param other the other line to check for intersection with
     * @return the intersection point if there is one, null otherwise
     */
    public Point getInterWhenSameEquation(Line other) {
        if (cmpDouble(this.start.getX(), other.start().getX()) == 0
                || cmpDouble(this.start.getX(), other.end().getX()) == 0) {
            return this.start();
        }
        if (cmpDouble(this.end.getX(), other.start().getX()) == 0
                || cmpDouble(this.end.getX(), other.end().getX()) == 0) {
            return this.end();
        }
        return null;
    }

    /**
     * Returns the intersection point with another line that has the
     * same equation as this line and they are vertical lines.
     * and we know there is one intersection point.
     *
     * @param other the other line to check for intersection with
     * @return the intersection point if there is one, null otherwise
     */
    public Point getInterWhenVerticalEquation(Line other) {
        if (cmpDouble(this.start.getY(), other.start().getY()) == 0
                || cmpDouble(this.start.getY(), other.end().getY()) == 0) {
            return this.start();
        }
        if (cmpDouble(this.end.getY(), other.start().getY()) == 0
                || cmpDouble(this.end.getY(), other.end().getY()) == 0) {
            return this.end();
        }
        return null;
    }

    /**
     * Get two lines with the same equation. and determines if:
     * the lines have one intersection at the edge.
     * the lines have infinite intersections.
     * or no intersections at all.
     *
     * @param other the other line to compare with.
     * @return an integer constant indicating the type of intersection between the two lines.
     */
    public int sameEquation(Line other) {
        // Determine the minimum and maximum x values for both lines.
        double x1 = Math.min(this.start.getX(), this.end.getX());
        double x2 = Math.max(this.start.getX(), this.end.getX());
        double x3 = Math.min(other.start().getX(), other.end().getX());
        double x4 = Math.max(other.start().getX(), other.end().getX());
        // Check if the lines overlap on the x-axis.
        if ((cmpDouble(x1, x3) >= 0 && cmpDouble(x1, x4) <= 0) || (cmpDouble(x3, x1) >= 0 && cmpDouble(x3, x2) <= 0)) {
            // If the lines share a point at edge, they have one intersection.
            if (cmpDouble(x2, x3) == 0 || cmpDouble(x1, x4) == 0) {
                return INTER_AT_EDGE;
            }
            // Otherwise, the lines have infinite intersections.
            return INFINITY_INTERS;
        }
        // If the lines do not overlap on the x-axis, they do not intersect.
        return NO_INTERSECTION;
    }

    /**
     * Get two vertical lines with the same equation. and determines if:
     * the lines have one intersection at the edge.
     * the lines have infinite intersections.
     * or no intersections at all.
     *
     * @param other the other line to compare with.
     * @return an integer constant indicating the type of intersection between the two lines.
     */
    public int verticalEquation(Line other) {
        // Determine the minimum and maximum y values for both lines.
        double y1 = Math.min(this.start.getY(), this.end.getY());
        double y2 = Math.max(this.start.getY(), this.end.getY());
        double y3 = Math.min(other.start().getY(), other.end().getY());
        double y4 = Math.max(other.start().getY(), other.end().getY());
        // Check if the lines overlap on the y-axis.
        if ((cmpDouble(y1, y3) >= 0 && cmpDouble(y1, y4) <= 0) || (cmpDouble(y3, y1) >= 0 && cmpDouble(y3, y2) <= 0)) {
            if (cmpDouble(y2, y3) == 0 || cmpDouble(y1, y4) == 0) {
                return INTER_AT_EDGE;
            }
            // If the lines share a point at edge, they have one intersection.
            return INFINITY_INTERS;
        }
        // If the lines do not overlap on the y-axis, they do not intersect.
        return NO_INTERSECTION;
    }

    /**
     * Returns true if this line and another line intersect, false otherwise.
     *
     * @param other the other line to check for intersection
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        // If both lines are vertical
        if (cmpDouble(this.start().getX(), this.end().getX()) == 0
                && cmpDouble(other.start().getX(), other.end().getX()) == 0) {
            // If both lines are on the same x value
            if (cmpDouble(this.start().getX(), other.start.getX()) == 0) {
                int ans = this.verticalEquation(other);
                return ans == INFINITY_INTERS || ans == INTER_AT_EDGE;
            }
            return false;
        }
        // Get the intersection point of the two lines
        Point inter = getFuncInter(this, other);
        // If the lines are parallel or lying in the same line.
        if (inter == null) {
            int ans = this.sameEquation(other);
            return ans == INFINITY_INTERS;
        }
        // Check if the intersection point is within the bounds of both lines
        return cmpDouble(inter.getX(), Math.min(this.start.getX(), this.end.getX())) >= 0
                && cmpDouble(inter.getX(), Math.max(this.start.getX(),
                this.end.getX())) <= 0
                && cmpDouble(inter.getX(), Math.min(other.start.getX(),
                other.end.getX())) >= 0
                && cmpDouble(inter.getX(), Math.max(other.start.getX(),
                other.end.getX())) <= 0
                && cmpDouble(inter.getY(), Math.min(this.start.getY(),
                this.end.getY())) >= 0
                && cmpDouble(inter.getY(), Math.max(this.start.getY(),
                this.end.getY())) <= 0
                && cmpDouble(inter.getY(), Math.min(other.start.getY(),
                other.end.getY())) >= 0
                && cmpDouble(inter.getY(), Math.max(other.start.getY(),
                other.end.getY())) <= 0;
    }

    /**
     * Returns the intersection point of this line with another given line.
     * If they don't intersect, returns null.
     *
     * @param other the other line to check intersection with
     * @return the intersection point of the two lines, or null if they don't intersect
     */
    public Point intersectionWith(Line other) {
        // If the lines don't intersect, return null
        if (!this.isIntersecting(other)) {
            return null;
        }
        /*
         * If both lines are vertical and share the same x coordinate,
         * check for intersection at the edge case
         */
        if (cmpDouble(this.start().getX(), this.end().getX()) == 0
                && cmpDouble(other.start().getX(), other.end().getX()) == 0
                && cmpDouble(this.start().getX(), other.start.getX()) == 0) {
            int ans = this.verticalEquation(other);
            if (ans == INTER_AT_EDGE) {
                return this.getInterWhenVerticalEquation(other);
            }
            return null;
        }
        // Otherwise, return the intersection point of the two lines
        return getFuncInter(this, other);
    }

    /**
     * Check if a point locate on the line.
     *
     * @param p the point to check
     * @return true - if the point in the line. false - if not.
     */
    public boolean pointOnLine(Point p) {
        Line l = new Line(p, p);
        return this.isIntersecting(l);
    }

    /**
     * Returns the closest intersection point of this line with a given rectangle, if exists.
     * If this line does not intersect with the rectangle, returns null.
     *
     * @param rect the rectangle to check for intersection with this line.
     * @return the closest intersection point of this line with the rectangle, if exists. Otherwise, null.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> lst = rect.intersectionPoints(this);
        if (lst.isEmpty()) {
            return null;
        }
        Point closest = lst.get(0);
        for (int i = 1; i < lst.size(); i++) {
            if (this.start.distance(closest) > this.start.distance(lst.get(i))) {
                closest = lst.get(i);
            }
        }
        return closest;
    }

    /**
     * Returns true if this line is equal to the given line, and false otherwise.
     * Two lines are considered equal if they have the same start and end points,
     * regardless of the order of the points.
     *
     * @param other the line to compare to
     * @return true if this line is equal to the given line, and false otherwise
     */
    public boolean equals(Line other) {
        return (this.start().equals(other.start())
                && this.end().equals(other.end()))
                || (this.start().equals(other.end())
                && this.end().equals(other.start()));
    }
}
