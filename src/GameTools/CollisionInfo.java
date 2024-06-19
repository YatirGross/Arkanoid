// 207083395 Yatir Zeev Gross
package GameTools;

import geometry.Point;

/**
 * This class represents information about a collision between two objects.
 */
public class CollisionInfo {
    private Point collision;
    private Collidable object;
    /**
     * Constructs a new GameTools.CollisionInfo object with the given collision point and object.
     *
     * @param p the point of the collision.
     * @param object the collidable object that involved in the collision.
     */
    public CollisionInfo(Point p, Collidable object) {
        this.collision = new Point(p.getX(), p.getY());
        this.object = object;
    }
    /**
     * Returns the point at which the collision occurs.
     *
     * @return the collision point.
     */
    public Point collisionPoint() {
        return new Point(this.collision.getX(), this.collision.getY());
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the collidable object.
     */
    public Collidable collisionObject() {
        return this.object;
    }
}