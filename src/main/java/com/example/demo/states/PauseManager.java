package com.example.demo.states;

import com.example.demo.levels.LevelView;
import com.example.demo.managers.GameLoopManager;

import javafx.scene.Group;
import javafx.scene.effect.GaussianBlur;

/**
 * Manages the pause functionality for a game.
 */
public class PauseManager {

    /**
     * Indicates whether the game is currently paused.
     */
    private boolean isPaused = false;

    /**
     * The Gaussian blur effect applied when the game is paused.
     */
    private final GaussianBlur blurEffect = new GaussianBlur(10);

    /**
     * The overlay group for displaying pause-related UI elements.
     */
    private final Group pauseOverlay;

    /**
     * The root group containing the main game scene.
     */
    private final Group root;

    /**
     * The LevelView instance managing level-specific visuals and UI elements.
     */
    private final LevelView levelView;

    /**
     * The GameLoopManager instance controlling the game loop state.
     */
    private final GameLoopManager gameLoopManager;

    /**
     * Constructs a PauseManager instance.
     *
     * @param gameLoopManager the manager controlling the game loop state
     * @param root the root group of the game scene
     * @param pauseOverlay the overlay group for pause UI
     * @param levelView the level view managing level-specific visuals
     */
    public PauseManager(GameLoopManager gameLoopManager, Group root, Group pauseOverlay, LevelView levelView) {
        this.gameLoopManager = gameLoopManager;
        this.root = root;
        this.pauseOverlay = pauseOverlay;
        this.levelView = levelView;
    }

    /**
     * Toggles the paused state of the game.
     * When paused, applies visual effects and shows pause UI elements.
     * When unpaused, restores the game state and clears pause UI.
     */
    public void togglePause() {
        isPaused = !isPaused;
        if (isPaused) {
            gameLoopManager.pause();
            levelView.showPauseText();
            root.setEffect(blurEffect);
            pauseOverlay.getChildren().add(levelView.getPauseText());
            pauseOverlay.getChildren().add(levelView.getMainMenuButton());
            pauseOverlay.setMouseTransparent(false);
        } else {
            gameLoopManager.play();
            levelView.hidePauseText();
            root.setEffect(null);
            pauseOverlay.getChildren().clear();
        }
    }

    /**
     * Checks if the game is currently paused.
     *
     * @return true if the game is paused, false otherwise
     */
    public boolean isPaused() {
        return isPaused;
    }
}