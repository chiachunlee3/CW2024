package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;


public class GameLoopManager {
    private final Timeline timeline;
    private final Runnable updateScene;

    public GameLoopManager(int millisecondDelay, Runnable updateScene) {
        this.updateScene = updateScene;
        this.timeline = new Timeline();
        initializeTimeline(millisecondDelay);
    }

    private void initializeTimeline(int millisecondDelay) {
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame gameLoop = new KeyFrame(Duration.millis(millisecondDelay), e -> updateScene.run());
        timeline.getKeyFrames().add(gameLoop);
    }

    public void start() {
        timeline.play();
    }

    public void stop() {
        timeline.stop();
    }

    public void pause() {
        timeline.pause();
    }

    public void play() {
        timeline.play();
    }
}
