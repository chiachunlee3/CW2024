package com.example.demo.states;

import com.example.demo.levels.LevelView;
import com.example.demo.managers.GameLoopManager;

import javafx.scene.Group;
import javafx.scene.effect.GaussianBlur;

/**
 * Manages the pause functionality for a game.
 */
public class PauseManager {
    private boolean isPaused = false;
    private final GaussianBlur blurEffect = new GaussianBlur(10);
    private final Group pauseOverlay;
    private final Group root;
    private final LevelView levelView;
    private final GameLoopManager gameLoopManager;

    public PauseManager(GameLoopManager gameLoopManager, Group root, Group pauseOverlay, LevelView levelView) {
        this.gameLoopManager = gameLoopManager;
        this.root = root;
        this.pauseOverlay = pauseOverlay;
        this.levelView = levelView;
    }

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

    public boolean isPaused() {
        return isPaused;
    }
}
