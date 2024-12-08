package com.example.demo.controller;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import com.example.demo.LevelParent;
import com.example.demo.MainMenu;
import com.example.demo.TransitionScreen;

import java.lang.reflect.Constructor;

import javafx.animation.FadeTransition;
import javafx.scene.Group;
import javafx.util.Duration;

/**
 * The {@code Controller} class manages the game flow, including transitions between levels,
 * displaying the main menu, and handling errors. It also provides functionality for pausing
 * the game and restarting levels.
 */
public class Controller {

    private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.LevelOne"; // Default first level
    private final Stage stage; // The primary stage for displaying scenes
    private MainMenu mainMenu; // The main menu instance
    private boolean isPaused = false; // Tracks whether the game is paused

    /**
     * Constructs a {@code Controller} object.
     *
     * @param stage the primary stage of the application.
     */
    public Controller(Stage stage) {
        this.stage = stage;
    }

    /**
     * Launches the game by displaying the main menu.
     */
    public void launchGame() {
        showMainMenu();
        stage.show();
    }

    /**
     * Displays the main menu. Initializes it if it hasn't been created yet.
     */
    private void showMainMenu() {
        if (mainMenu == null) {
            mainMenu = new MainMenu(stage.getWidth(), stage.getHeight());
            mainMenu.setOnStartGame(() -> {
                try {
                    goToLevelWithTransition(LEVEL_ONE_CLASS_NAME);
                } catch (Exception e) {
                    showError(e);
                }
            });
        }
        stage.setScene(mainMenu.getScene());
    }

    /**
     * Handles transitions to the next level.
     *
     * @param nextLevelClassName the class name of the next level.
     */
    private void handleLevelChange(String nextLevelClassName) {
        goToLevelWithTransition(nextLevelClassName);
    }

    /**
     * Displays an error message in an alert dialog.
     *
     * @param e the exception that occurred.
     */
    private void showError(Exception e) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("An error occurred");
        alert.setContentText(e.getMessage());
        alert.show();
    }

    /**
     * Toggles the pause state of the game.
     */
    public void togglePause() {
        isPaused = !isPaused;
    }

    /**
     * Checks if the game is currently paused.
     *
     * @return {@code true} if the game is paused; {@code false} otherwise.
     */
    public boolean isPaused() {
        return isPaused;
    }

    /**
     * Transitions to a specified level with a transition effect.
     *
     * @param className the class name of the level to transition to.
     */
    private void goToLevelWithTransition(String className) {
        String levelText = "Level 1"; // Default to Level 1
        if (className.equals("com.example.demo.LevelTwo")) {
            levelText = "Level 2";
        } else if (className.equals("com.example.demo.LevelThree")) {
            levelText = "Level 3";
        }

        Scene transitionScene = new TransitionScreen(stage.getWidth(), stage.getHeight(), levelText, () -> {
            try {
                Class<?> myClass = Class.forName(className);
                Constructor<?> constructor = myClass.getConstructor(double.class, double.class, Controller.class);
                LevelParent myLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth(), this);
                myLevel.setOnLevelChange(this::handleLevelChange);

                Scene gameScene = myLevel.initializeScene();

                // Apply fade-in transition to the game scene
                Group root = (Group) gameScene.getRoot();
                root.setOpacity(0);

                FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), root);
                fadeIn.setFromValue(0);
                fadeIn.setToValue(1);
                fadeIn.setOnFinished(event -> myLevel.startGame());

                stage.setScene(gameScene);
                fadeIn.play();
            } catch (Exception e) {
                showError(e);
            }
        }).getScene();

        stage.setScene(transitionScene);
    }

    /**
     * Restarts the current level. If no current level is active, it defaults to Level One.
     */
    public void restartLevel() {
        Scene currentScene = stage.getScene();
        if (currentScene != null) {
            Object currentRoot = currentScene.getRoot();
            if (currentRoot instanceof LevelParent) {
                LevelParent currentLevel = (LevelParent) currentRoot;
                try {
                    String className = currentLevel.getClass().getName();
                    goToLevelWithTransition(className);
                } catch (Exception e) {
                    System.err.println("Error restarting level: " + e.getMessage());
                    showError(e);
                }
            } else {
                // Default to Level One
                try {
                    goToLevelWithTransition("com.example.demo.LevelOne");
                } catch (Exception e) {
                    System.err.println("Failed to reset to LevelOne: " + e.getMessage());
                    showError(e);
                }
            }
        } else {
            System.err.println("Current scene is null.");
        }
    }
}