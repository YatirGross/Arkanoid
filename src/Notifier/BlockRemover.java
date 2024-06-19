// 207083395 Yatir Zeev Gross
package Notifier;

import GameLevels.GameLevel;
import sprites.Ball;
import sprites.Block;
/**
 * The BlockRemover class is responsible for removing blocks from the game
 * when they are hit by the ball. It implements the HitListener interface.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;
    /**
     * Constructs a BlockRemover object.
     *
     * @param gameLevel           the game to remove blocks from
     * @param remainingBlocks  the counter of remaining blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }
    /**
     * Called when a block is hit by a ball.
     * Removes the block from the game and decreases the counter of remaining blocks.
     *
     * @param beingHit  the block being hit
     * @param hitter    the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
        remainingBlocks.decrease(1);
    }
}
