// 207083395 Yatir Zeev Gross
package GameTools;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import sprites.SpriteCollection;

import java.awt.Color;

/**
 * The CountDownAnimation class represents a countdown animation.
 * It displays a countdown on the screen and advances the animation
 * based on the specified number of seconds and count from value.
 */
public class CountDownAnimation implements Animation {
    private static final double MILI = 1000;
    private double numOfSecond;
    private int countFrom;
    private int currentCount;
    private SpriteCollection gameScreen;
    private Color color;
    private boolean stop;

    /**
     * Constructs a CountDownAnimation with the specified number of seconds, count from value,
     * game screen, and color.
     *
     * @param numOfSeconds the total number of seconds for the countdown.
     * @param countFrom    the starting count value for the countdown.
     * @param gameScreen   the SpriteCollection representing the game screen.
     * @param color        the color of the countdown text.
     */
    public CountDownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, Color color) {
        this.numOfSecond = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.currentCount = countFrom;
        this.color = color;
        stop = false;
    }

    /**
     * Performs one frame of the countdown animation.
     * Draws the game screen, displays the countdown text, and advances the animation.
     *
     * @param d the DrawSurface to draw on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.setColor(this.color);
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, Integer.toString(this.currentCount), 120);
        if (this.currentCount == 0) {
            this.stop = true;
        }
        Sleeper sleeper = new Sleeper();
        if (this.currentCount != this.countFrom) {
            sleeper.sleepFor((long) (this.numOfSecond * MILI / this.countFrom));
        }
        this.currentCount--;
    }

    /**
     * Determines whether the countdown animation should stop.
     *
     * @return true if the countdown animation should stop, false otherwise.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
