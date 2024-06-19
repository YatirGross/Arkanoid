// 207083395 Yatir Zeev Gross
package GameTools;
import geometry.Line;
import geometry.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * The GameTools.GameEnvironment class represents a collection of collidable objects in the game.
 * It holds a list of all the collidables in the game environment and provides methods
 * to add new collidables and check for collisions with a given trajectory.
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * Constructor for GameTools.GameEnvironment class.
     * Initializes a new ArrayList to hold the collidables.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Adds the given collidable to the game environment.
     *
     * @param c the collidable to be added.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Removes a collidable object from the list of collidables.
     *
     * @param c the collidable object to be removed
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }
    /**
     * Checks for the closest collision between a given trajectory and the collidables in the environment.
     *
     * @param trajectory the line representing the movement of the object.
     * @return a CollisionInfo object containing information about the closest collision that is going to occur.
     * If no collision is found, returns null.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Point> inters = new ArrayList<>();
        List<Collidable> collideIn = new ArrayList<>();
        Point intersec;
        // Run over all collidables in the environment
        for (int i = 0; i < collidables.size(); i++) {
            // Check if the trajectory intersects with the collidable
            intersec = trajectory.closestIntersectionToStartOfLine(collidables.get(i).getCollisionRectangle());
            if (intersec != null) {
                inters.add(intersec);
                collideIn.add(collidables.get(i));
            }
        }
        if (inters.isEmpty()) {
            return null;
        }
        Point closest = inters.get(0);
        int closeIndex = 0;
        // Find the closest intersection to the start of the trajectory
        for (int i = 1; i < inters.size(); i++) {
            if (trajectory.start().distance(closest) > trajectory.start().distance(inters.get(i))) {
                closest = inters.get(i);
                closeIndex = i;
            }
        }
        return new CollisionInfo(closest, collideIn.get(closeIndex));
    }
}