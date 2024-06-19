// 207083395 Yatir Zeev Gross
package GameTools;

import GameLevels.GameLevel;
import GameLevels.LevelInformation;
import Notifier.Counter;
import biuoop.KeyboardSensor;

import java.util.List;
/**
 * The GameFlow class is responsible for running a sequence of levels in the game.
 * It manages the transition between levels and displays the end screen with the final score.
 */
public class GameFlow {
    private static final int FRAMES_PER_SEC = 60;
    private AnimationRunner animationRunner;
    private Counter score;
    /**
     * Constructs a GameFlow object.
     * Initializes the animation runner and score counter.
     */
    public GameFlow() {
        this.animationRunner = new AnimationRunner(FRAMES_PER_SEC);
        this.score = new Counter(0);
    }
    /**
     * Runs the given list of levels in sequence.
     *
     * @param levels the list of level information objects representing the levels to run.
     */
    public void runLevels(List<LevelInformation> levels) {
        Boolean isWon = true;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.score, this.animationRunner);
            level.initialize();
            while (!level.shouldStop()) {
                level.run();
            }
            if (!level.stillHasBalls()) {
                isWon = false;
                break;
            }
        }
        this.animationRunner.run(new KeypressStoppableAnimation(this.animationRunner.getKeyboard(),
                KeyboardSensor.SPACE_KEY, new EndScreen(isWon, this.score)));
        this.animationRunner.closeAnimation();
    }
}
