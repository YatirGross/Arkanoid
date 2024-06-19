// 207083395 Yatir Zeev Gross
package Notifier;

import sprites.Ball;
import sprites.Block;
/**
 * The ScoreTrackingListener class is responsible for tracking the score of the player.
 * It implements the HitListener interface and increases the score by a certain amount
 * whenever a block is hit.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    /**
     * Constructs a ScoreTrackingListener with the given score counter.
     *
     * @param scoreCounter The counter object to track the score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * Updates the score when a block is hit.
     *
     * @param beingHit The block being hit.
     * @param hitter   The ball that hit the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}
