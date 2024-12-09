package com.example.demo;

import javafx.animation.PauseTransition;
import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.effect.DropShadow;
import javafx.util.Duration;
import java.util.function.Consumer;

/**
 * Manages level transitions, including displaying messages and triggering callbacks.
 */
public class LevelTransitionManager {
    private final Group root;
    private final double screenWidth;
    private final double screenHeight;
    private Consumer<String> levelChangeCallback;
    private final GameLoopManager gameLoopManager; // Add this dependency

    public LevelTransitionManager(Group root, double screenWidth, double screenHeight, GameLoopManager gameLoopManager) {
        this.root = root;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.gameLoopManager = gameLoopManager; // Initialize it
    }

    public void setOnLevelChange(Consumer<String> callback) {
        this.levelChangeCallback = callback;
    }

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

