// 207083395 Yatir Zeev Gross
package sprites;
import GameTools.Collidable;
import GameLevels.GameLevel;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

import java.awt.Color;

/**
 * The sprites.Paddle class represents the game paddle.
 * It implements both the sprites.Sprite and GameTools.Collidable interfaces.
 */
public class Paddle implements Sprite, Collidable {
    private static final double REGION_1 = 1;
    private static final double REGION_2 = 2;
    private static final double REGION_3 = 3;
    private static final double REGION_4 = 4;
    private static final double REGION_5 = 5;
    private static final double REGIONS = 5;
    private static final int ANGLE_1 = 300;
    private static final int ANGLE_2 = 330;
    private static final int ANGLE_4 = 30;
    private static final int ANGLE_5 = 60;
    private biuoop.KeyboardSensor keyboard;
    private Rectangle shape;
    private int minRange;
    private int maxRange;
    private Color color;
    private int speed;

    /**
     * Constructor.
     * Initializes a new paddle with the given GUI, rectangle, min and max ranges, and color.
     *
     * @param keyboard  the keyboard sensor.
     * @param rectangle the rectangle shape of the paddle.
     * @param min       the minimum range for the paddle movement.
     * @param max       the maximum range for the paddle movement.
     * @param color     the color of the paddle.
     * @param speed     the paddle's speed.
     */
    public Paddle(KeyboardSensor keyboard, Rectangle rectangle, int min, int max, Color color, int speed) {
        this.keyboard = keyboard;
        this.shape = rectangle;
        this.color = color;
        this.minRange = min;
        this.maxRange = max;
        this.speed = speed;
    }

    /**
     * Moves the paddle left.
     * If the movement goes out of bounds, the paddle's position is set to the min range.
     */
    public void moveLeft() {
        Point point;
        if (this.shape.getUpperLeft().getX() - this.speed < this.minRange) {
            point = new Point(this.minRange, this.shape.getUpperLeft().getY());
        } else {
            point = new Point(this.shape.getUpperLeft().getX() - this.speed, this.shape.getUpperLeft().getY());
        }
        this.shape = new Rectangle(point, this.shape.getWidth(), this.shape.getHeight());
    }

    /**
     * Moves the paddle right.
     * If the movement goes out of bounds, the paddle's position is set to the max range.
     */
    public void moveRight() {
        Point point;
        if (this.shape.getUpperLeft().getX() + this.shape.getWidth() + this.speed > this.maxRange) {
            point = new Point(this.maxRange - this.shape.getWidth(), this.shape.getUpperLeft().getY());
        } else {
            point = new Point(this.shape.getUpperLeft().getX() + this.speed, this.shape.getUpperLeft().getY());
        }
        this.shape = new Rectangle(point, this.shape.getWidth(), this.shape.getHeight());
    }

    /**
     * Draws the paddle on the given DrawSurface object.
     *
     * @param d the DrawSurface to draw the paddle on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(), (int) this.shape.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(), (int) this.shape.getHeight());
    }

    /**
     * This method is called by the game's animation loop in each frame.
     * It checks whether the left or right arrow key is currently pressed and moves the paddle accordingly.
     */
    @Override
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        } else if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * return a new geometry.Rectangle object that represents the shape of the paddle.
     *
     * @return the shape of the paddle
     */
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.shape.getUpperLeft(), this.shape.getWidth(), this.shape.getHeight());
    }

    /**
     * Calculates and returns the new velocity of the ball after hitting the paddle
     * in one of the five different regions.
     *
     * @param p        the collision point between the ball and the paddle
     * @param currentV the current velocity of the ball
     * @return the new velocity of the ball after hitting the paddle in the corresponding region.
     */
    private Velocity getRegion(Point p, Velocity currentV) {
        double r0 = this.shape.getUpperLeft().getX();
        double r1 = this.shape.getUpperLeft().getX() + (REGION_1 / REGIONS) * this.shape.getWidth();
        double r2 = this.shape.getUpperLeft().getX() + (REGION_2 / REGIONS) * this.shape.getWidth();
        double r3 = this.shape.getUpperLeft().getX() + (REGION_3 / REGIONS) * this.shape.getWidth();
        double r4 = this.shape.getUpperLeft().getX() + (REGION_4 / REGIONS) * this.shape.getWidth();
        if (Point.cmpDouble(p.getX(), r0) >= 0 && Point.cmpDouble(p.getX(), r1) < 0) {
            return Velocity.fromAngleAndSpeed(ANGLE_1, currentV.getSpeed());
        }
        if (Point.cmpDouble(p.getX(), r1) >= 0 && Point.cmpDouble(p.getX(), r2) < 0) {
            return Velocity.fromAngleAndSpeed(ANGLE_2, currentV.getSpeed());
        }
        if (Point.cmpDouble(p.getX(), r2) >= 0 && Point.cmpDouble(p.getX(), r3) < 0) {
            return new Velocity(currentV.getDx(), -currentV.getDy());
        }
        if (Point.cmpDouble(p.getX(), r3) >= 0 && Point.cmpDouble(p.getX(), r4) < 0) {
            return Velocity.fromAngleAndSpeed(ANGLE_4, currentV.getSpeed());
        }
        return Velocity.fromAngleAndSpeed(ANGLE_5, currentV.getSpeed());
    }

    /**
     * Calculates the new velocity after a collision with the paddle, based on the collision point.
     * If the collision occurs with the top surface of the paddle, it determines the new velocity based on five regions.
     * If the collision occurs with one of the paddle's side surfaces, the method calculates the new velocity by
     * reversing the ball's horizontal velocity.
     *
     * @param hitter the ball object that hit the block.
     * @param collisionPoint  the point of collision with the paddle
     * @param currentVelocity the current velocity of the ball
     * @return the new velocity of the paddle
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (Point.cmpDouble(collisionPoint.getY(), this.shape.getUpperLeft().getY()) == 0) {
            return getRegion(collisionPoint, currentVelocity);
        }
        Velocity newVct = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        if (Point.cmpDouble(collisionPoint.getX(), this.shape.getUpperLeft().getX()) == 0
            || Point.cmpDouble(collisionPoint.getX(), this.shape.getUpperLeft().getX() + this.shape.getWidth()) == 0) {
            newVct.setDx(-currentVelocity.getDx());
        }
        if (Point.cmpDouble(collisionPoint.getY(), this.shape.getUpperLeft().getY()) == 0
            || Point.cmpDouble(collisionPoint.getY(), this.shape.getUpperLeft().getY() + this.shape.getHeight()) == 0) {
            newVct.setDy(-currentVelocity.getDy());
        }
        return newVct;
    }

    /**
     * Add this paddle to the game.
     *
     * @param g the game to add the paddle to.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
