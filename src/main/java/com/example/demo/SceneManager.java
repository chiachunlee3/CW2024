package com.example.demo;

import javafx.scene.Group;
import javafx.scene.Scene;

public class SceneManager {
    private final Scene scene;
    private final Group root;
    private final Group overlayGroup;
    private final Group pauseOverlay;

    public SceneManager(double screenWidth, double screenHeight) {
        this.root = new Group();
        this.overlayGroup = new Group();
        this.pauseOverlay = new Group();
        this.scene = new Scene(new Group(root, overlayGroup, pauseOverlay), screenWidth, screenHeight);
    }

    public Scene getScene() {
        return scene;
    }

    public Group getRoot() {
        return root;
    }

    public Group getOverlayGroup() {
        return overlayGroup;
    }

    public Group getPauseOverlay() {
        return pauseOverlay;
    }
}
