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
 * The LevelTwo class implements the LevelInformation interface
 * to provide the specifications for Level 2 of the game.
 */
public class LevelTwo implements LevelInformation {
    private static final double BALL_SPEED = 3;
    private static final int BALL_AMOUNT = 10;
    private static final int PADDLE_SPEED = 5;
    private static final int PADDLE_WIDTH = 500;
    private static final double SCREEN_WIDTH = 800;
    private static final double SCREEN_HEIGHT = 600;
    private static final int BORDER_SIZE = 10;
    private static final double BLOCK_HEIGHT = 30;
    private static final double DX = 40;
    private static final double DY = 5;

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
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Backround(2);
    }

    @Override
    public List<Block> blocks() {
        Color[] c = {Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.pink, Color.cyan};
        int colorInd = 0;
        int blockWidth = (int) ((SCREEN_WIDTH - BORDER_SIZE * 2) / this.numberOfBlocksToRemove());
        List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < this.numberOfBlocksToRemove(); i++) {
            Point point = new Point(i * blockWidth + BORDER_SIZE, SCREEN_HEIGHT / 3);
            Block block = new Block(point, blockWidth, BLOCK_HEIGHT, c[colorInd / 2]);
            blocks.add(block);
            if (i != 7) {
                colorInd++;
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }

    @Override
    public boolean isLightBack() {
        return true;
    }
}