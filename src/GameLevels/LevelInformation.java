// 207083395 Yatir Zeev Gross
package GameLevels;

import geometry.Velocity;
import sprites.Block;
import sprites.Sprite;

import java.util.List;
/**
 * The LevelInformation interface represents the information and specifications of a game level.
 * It provides methods to retrieve various properties of the level, such as the number of balls,
 * initial ball velocities, paddle speed, paddle width, level name, background sprite, blocks, and more.
 */
public interface LevelInformation {
    /**
     * Returns the number of balls in the level.
     *
     * @return the number of balls
     */
    int numberOfBalls();
    /**
     * Returns a list of initial velocities for the balls in the level.
     * The size of the list should match the number of balls in the level.
     *
     * @return the list of initial ball velocities
     */
    List<Velocity> initialBallVelocities();
    /**
     * Returns the speed of the paddle in the level.
     *
     * @return the paddle speed
     */
    int paddleSpeed();
    /**
     * Returns the width of the paddle in the level.
     *
     * @return the paddle width
     */
    int paddleWidth();
    /**
     * Returns the name of the level.
     * The name will be displayed at the top of the screen.
     *
     * @return the level name
     */
    String levelName();
    /**
     * Returns the background sprite of the level.
     * The sprite represents the background of the level's game screen.
     *
     * @return the background sprite
     */
    Sprite getBackground();
    /**
     * Returns a list of blocks that make up the level.
     * Each block contains its size, color, and location.
     *
     * @return the list of blocks
     */
    List<Block> blocks();
    /**
     * Returns the number of blocks that should be removed
     * before the level is considered to be "cleared".
     *
     * @return the number of blocks to remove
     */
    int numberOfBlocksToRemove();
    /**
     * Checks if the background of the level is light.
     * Light backgrounds require different visual settings for text.
     *
     * @return true if the background is light, false otherwise
     */
    boolean isLightBack();
}
