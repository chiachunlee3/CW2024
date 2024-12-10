package com.example.demo.managers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import com.example.demo.actors.UserPlane;

import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class BackgroundManager {
    private final ImageView background;
    private final double screenHeight;
    private final double screenWidth;
    private final Group root;
    private final UserPlane user;
    private final Runnable fireProjectileAction;
    private final Runnable togglePauseAction;
    private final Runnable restartGameAction;

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

    private void initializeBackground() {
        background.setFocusTraversable(true);
        background.setFitHeight(screenHeight);
        background.setFitWidth(screenWidth);
        background.setOnKeyPressed(this::handleKeyPress);
        background.setOnKeyReleased(this::handleKeyRelease);
        root.getChildren().add(background);
    }

    private void handleKeyPress(KeyEvent e) {
        KeyCode kc = e.getCode();
        if (kc == KeyCode.UP) user.moveUp();
        if (kc == KeyCode.DOWN) user.moveDown();
        if (kc == KeyCode.SPACE) fireProjectileAction.run();
        if (kc == KeyCode.P) togglePauseAction.run();
        if (kc == KeyCode.R) restartGameAction.run();
    }

    private void handleKeyRelease(KeyEvent e) {
        KeyCode kc = e.getCode();
        if (kc == KeyCode.UP || kc == KeyCode.DOWN) user.stop();
    }

    public ImageView getBackground() {
        return background;
    }
}
