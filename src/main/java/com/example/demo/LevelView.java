package com.example.demo;

import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;

/**
 * Represents the visual components and user interface for a game level.
 * Manages the display of elements like health, kills remaining, pause text,
 * win and game over screens, and a main menu button.
 */
public class LevelView {

    /**
     * X-coordinate position of the heart display.
     */
    private static final double HEART_DISPLAY_X_POSITION = 5;

    /**
     * Y-coordinate position of the heart display.
     */
    private static final double HEART_DISPLAY_Y_POSITION = 25;

    /**
     * X-coordinate position for displaying the win image.
     */
    private static final int WIN_IMAGE_X_POSITION = 355;

    /**
     * Y-coordinate position for displaying the win image.
     */
    private static final int WIN_IMAGE_Y_POSITION = 100;

    /**
     * Width of the game over image.
     */
    private static final double GAME_OVER_IMAGE_WIDTH = 700;

    /**
     * Height of the game over image.
     */
    private static final double GAME_OVER_IMAGE_HEIGHT = 600;

    /**
     * The root group where all visual elements of the level are added.
     */
    private final Group root;

    /**
     * Image displayed when the player wins the level.
     */
    private final WinImage winImage;

    /**
     * Image displayed when the player loses the level.
     */
    private final GameOverImage gameOverImage;

    /**
     * The heart display used to show the player's remaining health.
     */
    private final HeartDisplay heartDisplay;

    /**
     * Text displayed when the game is paused.
     */
    private final Text pauseText;

    /**
     * Text displaying the remaining kills needed to complete the level.
     */
    protected final Text killsRemainingText;

    private final MainMenuButtonManager mainMenuButtonManager;

    /**
     * Instruction text for controlling the game.
     */
    private final Text instructionText;

    /**
     * Constructs a new LevelView instance.
     *
     * @param root           the root group for the level.
     * @param heartsToDisplay the number of hearts to display initially.
     */
    public LevelView(Group root, int heartsToDisplay) {
        this.root = root;

        // Initialize pause text
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

        // Initialize heart display
        this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);

        // Initialize win and game over images
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

        // Initialize kills remaining text
        killsRemainingText = new Text();
        killsRemainingText.setFont(Font.font("Monospaced", FontWeight.BOLD, 20));
        killsRemainingText.setFill(Color.WHITE);
        killsRemainingText.setX(50);
        killsRemainingText.setY(50);
        root.getChildren().add(killsRemainingText);
        killsRemainingText.toFront();

        // Create main menu button
        this.mainMenuButtonManager = new MainMenuButtonManager(root);

        // Initialize instruction text
        instructionText = new Text("R: Restart | P: Pause");
        instructionText.setFont(Font.font("Monospaced", FontWeight.BOLD, 18));
        instructionText.setFill(Color.BLACK);
        instructionText.setX(30);
        instructionText.setY(700);
    }

    /**
     * Displays the heart display on the screen.
     */
    public void showHeartDisplay() {
        root.getChildren().add(heartDisplay.getContainer());
    }

    /**
     * Displays the win image and enables the main menu button.
     */
    public void showWinImage() {
        root.getChildren().add(winImage);
        winImage.showWinImage();
        mainMenuButtonManager.show();
    }

    /**
     * Displays the game over image and enables the main menu button.
     */
    public void showGameOverImage() {
        if (!root.getChildren().contains(gameOverImage)) {
            root.getChildren().add(gameOverImage);
        }
        gameOverImage.toFront();

        // Explicitly reset button visibility
        mainMenuButtonManager.show();
    }

    /**
     * Updates the number of hearts displayed to match the given remaining health.
     *
     * @param heartsRemaining the number of hearts to display.
     */
    public void removeHearts(int heartsRemaining) {
        int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
        for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
            heartDisplay.removeHeart();
        }
    }

    /**
     * Updates the position of the pause text based on the screen size.
     */
    public void updatePauseTextPosition() {
        pauseText.setX(360);
        pauseText.setY(350);
    }

    /**
     * Shows the pause text and brings it to the front.
     */
    public void showPauseText() {
        updatePauseTextPosition();
        pauseText.setVisible(true);
        pauseText.toFront();
        mainMenuButtonManager.show();
    }

    /**
     * Hides the pause text.
     */
    public void hidePauseText() {
        pauseText.setVisible(false);

        // Do not hide the button if game-over might occur later
        if (!root.getChildren().contains(gameOverImage) || !gameOverImage.isVisible()) {
            mainMenuButtonManager.hide();
        }
    }

    /**
     * Returns the pause text node.
     *
     * @return the pause text node.
     */
    public Text getPauseText() {
        return pauseText;
    }

    /**
     * Updates the display showing the remaining kills required.
     *
     * @param killsRemaining the number of kills remaining.
     */
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

    /**
     * Returns the main menu button.
     *
     * @return the main menu button.
     */
    public Button getMainMenuButton() {
    	return mainMenuButtonManager.getMainMenuButton();
    }

    /**
     * Returns the instruction text node.
     *
     * @return the instruction text node.
     */
    public Text getInstructionText() {
        return instructionText;
    }

    /**
     * Adds a heart to the heart display.
     */
    public void addHeart() {
        heartDisplay.addHeart();
    }

    /**
     * Returns the current number of hearts displayed.
     *
     * @return the current heart count.
     */
    public int getCurrentHeartCount() {
        return heartDisplay.getContainer().getChildren().size();
    }

    /**
     * Updates the heart display to match the current health.
     *
     * @param currentHealth the player's current health.
     */
    public void updateHeartDisplay(int currentHealth) {
        int currentHeartCount = heartDisplay.getCurrentHeartCount();

        if (currentHeartCount < currentHealth) {
            for (int i = 0; i < currentHealth - currentHeartCount; i++) {
                heartDisplay.addHeart();
            }
        } else if (currentHeartCount > currentHealth) {
            for (int i = 0; i < currentHeartCount - currentHealth; i++) {
                heartDisplay.removeHeart();
            }
        }
    }
}