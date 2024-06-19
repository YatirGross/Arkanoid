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
 * The LevelThree class implements the LevelInformation interface
 * to provide the specifications for Level 3 of the game.
 */
public class LevelThree implements LevelInformation {
    private static final int BALL_AMOUNT = 2;
    private static final double BALL_SPEED = 4;
    private static final int PADDLE_SPEED = 6;
    private static final int PADDLE_WIDTH = 120;
    private static final double SCREEN_WIDTH = 800;
    private static final double SCREEN_HEIGHT = 600;
    private static final int BLOCK_WIDTH = 60;
    private static final int BLOCK_HEIGHT = 20;
    private static final int ROWS_NUMBER = 6;
    private static final int START_ROW = 3;
    private static final int BORDER_SIZE = 10;
    private static final double DX = 100;

    @Override
    public int numberOfBalls() {
        return BALL_AMOUNT;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Random random = new Random();
        List<Velocity> lst = new ArrayList<>();
        for (int i = 0; i < this.numberOfBalls(); i++) {
            int angle = random.nextInt(70) + random.nextInt(2) * 290;
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
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Backround(3);
    }

    @Override
    public List<Block> blocks() {
        Color[] c = {Color.GREEN, Color.MAGENTA, Color.RED, Color.PINK, Color.CYAN, Color.yellow};
        List<Block> blocks = new ArrayList<>();
        for (int i = START_ROW; i < ROWS_NUMBER + START_ROW; i++) {
            for (int j = i; j < (SCREEN_WIDTH - (BORDER_SIZE * 2)) / BLOCK_WIDTH; j++) {
                Point point = new Point(j * BLOCK_WIDTH + BORDER_SIZE, i * BLOCK_HEIGHT + BORDER_SIZE);
                Block block = new Block(point, BLOCK_WIDTH, BLOCK_HEIGHT, c[i % c.length]);
                blocks.add(block);
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        int count = 0;
        for (int i = START_ROW; i < ROWS_NUMBER + START_ROW; i++) {
            for (int j = i; j < (SCREEN_WIDTH - (BORDER_SIZE * 2)) / BLOCK_WIDTH; j++) {
                count++;
            }
        }
        return count;
    }
    @Override
    public boolean isLightBack() {
        return false;
    }
}
