// 207083395 Yatir Zeev Gross
package geometry;

/**
 The geometry.Velocity class represents the velocity of a ball, by its dx and dy values.
 It also provides methods to calculate the angle and speed of the velocity vector, and to apply it to a point.
 */
public class Velocity {
    private static final int UP = 0;
    private static final int RIGHT = 90;
    private static final int DOWN = 180;
    private static final int LEFT = 270;
    private static final int PI_IN_DEGREE = 180;
    private static final double THRESHOLD = 0.00001;
    private double dx;
    private double dy;
    /**
     Constructs a new geometry.Velocity object with the given dx and dy values.

     @param dx the change in x value.
     @param dy the change in y value.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     Compares two double values to check if they are equal, within a small threshold.

     @param d1 the first double value.
     @param d2 the second double value.
     @return 0 if the values are equal (within the threshold), or the difference between them.
     */
    private double cmpDouble(double d1, double d2) {
        if (Math.abs(d1 - d2) < THRESHOLD) {
            return 0;
        }
        return d1 - d2;
    }
    /**
     Returns the current dx value of this geometry.Velocity.

     @return the dx value.
     */
    public double getDx() {
        return this.dx;
    }
    /**
     Returns the current dy value of this geometry.Velocity.

     @return the dy value.
     */
    public double getDy() {
        return this.dy;
    }
    /**
     Sets the dx value of this geometry.Velocity to a new value.

     @param dx the new dx value.
     */
    public void setDx(double dx) {
        this.dx = dx;
    }
    /**
     Sets the dy value of this geometry.Velocity to a new value.

     @param dy the new dy value.
     */
    public void setDy(double dy) {
        this.dy = dy;
    }
    /**
     Applies this geometry.Velocity object to a given geometry.Point, and returns a new geometry.Point
     with the new x and y values calculated.

     @param p the geometry.Point to apply the geometry.Velocity to.
     @return a new geometry.Point with the new x and y values.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
    /**
     Calculates and returns a new geometry.Velocity object based on an angle and speed.

     @param angle the angle in degrees of the geometry.Velocity vector.
     @param speed the magnitude (speed) of the geometry.Velocity vector.
     @return a new geometry.Velocity object with the dx and dy values calculated based on the given angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radAngle = angle / PI_IN_DEGREE * Math.PI;
        double dx = speed * Math.sin(radAngle);
        double dy = speed * Math.cos(radAngle);
        // In DrawSurface the Y axis is inverted, down direction is positive.
        return new Velocity(dx, -dy);
    }
    /**
     Calculates and returns the speed of this geometry.Velocity vector.

     @return the speed value.
     */
    public double getSpeed() {
        return Math.sqrt(this.dx * this.dx + this.dy * this.dy);
    }
    /**
     Calculates the angle of the velocity vector in degrees.
     If the dy component of the velocity is 0, it determines the angle based on the dx component alone.
     If the dy component is not 0, it uses the Math.atan to calculate the angle based on the dx and dy components.
     and then converts the angle from radians to degrees and makes sure the angle is in the range [0, 360) degrees.

     @return the angle of the velocity in degrees
     */
    public double getAngle() {
        double angle;
        double dyOp = -this.dy;
        if (cmpDouble(dyOp, 0) == 0) {
            // angle is either 90 degrees or 270 degrees, depending on the sign of the dx component
            angle = this.dx >= 0 ? RIGHT : LEFT;
        } else {
            // calculate the angle based on the dx and dy components using the Math.atan method
            angle = Math.atan(this.dx / dyOp);
            // convert the angle from radians to degrees
            angle = angle / Math.PI * PI_IN_DEGREE;
            if (cmpDouble(dyOp, 0) < 0) {
                // add 180 degrees to the angle to get the correct angle in the range [0, 360) degrees
                angle += PI_IN_DEGREE;
            }
            if (angle < 0) {
                // add 360 degrees to the angle to get the correct angle in the range [0, 360) degrees
                angle += PI_IN_DEGREE + PI_IN_DEGREE;
            }
        }
        return angle;
    }
}
