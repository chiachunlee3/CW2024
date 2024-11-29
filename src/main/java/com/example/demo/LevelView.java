package com.example.demo;

import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import com.example.demo.controller.Controller;


public class LevelView {

    private static final double HEART_DISPLAY_X_POSITION = 5;
    private static final double HEART_DISPLAY_Y_POSITION = 25;
    private static final int WIN_IMAGE_X_POSITION = 355;
    private static final int WIN_IMAGE_Y_POSITION = 100;
    private static final double GAME_OVER_IMAGE_WIDTH = 700;
    private static final double GAME_OVER_IMAGE_HEIGHT = 600;
    private final Group root;
    private final WinImage winImage;
    private final GameOverImage gameOverImage;
    private final HeartDisplay heartDisplay;
    private final Text pauseText;
    protected final Text killsRemainingText;
    private Button mainMenuButton;

    public LevelView(Group root, int heartsToDisplay) {
        this.root = root;

        pauseText = new Text("Game Paused!");
        pauseText.setFont(Font.font("Monospaced", FontWeight.BOLD, 80));
        pauseText.setFill(Color.WHITE);

        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.BLACK);
        shadow.setRadius(10);
        shadow.setOffsetX(3);
        shadow.setOffsetY(3);
        pauseText.setEffect(shadow);

        updatePauseTextPosition();
        pauseText.setVisible(false);
        root.getChildren().add(pauseText);

        this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
        this.winImage = new WinImage(WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION);

        double windowWidth = root.getScene().getWidth();
        double windowHeight = root.getScene().getHeight();
        int lossScreenXPosition = (int) (windowWidth / 2 - GAME_OVER_IMAGE_WIDTH / 2);
        int lossScreenYPosition = (int) (windowHeight / 2 - GAME_OVER_IMAGE_HEIGHT / 2 - 40);
        this.gameOverImage = new GameOverImage(
                lossScreenXPosition,
                lossScreenYPosition,
                GAME_OVER_IMAGE_WIDTH,
                GAME_OVER_IMAGE_HEIGHT
        );

        killsRemainingText = new Text();
        killsRemainingText.setFont(Font.font("Monospaced", FontWeight.BOLD, 20));
        killsRemainingText.setFill(Color.WHITE);
        killsRemainingText.setX(50);
        killsRemainingText.setY(50);
        root.getChildren().add(killsRemainingText);

        killsRemainingText.toFront();
        
        createMainMenuButton();
    }

    public void showHeartDisplay() {
        root.getChildren().add(heartDisplay.getContainer());
    }

    public void showWinImage() {
        root.getChildren().add(winImage);
        winImage.showWinImage();
        if (!root.getChildren().contains(mainMenuButton)) {
            root.getChildren().add(mainMenuButton);
        }
        mainMenuButton.setVisible(true);
        mainMenuButton.toFront();
    }

    public void showGameOverImage() {
        root.getChildren().add(gameOverImage);
        if (!root.getChildren().contains(mainMenuButton)) {
            root.getChildren().add(mainMenuButton);
        }
        mainMenuButton.setVisible(true);
        mainMenuButton.toFront();
    }

    public void removeHearts(int heartsRemaining) {
        int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
        for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
            heartDisplay.removeHeart();
        }
    }

    public void updatePauseTextPosition() {
        pauseText.setX(360);
        pauseText.setY(350);
    }

    public void showPauseText() {
        updatePauseTextPosition();
        pauseText.setVisible(true);
        pauseText.toFront();
        mainMenuButton.setVisible(true);
        mainMenuButton.toFront();
    }

    public void hidePauseText() {
        pauseText.setVisible(false);
        mainMenuButton.setVisible(false);
    }

    public Text getPauseText() {
        return pauseText;
    }

    public void updateKillsRemaining(int killsRemaining) {
        double padding = 20; 
        double textWidthMargin = 300;
        double xPosition = 1300 - textWidthMargin - padding;
        double yPosition = padding + 30;

        killsRemainingText.setText("Kills Remaining: " + killsRemaining);
        killsRemainingText.setX(xPosition);
        killsRemainingText.setY(yPosition);

        killsRemainingText.setVisible(true);
        killsRemainingText.toFront();
    }
    
    private void createMainMenuButton() {
        mainMenuButton = new Button("Main Menu");
        
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
            javafx.stage.Stage stage = (javafx.stage.Stage) mainMenuButton.getScene().getWindow();
            Controller controller = new Controller(stage);
            controller.launchGame();
        });

        mainMenuButton.setVisible(false);
        root.getChildren().add(mainMenuButton);
    }
    
    public Button getMainMenuButton() {
        return mainMenuButton;
    }
}
