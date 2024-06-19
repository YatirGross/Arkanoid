// 207083395 Yatir Zeev Gross
package sprites;
import Notifier.Counter;
import biuoop.DrawSurface;
import geometry.Rectangle;

import java.awt.Color;
/**
 * The ScoreIndicator class represents a sprite that displays the current score and the level name on the screen.
 */
public class ScoreIndicator implements Sprite {
    private static final int TOP_Y = 16;
    private static final int FONT_SIZE = 16;
    private static final double SCORE_PLACE = 7.0 / 15.0;
    private static final double LEVEL_PLACE = 2.0 / 3.0;

    private Rectangle shape;
    private Counter score;
    private String levelName;
    /**
     * Constructs a ScoreIndicator object with the given shape and score counter.
     *
     * @param shape The shape representing the area of the score indicator.
     * @param score The counter object representing the score.
     * @param levelName the name of the level to display on the score block.
     */
    public ScoreIndicator(Rectangle shape, Counter score, String levelName) {
        this.shape = shape;
        this.score = score;
        this.levelName = levelName;
    }
    /**
     * Draws the score indicator on the given DrawSurface.
     *
     * @param d The DrawSurface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        int x = (int) this.shape.getUpperLeft().getX();
        int y = (int) this.shape.getUpperLeft().getY();
        // Draw background rectangle
        d.setColor(Color.white);
        d.fillRectangle(x, y, (int) this.shape.getWidth(), (int) this.shape.getHeight());
        // Draw score and level's name text
        String scoreSt = "Score: " + this.score.getValue();
        String level = "Level Name: " + this.levelName;
        d.setColor(Color.black);
        d.drawText((int) (d.getWidth() * SCORE_PLACE), TOP_Y, scoreSt, FONT_SIZE);
        d.drawText((int) (d.getWidth() * LEVEL_PLACE), TOP_Y, level, FONT_SIZE);
    }
    /**
     * Do nothing.
     */
    @Override
    public void timePassed() {
    }
}
