package com.example.demo.levels;

import com.example.demo.managers.InstructionTextManager;
import com.example.demo.managers.KillsRemainingTextManager;
import com.example.demo.managers.MainMenuButtonManager;
import com.example.demo.managers.PauseTextManager;
import com.example.demo.states.GameOverImage;
import com.example.demo.states.WinImage;
import com.example.demo.visuals.HeartDisplay;

import javafx.scene.Group;
import javafx.scene.text.Text;
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
     * Text displaying the remaining kills needed to complete the level.
     */
    protected final KillsRemainingTextManager killsRemainingTextManager;

    private final MainMenuButtonManager mainMenuButtonManager;
    
    private final PauseTextManager pauseTextManager;
    
    private final InstructionTextManager instructionTextManager;

    /**
     * Constructs a new LevelView instance.
     *
     * @param root           the root group for the level.
     * @param heartsToDisplay the number of hearts to display initially.
     */
    public LevelView(Group root, int heartsToDisplay) {
        this.root = root;
        
     // Initialize pause text manager
        this.pauseTextManager = new PauseTextManager(root);
       
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

        this.killsRemainingTextManager = new KillsRemainingTextManager(root);
        
        // Create main menu button
        this.mainMenuButtonManager = new MainMenuButtonManager(root);

        this.instructionTextManager = new InstructionTextManager(root);
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
     * Shows the pause text and brings it to the front.
     */
    public void showPauseText() {
    	pauseTextManager.showPauseText();
        mainMenuButtonManager.show();
    }

    /**
     * Hides the pause text.
     */
    public void hidePauseText() {
    	 pauseTextManager.hidePauseText();

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
    	 return pauseTextManager.getPauseText();
    }

    /**
     * Updates the display showing the remaining kills required.
     *
     * @param killsRemaining the number of kills remaining.
     */
    public void updateKillsRemaining(int killsRemaining) {
    	 killsRemainingTextManager.updateKillsRemaining(killsRemaining);
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
    	return instructionTextManager.getInstructionText();
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