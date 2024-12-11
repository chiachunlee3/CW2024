package com.example.demo.managers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * Manages the game loop using a JavaFX Timeline.
 * Provides functionality to start, stop, pause, and resume the game loop.
 */
public class GameLoopManager {
    private final Timeline timeline;
    private final Runnable updateScene;

    /**
     * Constructs a GameLoopManager with the specified delay and update logic.
     *
     * @param millisecondDelay the delay in milliseconds between each frame of the game loop
     * @param updateScene the Runnable containing the logic to execute on each frame
     */
    public GameLoopManager(int millisecondDelay, Runnable updateScene) {
        this.updateScene = updateScene;
        this.timeline = new Timeline();
        initializeTimeline(millisecondDelay);
    }

    /**
     * Initializes the Timeline with a KeyFrame using the specified delay.
     *
     * @param millisecondDelay the delay in milliseconds between each frame
     */
    private void initializeTimeline(int millisecondDelay) {
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame gameLoop = new KeyFrame(Duration.millis(millisecondDelay), e -> updateScene.run());
        timeline.getKeyFrames().add(gameLoop);
    }

    /**
     * Starts the game loop.
     */
    public void start() {
        timeline.play();
    }

    /**
     * Stops the game loop.
     */
    public void stop() {
        timeline.stop();
    }

    /**
     * Pauses the game loop.
     */
    public void pause() {
        timeline.pause();
    }

    /**
     * Resumes the game loop.
     */
    public void play() {
        timeline.play();
    }
}