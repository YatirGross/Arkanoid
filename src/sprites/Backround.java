// 207083395 Yatir Zeev Gross
package sprites;

import biuoop.DrawSurface;
import geometry.Point;

import java.awt.Color;
/**
 * The Background class represents the background of a game level.
 * It implements the Sprite interface and is responsible for drawing
 * the appropriate background based on the level number.
 */
public class Backround implements Sprite {
    private int level;
    /**
     * Constructs a Background object with the specified level.
     *
     * @param level the level number
     */
    public Backround(int level) {
        this.level = level;
    }
    /**
     * Draws the background on the given DrawSurface.
     *
     * @param d the DrawSurface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        switch (this.level) {
            case 1:
                this.drawLevelOne(d);
                break;
            case 2:
                this.drawLevelTwo(d);
                break;
            case 3:
                this.drawLevelThree(d);
                break;
            default:
                break;
        }
    }
    /**
     * Performs the appropriate action for the passage of time.
     * Currently, this method does nothing.
     */
    @Override
    public void timePassed() {
    }

    /**
     * Draws the background for level one.
     *
     * @param d the DrawSurface to draw on
     */
    private void drawLevelOne(DrawSurface d) {
        new Block(new Point(0, 0), d.getWidth(), d.getHeight(), Color.black).drawOn(d);
        for (int i = d.getWidth() / 2; i > 0; i -= 10) {
            if (i % 40 == 0) {
                d.setColor(Color.yellow);
                d.drawCircle(d.getWidth() / 2, d.getHeight() / 5 + 20, i);
            } else {
                d.setColor(Color.blue.brighter());
                d.drawCircle(d.getWidth() / 2, d.getHeight() / 5 + 20, i);
            }
        }
    }

    /**
     * Draws the background for level two.
     *
     * @param d the DrawSurface to draw on
     */
    private void drawLevelTwo(DrawSurface d) {
        new Block(new Point(0, 0), d.getWidth(), d.getHeight(), Color.white).drawOn(d);
        d.setColor(Color.getHSBColor(61, 34, 83));
        d.fillCircle(d.getWidth() / 6, d.getHeight() / 6, 60);
        d.setColor(Color.getHSBColor(61, 62, 68));
        d.fillCircle(d.getWidth() / 6, d.getHeight() / 6, 50);
        d.setColor(Color.getHSBColor(61, 100, 50));
        d.fillCircle(d.getWidth() / 6, d.getHeight() / 6, 40);
        d.setColor(Color.getHSBColor(61, 62, 68));
        d.fillCircle(d.getWidth() / 6, d.getHeight() / 6, 30);
        for (int i = 10; i <= d.getWidth() - 10; i += 10) {
            d.drawLine(d.getWidth() / 6, d.getHeight() / 6, i, d.getHeight() / 3);
        }
    }

    /**
     * Draws the background for level three.
     *
     * @param d the DrawSurface to draw on
     */
    private void drawLevelThree(DrawSurface d) {
        new Block(new Point(0, 0), d.getWidth(), d.getHeight(), Color.blue.darker().darker()).drawOn(d);
        d.setColor(Color.white);
        d.setColor(Color.black);
        d.fillRectangle(d.getWidth() / 8, (int) ((2.0 / 3.0) * d.getHeight()), 100, d.getHeight() / 3);
        d.fillRectangle((int) (d.getWidth() * (5.0 / 7.0)), (int) ((3.0 / 4.0) * d.getHeight()), 80, d.getHeight() / 4);
        d.fillRectangle((int) (d.getWidth() * (6.0 / 7.0)), (int) ((3.0 / 4.0) * d.getHeight()), 80, d.getHeight() / 4);
        d.setColor(Color.white);
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(d.getWidth() / 8 + j * 20 + 4, (int) ((2.0 / 3.0) * d.getHeight()) + i * 40 + 4,
                        12, 32);
                d.fillRectangle((int) (d.getWidth() * (5.0 / 7.0)) + j * 16 + 4,
                        (int) ((3.0 / 4.0) * d.getHeight()) + i * 40 + 4, 8, 32);
                d.fillRectangle((int) (d.getWidth() * (6.0 / 7.0)) + j * 16 + 4,
                        (int) ((3.0 / 4.0) * d.getHeight()) + i * 40 + 4, 8, 32);
            }
        }
        d.setColor(Color.gray.darker().darker());
        d.fillRectangle(d.getWidth() / 8 + 35, (int) ((2.0 / 3.0) * d.getHeight()) - 50, 30, 50);
        d.fillRectangle((int) (d.getWidth() * (5.0 / 7.0)) + 30, (int) ((3.0 / 4.0) * d.getHeight()) - 40, 20, 40);
        d.fillRectangle((int) (d.getWidth() * (6.0 / 7.0)) + 30, (int) ((3.0 / 4.0) * d.getHeight()) - 40, 20, 40);
        d.setColor(Color.gray.darker());
        d.fillRectangle(d.getWidth() / 8 + 45, (int) ((2.0 / 3.0) * d.getHeight()) - 200, 10, 150);
        d.fillRectangle((int) (d.getWidth() * (5.0 / 7.0)) + 36, (int) ((3.0 / 4.0) * d.getHeight()) - 100, 8, 60);
        d.fillRectangle((int) (d.getWidth() * (6.0 / 7.0)) + 36, (int) ((3.0 / 4.0) * d.getHeight()) - 100, 8, 60);
        d.setColor(Color.getHSBColor(61, 34, 83));
        d.fillCircle(d.getWidth() / 8 + 50, (int) ((2.0 / 3.0) * d.getHeight()) - 207, 14);
        d.fillCircle((int) (d.getWidth() * (5.0 / 7.0)) + 40, (int) ((3.0 / 4.0) * d.getHeight()) - 105, 10);
        d.fillCircle((int) (d.getWidth() * (6.0 / 7.0)) + 40, (int) ((3.0 / 4.0) * d.getHeight()) - 105, 10);
        d.setColor(Color.red);
        d.fillCircle(d.getWidth() / 8 + 50, (int) ((2.0 / 3.0) * d.getHeight()) - 207, 10);
        d.fillCircle((int) (d.getWidth() * (5.0 / 7.0)) + 40, (int) ((3.0 / 4.0) * d.getHeight()) - 105, 7);
        d.fillCircle((int) (d.getWidth() * (6.0 / 7.0)) + 40, (int) ((3.0 / 4.0) * d.getHeight()) - 105, 7);
        d.setColor(Color.yellow);
        d.fillCircle(d.getWidth() / 8 + 50, (int) ((2.0 / 3.0) * d.getHeight()) - 207, 5);
        d.fillCircle((int) (d.getWidth() * (5.0 / 7.0)) + 40, (int) ((3.0 / 4.0) * d.getHeight()) - 105, 3);
        d.fillCircle((int) (d.getWidth() * (6.0 / 7.0)) + 40, (int) ((3.0 / 4.0) * d.getHeight()) - 105, 3);
        d.drawLine(d.getWidth() / 8 + 50, (int) ((2.0 / 3.0) * d.getHeight()) - 207,
                (int) (d.getWidth() * (5.0 / 7.0)) + 40, (int) ((3.0 / 4.0) * d.getHeight()) - 105);
        d.drawLine(d.getWidth() / 8 + 50, (int) ((2.0 / 3.0) * d.getHeight()) - 207,
                (int) (d.getWidth() * (6.0 / 7.0)) + 40, (int) ((3.0 / 4.0) * d.getHeight()) - 105);
    }
}

