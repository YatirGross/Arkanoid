# Arkanoid Game OOP Project in Java

Welcome to the Arkanoid Game OOP Project! This project is an implementation of the classic Arkanoid game using Object-Oriented Programming principles in Java.

## Project Structure

### GameLevels
- `GameLevel.java`: Manages game logic for each level.
- `LevelInformation.java`: Interface for level information.
- `LevelOne.java`, `LevelTwo.java`, `LevelThree.java`: Implementations of different game levels.

### GameTools
- `Animation.java`: Interface for animations.
- `AnimationRunner.java`: Runs animations.
- `Collidable.java`: Interface for collidable objects.
- `CollisionInfo.java`: Information about collisions.
- `CountDownAnimation.java`: Countdown animation before the game starts.
- `EndScreen.java`: End screen animation.
- `GameEnvironment.java`: Manages the game environment.
- `GameFlow.java`: Manages the game flow.
- `KeypressStoppableAnimation.java`: Animation that can be stopped with a key press.
- `PauseScreen.java`: Pause screen animation.

### Notifier
- `BallRemover.java`: Listens for ball removal events.
- `BlockRemover.java`: Listens for block removal events.
- `Counter.java`: Utility class for counting.
- `HitListener.java`: Interface for hit events.
- `HitNotifier.java`: Interface for notifying hit events.
- `ScoreTrackingListener.java`: Listens for score updates.

### Geometry
- `Line.java`: Represents a line segment.
- `Point.java`: Represents a point in 2D space.
- `Rectangle.java`: Represents a rectangle.
- `Velocity.java`: Represents velocity.

### Sprites
- `Backround.java`: Represents the game background.
- `Ball.java`: Represents a ball.
- `Block.java`: Represents a block.
- `Paddle.java`: Represents the paddle.
- `ScoreIndicator.java`: Displays the score.
- `Sprite.java`: Interface for sprites.
- `SpriteCollection.java`: Manages a collection of sprites.

## Demo
<!-- For the first GIF -->
<img src="https://github.com/YatirGross/Arkanoid/assets/155381822/05dbc3fd-d921-42fe-92c2-7ee566347cb7" alt="level arkanoid gif" style="width: 600px; height: auto;">

<!-- For the second GIF -->
<img src="https://github.com/YatirGross/Arkanoid/assets/155381822/93c220c9-67a8-4da2-bd67-6d07d5a5473c" alt="level 2 arkanoid gif" style="width: 600px; height: auto;">



## Installation and Running the Game

To run this project locally, follow these steps:

1. **Clone the repository:**
    ```bash
    git clone https://github.com/your-username/arkanoid-game.git
    cd arkanoid-game
    ```

2. **Compile the project:**
    ```bash
    javac -d bin src/**/*.java
    ```

    This command compiles all Java files (`*.java`) from the `src` directory and its subdirectories (`**`) and stores the compiled `.class` files in the `bin` directory.

3. **Run the game:**
    ```bash
    java -cp bin GameRunner
    ```

    This command runs the game using the compiled bytecode (`bin` directory) and starts the game by executing the `GameRunner` class.

Make sure you have Java Development Kit (JDK) installed and configured correctly on your system to compile and run Java programs from the command line. Adjust paths and commands as necessary based on your specific development environment or project structure.

Usage
The game consists of several levels, each with unique configurations and challenges. Control a paddle to bounce a ball and break blocks. Track your score and handle game events like level completion and game over.

Contributing
Contributions are welcome! Please open an issue or submit a pull request for any improvements or new features.
