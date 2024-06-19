// 207083395 Yatir Zeev Gross
package GameTools;

import Notifier.Counter;
import biuoop.DrawSurface;
/**
 * The EndScreen class represents the final screen displayed at the end of the game.
 * It displays a message indicating whether the player won or lost, along with the final score.
 */
public class EndScreen implements Animation {
    private boolean isWon;
    private Counter score;

    /**
     * Constructs an EndScreen with the specified win status and score.
     *
     * @param isWon the win status of the game (true if won, false if lost).
     * @param score the final score of the game.
     */
    public EndScreen(boolean isWon, Counter score) {
        this.isWon = isWon;
        this.score = score;
    }
    /**
     * Performs one frame of the end screen animation.
     * Draws the appropriate message and the final score on the screen.
     *
     * @param d the DrawSurface to draw on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        String msg = this.isWon ? "You Win! " : "Game Over. ";
        d.drawText(10, d.getHeight() / 2, msg + "Your score is " + this.score.getValue(), 50);
    }

    /**
     * Determines whether the end screen animation should stop.
     *
     * @return false, indicating that the end screen animation never stops.
     */
    @Override
    public boolean shouldStop() {
        return false;
    }
}
