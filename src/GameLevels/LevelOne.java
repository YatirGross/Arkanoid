// 207083395 Yatir Zeev Gross
package GameLevels;

import geometry.Point;
import geometry.Velocity;
import sprites.Backround;
import sprites.Block;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The LevelOne class implements the LevelInformation interface
 * to provide the specifications for Level 1 of the game.
 */
public class LevelOne implements LevelInformation {
    private static final double BALL_SPEED = 5;
    private static final int BALL_AMOUNT = 1;
    private static final int PADDLE_SPEED = 10;
    private static final int PADDLE_WIDTH = 200;
    private static final double SCREEN_WIDTH = 800;
    private static final double SCREEN_HEIGHT = 600;
    private static final double BLOCK_SIZE = 40;

    @Override
    public int numberOfBalls() {
        return BALL_AMOUNT;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Random random = new Random();
        List<Velocity> lst = new ArrayList<>();
        for (int i = 0; i < this.numberOfBalls(); i++) {
            int angle = 0;
            lst.add(Velocity.fromAngleAndSpeed(angle, BALL_SPEED));
        }
        return lst;
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new Backround(1);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Point point = new Point(SCREEN_WIDTH / 2  - (BLOCK_SIZE / 2), SCREEN_HEIGHT / 5);
        Block block = new Block(point, BLOCK_SIZE, BLOCK_SIZE, Color.RED);
        blocks.add(block);
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

    @Override
    public boolean isLightBack() {
        return false;
    }
}
