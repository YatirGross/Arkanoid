// 207083395 Yatir Zeev Gross
package Notifier;

import GameLevels.GameLevel;
import sprites.Ball;
import sprites.Block;
/**
 * BallRemover is a class that implements the HitListener interface
 * to remove balls from the game and update the remaining ball counter.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;
    /**
     * Constructs a BallRemover object.
     *
     * @param gameLevel           the game instance
     * @param remainingBalls the counter for remaining balls
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }
    /**
     * This method is called whenever a ball hits the dead block.
     * Removes the ball from the game and decreases the remaining ball count.
     *
     * @param beingHit the block being hit
     * @param hitter   the ball that hits the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        remainingBalls.decrease(1);
    }
}
