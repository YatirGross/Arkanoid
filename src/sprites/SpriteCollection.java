// 207083395 Yatir Zeev Gross
package sprites;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The SpriteCollection class represents a collection of sprites.
 * It provides methods for adding sprites, notifying all sprites of the passage of time,
 * and drawing all sprites on a given DrawSurface.
 */
public class SpriteCollection {
    private List<Sprite> spriteList;

    /**
     * Constructs a new, empty SpriteCollection.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<>();
    }

    /**
     * Adds the specified sprite to the collection.
     *
     * @param s the sprite to add to the collection
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * Removes a sprite from the sprite list.
     *
     * @param s the sprite to be removed
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }
    /**
     * Notifies all sprites in the collection of the passage of time by calling their timePassed() method.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < this.spriteList.size(); i++) {
            this.spriteList.get(i).timePassed();
        }
    }

    /**
     * Draws all sprites in the collection on the given DrawSurface by calling their drawOn(d) method.
     *
     * @param d the DrawSurface on which to draw the sprites
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < this.spriteList.size(); i++) {
            this.spriteList.get(i).drawOn(d);
        }
    }
}
