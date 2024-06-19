// 207083395 Yatir Zeev Gross
package GameTools;

import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import sprites.Ball;

/**
 * The Collidable interface represents an object in the game that can collide with other objects.
 * It provides methods to get the collision shape of the object, and to notify the object when a collision has occurred.
 */
public interface Collidable {
    /**
     * Returns the collision shape of the object.
     *
     * @return the collision rectangle of the object
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that a collision has occurred at the given collision point with the given velocity.
     *
     * @param  hitter the ball object that involved at the colision
     * @param collisionPoint  the point at which the collision occurred
     * @param currentVelocity the velocity of the object at the time of the collision
     * @return the new velocity expected after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}