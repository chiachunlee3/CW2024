package com.example.demo.managers;

import javafx.scene.Group;
import javafx.scene.Scene;

/**
 * The SceneManager class manages the primary scene and its associated groups,
 * including the root, overlay, and pause overlay groups.
 */
public class SceneManager {

    private final Scene scene;
    private final Group root;
    private final Group overlayGroup;
    private final Group pauseOverlay;

    /**
     * Constructs a SceneManager with specified screen width and height.
     *
     * @param screenWidth  the width of the scene
     * @param screenHeight the height of the scene
     */
    public SceneManager(double screenWidth, double screenHeight) {
        this.root = new Group();
        this.overlayGroup = new Group();
        this.pauseOverlay = new Group();
        this.scene = new Scene(new Group(root, overlayGroup, pauseOverlay), screenWidth, screenHeight);
    }

    /**
     * Gets the main scene.
     *
     * @return the scene managed by this SceneManager
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Gets the root group of the scene.
     *
     * @return the root group
     */
    public Group getRoot() {
        return root;
    }

    /**
     * Gets the overlay group of the scene.
     *
     * @return the overlay group
     */
    public Group getOverlayGroup() {
        return overlayGroup;
    }

    /**
     * Gets the pause overlay group of the scene.
     *
     * @return the pause overlay group
     */
    public Group getPauseOverlay() {
        return pauseOverlay;
    }
}