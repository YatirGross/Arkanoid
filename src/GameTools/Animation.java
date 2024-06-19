// 207083395 Yatir Zeev Gross
package GameTools;

import biuoop.DrawSurface;
/**
 * The Animation interface represents an animation in the game.
 * It defines the methods for performing a single frame of the animation
 * and determining whether the animation should stop.
 */
public interface Animation {
    /**
     * Performs one frame.
     *
     * @param d the DrawSurface on which to draw the frame.
     */
    void doOneFrame(DrawSurface d);

    /**
     * Checks if the animation should stop.
     *
     * @return true if the animation should stop, false otherwise.
     */
    boolean shouldStop();
}
