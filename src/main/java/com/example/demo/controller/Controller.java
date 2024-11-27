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

public class Controller {
    private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.LevelOne";
    private final Stage stage;
    private MainMenu mainMenu;

    public Controller(Stage stage) {
        this.stage = stage;
    }

    public void launchGame() {
        showMainMenu();
        stage.show();
    }

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

    private void handleLevelChange(String nextLevelClassName) {
        	goToLevelWithTransition(nextLevelClassName);
    }

    private void showError(Exception e) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("An error occurred");
        alert.setContentText(e.getMessage());
        alert.show();
    }
    
    private boolean isPaused = false;

    public void togglePause() {
        isPaused = !isPaused;
    }

    public boolean isPaused() {
        return isPaused;
    }
    private void goToLevelWithTransition(String className) {
    	// Determine the level text dynamically
        String levelText = "Level 1"; // Default to Level 1
        if (className.equals("com.example.demo.LevelTwo")) {
            levelText = "Level 2";
        }
        
        Scene transitionScene = new TransitionScreen(stage.getWidth(), stage.getHeight(), levelText, () -> {
            try {
                // Load the actual game level
                Class<?> myClass = Class.forName(className);
                Constructor<?> constructor = myClass.getConstructor(double.class, double.class);
                LevelParent myLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth());
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
}