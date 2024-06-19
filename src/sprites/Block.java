// 207083395 Yatir Zeev Gross
package sprites;
import GameTools.Collidable;
import GameLevels.GameLevel;
import Notifier.HitListener;
import Notifier.HitNotifier;
import geometry.Velocity;
import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The sprites.Block class represents a block in the game. A block is a Collidable object that can be hit by a ball and
 * a Sprite object that can be drawn on the screen. with a shape of rectangle.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private static final int UP = 0;
    private static final int RIGHT = 90;
    private static final int DOWN = 180;
    private static final int LEFT = 270;
    private static final int FULL_CIRCLE = 360;

    private Rectangle shape;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * Constructor for the sprites.Block class.
     *
     * @param shape the shape of the block
     * @param color the color of the block
     */
    public Block(Rectangle shape, Color color) {
        this.shape = new Rectangle(shape.getUpperLeft(), shape.getWidth(), shape.getHeight());
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Constructor for the sprites.Block class.
     *
     * @param upperLeft the upper left point of the block
     * @param width     the width of the block
     * @param height    the height of the block
     * @param color     the color of the block
     */
    public Block(Point upperLeft, double width, double height, Color color) {
        this.shape = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Get the rectangle shape of the block.
     *
     * @return a copy of the rectangle of the block
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.shape.getUpperLeft(), this.shape.getWidth(), this.shape.getHeight());
    }
    /**
     * Removes the collidable and sprite associated with this object from the given game.
     *
     * @param gameLevel The game from which to remove the collidable and sprite.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }
    /**
     * Notify the block that it has been hit by a ball at a certain collision point with a given velocity.
     * Calculate the new velocity of the ball.
     *
     * @param hitter the ball object that involved at the colision
     * @param collisionPoint  the point of collision with the block
     * @param currentVelocity the current velocity of the ball
     * @return the new velocity of the ball after the hit
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVct = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        // Calculate the points of the block's corners
        Point upperRight = new Point(this.shape.getUpperLeft().getX() + this.shape.getWidth(),
                this.shape.getUpperLeft().getY());
        Point bottomLeft = new Point(this.shape.getUpperLeft().getX(),
                this.shape.getUpperLeft().getY() + this.shape.getHeight());
        Point bottomRight = new Point(this.shape.getUpperLeft().getX() + this.shape.getWidth(),
                this.shape.getUpperLeft().getY() + this.shape.getHeight());
        // If the collision point is in the upper left corner of the block
        if (collisionPoint.equals(this.shape.getUpperLeft())) {
            // Check from which direction the ball is coming to the corner and change the velocity accordingly
            if (currentVelocity.getAngle() > UP && currentVelocity.getAngle() < RIGHT) {
                newVct.setDx(-currentVelocity.getDx());
                return newVct;
            }
            if (currentVelocity.getAngle() > DOWN && currentVelocity.getAngle() < LEFT) {
                newVct.setDy(-currentVelocity.getDy());
                return newVct;
            }
        }
        // If the collision point is in the upper right corner of the block
        if (collisionPoint.equals(upperRight)) {
            // Check from which direction the ball is coming to the corner and change the velocity accordingly
            if (currentVelocity.getAngle() > LEFT && currentVelocity.getAngle() < FULL_CIRCLE) {
                newVct.setDx(-currentVelocity.getDx());
                return newVct;
            }
            if (currentVelocity.getAngle() > RIGHT && currentVelocity.getAngle() < DOWN) {
                newVct.setDy(-currentVelocity.getDy());
                return newVct;
            }
        }
        // If the collision point is in the bottom left corner of the block
        if (collisionPoint.equals(bottomLeft)) {
            // Check from which direction the ball is coming to the corner and change the velocity accordingly
            if (currentVelocity.getAngle() > RIGHT && currentVelocity.getAngle() < DOWN) {
                newVct.setDx(-currentVelocity.getDx());
                return newVct;
            }
            if (currentVelocity.getAngle() > LEFT && currentVelocity.getAngle() < FULL_CIRCLE) {
                newVct.setDy(-currentVelocity.getDy());
                return newVct;
            }
        }
        // If the collision point is in the bottom right corner of the block
        if (collisionPoint.equals(bottomRight)) {
            // Check from which direction the ball is coming to the corner and change the velocity accordingly
            if (currentVelocity.getAngle() > DOWN && currentVelocity.getAngle() < LEFT) {
                newVct.setDx(-currentVelocity.getDx());
                return newVct;
            }
            if (currentVelocity.getAngle() > UP && currentVelocity.getAngle() < RIGHT) {
                newVct.setDy(-currentVelocity.getDy());
                return newVct;
            }
        }
        /*
         * If the cutting point is not in the corners of the block, or it is in a corner but the ball does not come
         * from special directions. Check which side of the block the ball is hit, and change the velocity accordingly.
         */
        if (Point.cmpDouble(collisionPoint.getX(), this.shape.getUpperLeft().getX()) == 0
            || Point.cmpDouble(collisionPoint.getX(), this.shape.getUpperLeft().getX() + this.shape.getWidth()) == 0) {
            newVct.setDx(-currentVelocity.getDx());
        }
        if (Point.cmpDouble(collisionPoint.getY(), this.shape.getUpperLeft().getY()) == 0
            || Point.cmpDouble(collisionPoint.getY(), this.shape.getUpperLeft().getY() + this.shape.getHeight()) == 0) {
            newVct.setDy(-currentVelocity.getDy());
        }
        this.notifyHit(hitter);
        return newVct;
    }

    /**
     * Draws the block on a given DrawSurface.
     *
     * @param surface the DrawSurface to draw the block on.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(), (int) this.shape.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(), (int) this.shape.getHeight());
    }

    /**
     * Adds the block as a Collidable and a Sprite to a given GameTools.Game.
     *
     * @param g the Game to add the block to.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Updates the state of the block over time.
     * This method is called once per frame by the game loop. (currently does nothing)
     */
    @Override
    public void timePassed() {
    }

    /**
     * Notifies all registered hit listeners about a hit event with the specified ball.
     *
     * @param hitter The ball that hit the block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
    /**
     * Adds a hit listener to the block.
     *
     * @param hl The hit listener to add.
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    /**
     * Removes a hit listener from the block.
     *
     * @param hl The hit listener to remove.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
