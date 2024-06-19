// 207083395 Yatir Zeev Gross
package sprites;
import biuoop.DrawSurface;

/**
 * The sprites.Sprite interface represents a graphical object that can be drawn on a DrawSurface and updated over time.
 */
public interface Sprite {
    /**
     * Draws the sprite on a given DrawSurface.
     *
     * @param d the DrawSurface to draw the sprite on
     */
    void drawOn(DrawSurface d);

    /**
     * Updates the state of the sprite over time.
     * This method is called once per frame by the game loop.
     */
    void timePassed();
}
