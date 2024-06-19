// 207083395 Yatir Zeev Gross

import GameLevels.LevelInformation;
import GameLevels.LevelOne;
import GameLevels.LevelThree;
import GameLevels.LevelTwo;
import GameTools.GameFlow;

import java.util.ArrayList;
import java.util.List;

/**
 * The Ass5Game class is the entry point for running the Arkanoid game.
 * It creates a new Game object, initializes it, and runs it.
 */
public class Ass6Game {
    /**
     * The main method is the entry point for running the Arkanoid game.
     * It creates a new Game object, initializes it, and runs it.
     *
     * @param args command line arguments (not used).
     */
    public static void main(String[] args) {
        List<LevelInformation> levels = getFixedList(args);
        GameFlow game = new GameFlow();
        game.runLevels(levels);
    }
    private static List<LevelInformation> getFixedList(String[] args) {
        List<LevelInformation> levels = new ArrayList<>();
        LevelInformation[] levelOption = {new LevelOne(), new LevelTwo(), new LevelThree()};
        for (String st : args) {
            try {
                int i = Integer.parseInt(st);
                if (i >= 1 && i <= 3) {
                    levels.add(levelOption[i - 1]);
                }
            } catch (Exception e) { }
        }
        if (levels.isEmpty()) {
            levels.add(new LevelOne());
            levels.add(new LevelTwo());
            levels.add(new LevelThree());
        }
        return levels;
    }
}
