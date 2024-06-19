// 207083395 Yatir Zeev Gross
package GameTools;

import biuoop.DrawSurface;
/**
 * A class representing a pause screen animation.
 * It displays a message indicating that the game is paused and ask the user to press space to continue.
 */
public class PauseScreen implements Animation {
    /**
     * Performs one frame of the pause screen animation.
     * Draws the "paused -- press space to continue" message on the provided DrawSurface.
     *
     * @param d the DrawSurface on which to draw the pause screen
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }
    /**
     * Checks if the animation should stop.
     * In the case of the pause screen, it should never stop, so it always returns false.
     *
     * @return always false, indicating that the animation should not stop
     */
    public boolean shouldStop() {
        return false;
    }
}
