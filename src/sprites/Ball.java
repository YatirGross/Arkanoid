// 207083395 Yatir Zeev Gross
package sprites;

import GameTools.CollisionInfo;
import GameLevels.GameLevel;
import GameTools.GameEnvironment;
import geometry.Velocity;
import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;

import java.util.Random;
import java.awt.Color;

/**
 * This class represents a ball with its center, radius, color, and velocity.
 */
public class Ball implements Sprite {
    private static final double ADD_FOR_POINT = 0.1;
    private static final double THRESHOLD = 0.00001;
    private Point center;
    private int r;
    private java.awt.Color color;
    private GameEnvironment gameEnvironment;
    private Velocity v = new Velocity(0, 0);

    /**
     * Constructor function that creates a new ball from a center point, radius, and color.
     *
     * @param center          the center point of the ball
     * @param r               the radius of the ball
     * @param color           the color of the ball
     * @param gameEnvironment the gama environment of the ball
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = new Point(center.getX(), center.getY());
        this.r = r;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Constructor function that creates a new ball from a center point coordinates, radius, and color.
     *
     * @param x     the x coordinate of the center point of the ball
     * @param y     the y coordinate of the center point of the ball
     * @param r     the radius of the ball
     * @param color the color of the ball
     * @param gameEnvironment the gama environment of the ball
     */
    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Returns the x coordinate of the center point of the ball.
     *
     * @return the x coordinate of the center point of the ball
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Returns the y coordinate of the center point of the ball.
     *
     * @return the y coordinate of the center point of the ball
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Returns the radius of the ball.
     *
     * @return the radius of the ball
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Returns the color of the ball.
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Generates a random color.
     *
     * @return a random color
     */
    public static Color randColor() {
        Random random = new Random();
        Color[] c = {Color.RED, Color.ORANGE, Color.MAGENTA, Color.CYAN, Color.GREEN, Color.BLUE, Color.PINK};
        return c[(int) (random.nextDouble() * c.length)];
    }

    /**
     * Compares two double values with a given threshold.
     *
     * @param d1 the first double value to compare
     * @param d2 the second double value to compare
     * @return 0 if the difference between d1 and d2 is within the threshold, otherwise returns d1-d2.
     */
    private double cmpDouble(double d1, double d2) {
        if (Math.abs(d1 - d2) < THRESHOLD) {
            return 0;
        }
        return d1 - d2;
    }

    /**
     * Sets the velocity of the ball with a given geometry.Velocity.
     *
     * @param v the new velocity of the object
     */
    public void setVelocity(Velocity v) {
        this.v = new Velocity(v.getDx(), v.getDy());
    }

    /**
     * Sets the velocity of the object with given dx and dy values.
     *
     * @param dx the new x velocity of the ball
     * @param dy the new y velocity of the ball
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * Returns a new geometry.Velocity with the same dx and dy values as the current ball's velocity.
     *
     * @return the current ball's velocity
     */
    public Velocity getVelocity() {
        return new Velocity(this.v.getDx(), this.v.getDy());
    }

    /**
     * Calculates a slightly different point than the given point p2, based on the position of p1.
     * If p1 is to the left of p2, the x-coordinate of the returned point will be slightly smaller.
     * If p1 is to the right of p2, the x-coordinate of the returned point will be slightly larger.
     * If p1 is above p2, the y-coordinate of the returned point will be slightly smaller.
     * If p1 is below p2, the y-coordinate of the returned point will be slightly larger.
     * The amount added or subtracted from the x and y coordinates is determined by the constant ADD_FOR_POINT.
     *
     * @param p1 the reference point for determining the position of the returned point.
     * @param p2 the original point.
     * @return a slightly different point than p2, based on the position of p1.
     */
    private Point getSlightlyP(Point p1, Point p2) {
        double x = p2.getX(), y = p2.getY();
        if (cmpDouble(p1.getX(), p2.getX()) < 0) {
            x -= ADD_FOR_POINT;
        }
        if (cmpDouble(p1.getX(), p2.getX()) > 0) {
            x += ADD_FOR_POINT;
        }
        if (cmpDouble(p1.getY(), p2.getY()) < 0) {
            y -= ADD_FOR_POINT;
        }
        if (cmpDouble(p1.getY(), p2.getY()) > 0) {
            y += ADD_FOR_POINT;
        }
        return new Point(x, y);
    }

    /**
     * Moves the ball one step according to its current velocity.
     * If the ball hits the edge of the screen, changes its velocity accordingly.
     */
    public void moveOneStep() {
        Point nextCenter = new Point(this.center.getX() + this.v.getDx(), this.center.getY() + this.v.getDy());
        Line trajectory = new Line(this.center, nextCenter);
        CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(trajectory);
        if (collisionInfo == null) {
            this.center = nextCenter;
        } else {
            this.center = getSlightlyP(this.center, collisionInfo.collisionPoint());
            this.v = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), this.v);
        }
    }
    /**
     * Removes the sprite from the game by invoking the removeSprite method on the given Game object.
     *
     * @param g the game from which the sprite should be removed
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
    /**
     * Draws the ball on a given DrawSurface.
     *
     * @param surface the DrawSurface to draw on
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.r);
        surface.setColor(Color.black);
        surface.drawCircle(this.getX(), this.getY(), this.r);
    }

    /**
     * This method adds the current object to the given game as a sprite.
     *
     * @param g the game to add the object to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * This method is called every frame and moves the current ball one step according to its velocity.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }
}
