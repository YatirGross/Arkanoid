// 207083395 Yatir Zeev Gross
package GameLevels;

import GameTools.Animation;
import GameTools.AnimationRunner;
import GameTools.Collidable;
import GameTools.CountDownAnimation;
import GameTools.GameEnvironment;
import GameTools.KeypressStoppableAnimation;
import GameTools.PauseScreen;
import Notifier.BallRemover;
import Notifier.BlockRemover;
import Notifier.Counter;
import Notifier.ScoreTrackingListener;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import sprites.SpriteCollection;
import sprites.Sprite;
import sprites.ScoreIndicator;
import sprites.Ball;
import sprites.Block;
import sprites.Paddle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The GameLevel class represents a level in game that includes a ball, paddle, and blocks.
 * It initializes the level and runs the animation loop.
 */
public class GameLevel implements Animation {
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private static final int BLOCK_HEIGHT = 20;
    private static final int BALL_RADIUS = 5;
    private static final int BORDER_SIZE = 10;
    private static final Color PADDLE_COLOR = Color.ORANGE;
    private static final int WIN_POINTS = 100;
    private static final Color BACKROUND_COLOR = Color.BLUE.darker();
    private static final double COUNT_DOWN_SEC = 2;
    private static final int COUNT_FROM = 3;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private LevelInformation level;
    private Counter remainedBlocks;
    private Counter remainedBalls;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;

    /**
     * Constructs a GameLevel object with the specified level, score, and animation runner.
     *
     * @param level  the level information
     * @param score  the current score
     * @param runner the animation runner
     */
    public GameLevel(LevelInformation level, Counter score, AnimationRunner runner) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.level = level;
        this.score = score;
        this.runner = runner;
        this.running = true;
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Removes a collidable object from the game environment.
     *
     * @param c the collidable object to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Adds a sprite to the sprite collection.
     *
     * @param s the sprite to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Removes a sprite from the sprite collection.
     *
     * @param s the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initializes the level in the game by creating and adding the blocks, ball, and paddle.
     */
    public void initialize() {
        // Set up game objects and counters
        this.remainedBlocks = new Counter(this.level.numberOfBlocksToRemove());
        this.remainedBalls = new Counter(this.level.numberOfBalls());
        Sprite background = this.level.getBackground();
        this.sprites.addSprite(background);
        this.initializeBalls();
        // Create paddle
        Point paddleStart = new Point(SCREEN_WIDTH / 2.0 - this.level.paddleWidth() / 2.0,
                SCREEN_HEIGHT - BLOCK_HEIGHT);
        Rectangle padRect = new Rectangle(paddleStart, this.level.paddleWidth(), BLOCK_HEIGHT);
        Paddle paddle = new Paddle(this.runner.getKeyboard(), padRect, BORDER_SIZE, SCREEN_WIDTH - BORDER_SIZE,
                PADDLE_COLOR, this.level.paddleSpeed());
        paddle.addToGame(this);
        // Create border
        this.initialBorder();
        // Create score indicator
        Rectangle shape = new Rectangle(new Point(0, 0), SCREEN_WIDTH, BORDER_SIZE + BORDER_SIZE);
        ScoreIndicator scoreInd = new ScoreIndicator(shape, this.score, this.level.levelName());
        this.addSprite(scoreInd);
        // Create block remover and score tracking listener
        BlockRemover br = new BlockRemover(this, this.remainedBlocks);
        ScoreTrackingListener stl = new ScoreTrackingListener(this.score);
        for (Block block : this.level.blocks()) {
            block.addHitListener(br);
            block.addHitListener(stl);
            block.addToGame(this);
        }
    }

    /*
    Helper method to initialize the balls of the level.
     */
    private void initializeBalls() {
        this.remainedBalls = new Counter(0);
        Point start = new Point(SCREEN_WIDTH / 2, SCREEN_HEIGHT - BLOCK_HEIGHT - 1);
        List<Velocity> velocities = this.level.initialBallVelocities();
        for (int i = 0; i < this.level.numberOfBalls(); i++) {
            Ball ball = new Ball(start, BALL_RADIUS, Color.white, this.environment);
            ball.setVelocity(velocities.get(i));
            ball.addToGame(this);
        }
        this.remainedBalls = new Counter(this.level.numberOfBalls());
    }

    /**
     * Creates the initial border blocks for the game and adds them to the game environment.
     * The border is made up of 3 blocks - top, right and left, each with a gray color.
     * and a bottom block for the dead block.
     */
    private void initialBorder() {
        List<Block> borders = new ArrayList<>();
        borders.add(new Block(new Point(0, 0), BORDER_SIZE, SCREEN_HEIGHT, Color.GRAY));
        borders.add(new Block(new Point(BORDER_SIZE, BORDER_SIZE + BORDER_SIZE), SCREEN_WIDTH - BORDER_SIZE,
                BORDER_SIZE, Color.GRAY));
        borders.add(new Block(new Point(SCREEN_WIDTH - BORDER_SIZE, BORDER_SIZE), BORDER_SIZE,
                SCREEN_HEIGHT - BORDER_SIZE, Color.GRAY));
        Block deadBlock = new Block(new Point(BORDER_SIZE, SCREEN_HEIGHT), SCREEN_WIDTH - BORDER_SIZE,
                0, BACKROUND_COLOR);
        borders.add(deadBlock);
        deadBlock.addHitListener(new BallRemover(this, this.remainedBalls));
        for (Block border : borders) {
            border.addToGame(this);
        }
    }

    /**
     * Runs the level animation loop.
     * Starts an infinite loop that continuously draws the game objects onto the screen
     * and updates their behavior according to the game logic.
     * Uses a Sleeper to maintain a consistent frame rate.
     */
    public void run() {
        Color c = this.level.isLightBack() ? Color.black : Color.white;
        this.runner.run(new CountDownAnimation(COUNT_DOWN_SEC, COUNT_FROM, this.sprites, c));
        this.running = true;
        this.runner.run(this);

    }
    /**
     * Performs one frame of the game animation.
     * Draws all the game objects on the given DrawSurface,
     * updates their behavior according to the game logic,
     * and checks for game over conditions.
     *
     * @param d the DrawSurface to draw on
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.runner.getKeyboard().isPressed("p")) {
            this.runner.run(new KeypressStoppableAnimation(this.runner.getKeyboard(), KeyboardSensor.SPACE_KEY,
                    new PauseScreen()));
        }
        if (this.remainedBalls.getValue() == 0) {
            this.running = false;
        }
        if (this.remainedBlocks.getValue() == 0) {
            this.score.increase(WIN_POINTS);
            this.running = false;
        }
    }
    /**
     * Determines whether the game animation should stop.
     *
     * @return true if the game should stop, false otherwise
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }
    /**
     * Checks if there are remaining balls in the game.
     *
     * @return true if there are remaining balls, false otherwise
     */
    public boolean stillHasBalls() {
        return this.remainedBalls.getValue() > 0;
    }
}
