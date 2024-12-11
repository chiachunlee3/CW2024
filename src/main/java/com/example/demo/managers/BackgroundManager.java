package com.example.demo.managers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import com.example.demo.actors.UserPlane;

import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * BackgroundManager is responsible for managing the background image of the game.
 * It initializes the background and handles user input events like key presses.
 */
public class BackgroundManager {
    private final ImageView background;
    private final double screenHeight;
    private final double screenWidth;
    private final Group root;
    private final UserPlane user;
    private final Runnable fireProjectileAction;
    private final Runnable togglePauseAction;
    private final Runnable restartGameAction;

    /**
     * Constructs a BackgroundManager instance.
     * 
     * @param backgroundImageName the name of the background image file
     * @param screenHeight the height of the game screen
     * @param screenWidth the width of the game screen
     * @param root the root Group of the scene graph
     * @param user the UserPlane representing the player's plane
     * @param fireProjectileAction the action to fire a projectile
     * @param togglePauseAction the action to toggle game pause
     * @param restartGameAction the action to restart the game
     */
    public BackgroundManager(String backgroundImageName, double screenHeight, double screenWidth, Group root, UserPlane user, 
                             Runnable fireProjectileAction, Runnable togglePauseAction, Runnable restartGameAction) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.root = root;
        this.user = user;
        this.fireProjectileAction = fireProjectileAction;
        this.togglePauseAction = togglePauseAction;
        this.restartGameAction = restartGameAction;

        this.background = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));
        initializeBackground();
    }

    /**
     * Initializes the background image view and its properties,
     * including event handling for user input.
     */
    private void initializeBackground() {
        background.setFocusTraversable(true);
        background.setFitHeight(screenHeight);
        background.setFitWidth(screenWidth);
        background.setOnKeyPressed(this::handleKeyPress);
        background.setOnKeyReleased(this::handleKeyRelease);
        root.getChildren().add(background);
    }

    /**
     * Handles key press events to perform specific actions in the game.
     * 
     * @param e the KeyEvent triggered by the user
     */
    private void handleKeyPress(KeyEvent e) {
        KeyCode kc = e.getCode();
        if (kc == KeyCode.UP) user.moveUp();
        if (kc == KeyCode.DOWN) user.moveDown();
        if (kc == KeyCode.SPACE) fireProjectileAction.run();
        if (kc == KeyCode.P) togglePauseAction.run();
        if (kc == KeyCode.R) restartGameAction.run();
    }

    /**
     * Handles key release events to stop the player's plane when applicable.
     * 
     * @param e the KeyEvent triggered by the user
     */
    private void handleKeyRelease(KeyEvent e) {
        KeyCode kc = e.getCode();
        if (kc == KeyCode.UP || kc == KeyCode.DOWN) user.stop();
    }

    /**
     * Retrieves the background image view.
     * 
     * @return the ImageView representing the background
     */
    public ImageView getBackground() {
        return background;
    }
}