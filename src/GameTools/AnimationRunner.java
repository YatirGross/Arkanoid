// 207083395 Yatir Zeev Gross
package GameTools;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
/**
 * The AnimationRunner class is responsible for running animations in the game.
 * It handles the animation loop and timing of frames.
 */
public class AnimationRunner {
    private static final int MILLI = 1000;
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private GUI gui;
    private int framePerSecond;
    private Sleeper sleeper;
    /**
     * Constructs an AnimationRunner with the specified frame rate.
     *
     * @param framePerSecond the frame rate at which animations should run.
     */
    public AnimationRunner(int framePerSecond) {
        this.gui = new GUI("The best game ever", SCREEN_WIDTH, SCREEN_HEIGHT);
        this.framePerSecond = framePerSecond;
        this.sleeper = new Sleeper();
    }
    /**
     * Returns the KeyboardSensor of the GUI.
     *
     * @return the KeyboardSensor object.
     */
    public KeyboardSensor getKeyboard() {
        return this.gui.getKeyboardSensor();
    }
    /**
     * Runs the given animation.
     * Performs the animation loop, updating frames at the specified frame rate.
     *
     * @param animation the animation to run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = MILLI / this.framePerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis();
            DrawSurface d = this.gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
    /**
     * Closes the animation window.
     * Should be called when the game has finished.
     */
    public void closeAnimation() {
        this.gui.close();
    }
}
