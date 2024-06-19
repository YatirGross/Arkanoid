// 207083395 Yatir Zeev Gross
package Notifier;

import sprites.Ball;
import sprites.Block;
/**
 * The HitListener interface represents a listener for hit events.
 * Classes that implement this interface can listen for hit events on blocks.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit by a ball.
     *
     * @param beingHit the block that is being hit
     * @param hitter   the ball that is doing the hitting
     */
    void hitEvent(Block beingHit, Ball hitter);
}
