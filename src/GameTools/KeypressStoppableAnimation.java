// 207083395 Yatir Zeev Gross
package GameTools;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * A class representing a keypress stoppable animation.
 * It allows the animation to be stopped by pressing a specific key on the keyboard.
 */
public class KeypressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;
    /**
     * Constructs a new KeypressStoppableAnimation.
     *
     * @param sensor    the KeyboardSensor used to check for keypresses
     * @param key       the key that stops the animation when pressed
     * @param animation the animation to be stopped
     */
    public KeypressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }
    /**
     * Performs one frame of the animation.
     * If the specified key is pressed, it stops the animation.
     *
     * @param d the DrawSurface on which to draw the animation
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.sensor.isPressed(key)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }
    /**
     * Checks if the animation should stop.
     *
     * @return true if the animation should stop, false otherwise
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
