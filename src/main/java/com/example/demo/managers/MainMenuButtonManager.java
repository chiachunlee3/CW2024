package com.example.demo.managers;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import com.example.demo.controller.Controller;

/**
 * Manages the Main Menu button for a JavaFX application.
 */
public class MainMenuButtonManager {

    private final Button mainMenuButton;
    private final Group root;

    /**
     * Constructor for MainMenuButtonManager.
     * Initializes the Main Menu button and sets up its appearance and behavior.
     * 
     * @param root the root group of the scene where the button will be added
     */
    public MainMenuButtonManager(Group root) {
        this.root = root;
        this.mainMenuButton = new Button("Main Menu");

        double windowWidth = root.getScene().getWidth();
        double windowHeight = root.getScene().getHeight();

        double buttonWidth = 200;
        double buttonHeight = 50;

        mainMenuButton.setLayoutX((windowWidth - buttonWidth) / 2);
        mainMenuButton.setLayoutY(windowHeight - 200);
        mainMenuButton.setPrefWidth(buttonWidth);
        mainMenuButton.setPrefHeight(buttonHeight);

        mainMenuButton.setStyle(
            "-fx-background-color: #FF4444; " +
            "-fx-text-fill: white; " +
            "-fx-font-size: 16px; " +
            "-fx-font-weight: bold; " +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 5, 0, 0, 2); " +
            "-fx-cursor: hand; " +
            "-fx-transition: all 0.3s ease;"
        );

        mainMenuButton.setOnMouseEntered(e -> {
            mainMenuButton.setStyle(
                "-fx-background-color: #FF6666; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 16px; " +
                "-fx-font-weight: bold; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 8, 0, 0, 3); " +
                "-fx-cursor: hand; " +
                "-fx-scale-x: 1.1; " +
                "-fx-scale-y: 1.1; " +
                "-fx-transition: all 0.3s ease;"
            );
        });

        mainMenuButton.setOnMouseExited(e -> {
            mainMenuButton.setStyle(
                "-fx-background-color: #FF4444; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 16px; " +
                "-fx-font-weight: bold; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 5, 0, 0, 2); " +
                "-fx-cursor: hand; " +
                "-fx-scale-x: 1.0; " +
                "-fx-scale-y: 1.0; " +
                "-fx-transition: all 0.3s ease;"
            );
        });

        mainMenuButton.setOnAction(event -> {
            Stage stage = (Stage) mainMenuButton.getScene().getWindow();
            Controller controller = new Controller(stage);
            controller.launchGame();
        });

        mainMenuButton.setVisible(false);
        root.getChildren().add(mainMenuButton);
    }

    /**
     * Returns the Main Menu button instance.
     * 
     * @return the Main Menu button
     */
    public Button getMainMenuButton() {
        return mainMenuButton;
    }

    /**
     * Shows the Main Menu button by making it visible and bringing it to the front.
     */
    public void show() {
        if (!root.getChildren().contains(mainMenuButton)) {
            root.getChildren().add(mainMenuButton);
        }
        mainMenuButton.setVisible(true);
        mainMenuButton.toFront();
    }

    /**
     * Hides the Main Menu button by making it invisible.
     */
    public void hide() {
        mainMenuButton.setVisible(false);
    }
}