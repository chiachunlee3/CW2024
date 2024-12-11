package com.example.demo.levels;

import javafx.animation.PauseTransition;
import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.effect.DropShadow;
import javafx.util.Duration;
import java.util.function.Consumer;

import com.example.demo.managers.GameLoopManager;

/**
 * Manages level transitions, including displaying messages and triggering callbacks.
 * This class is responsible for stopping the game loop, showing a transition message,
 * and triggering a level change callback after a delay.
 */
public class LevelTransitionManager {
    private final Group root;
    private final double screenWidth;
    private final double screenHeight;
    private Consumer<String> levelChangeCallback;
    private final GameLoopManager gameLoopManager; // Add this dependency

    /**
     * Creates a new LevelTransitionManager.
     *
     * @param root the root node to which transition elements will be added
     * @param screenWidth the width of the screen
     * @param screenHeight the height of the screen
     * @param gameLoopManager the game loop manager for controlling game states
     */
    public LevelTransitionManager(Group root, double screenWidth, double screenHeight, GameLoopManager gameLoopManager) {
        this.root = root;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.gameLoopManager = gameLoopManager; // Initialize it
    }

    /**
     * Sets the callback to be executed when a level change occurs.
     *
     * @param callback a Consumer that accepts the name of the next level
     */
    public void setOnLevelChange(Consumer<String> callback) {
        this.levelChangeCallback = callback;
    }

    /**
     * Transitions to the next level by displaying a message, stopping the game loop,
     * and executing the level change callback after a short delay.
     *
     * @param levelName the name of the next level
     */
    public void goToNextLevel(String levelName) {
        if (levelChangeCallback != null) {
            // Stop the game loop
            gameLoopManager.stop();

            // Display "Level Cleared" message
            Text levelClearedText = createLevelClearedText();
            root.getChildren().add(levelClearedText);

            // Delay before transitioning
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> {
                root.getChildren().remove(levelClearedText); // Clean up the message
                levelChangeCallback.accept(levelName);
            });
            pause.play();
        }
    }

    /**
     * Creates a "Level Cleared" text element for display.
     *
     * @return a Text object configured to display the "Level Cleared" message
     */
    private Text createLevelClearedText() {
        Text levelClearedText = new Text("Level Cleared!");
        levelClearedText.setFont(Font.font("Monospaced", FontWeight.BOLD, 100));
        levelClearedText.setFill(Color.RED);
        levelClearedText.setEffect(new DropShadow(5, Color.BLACK));
        levelClearedText.setX(screenWidth / 2 - 420); // Center horizontally
        levelClearedText.setY(screenHeight / 2);      // Center vertically
        return levelClearedText;
    }
}