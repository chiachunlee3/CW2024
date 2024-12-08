package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a heart display in the game, used to visually indicate
 * the player's health using heart icons. The hearts are displayed in a
 * horizontal box (HBox) and can be dynamically added or removed.
 */
public class HeartDisplay {

    /**
     * The path to the heart image resource file.
     */
    private static final String HEART_IMAGE_NAME = "/com/example/demo/images/heart.png";

    /**
     * The height of each heart image in pixels.
     */
    private static final int HEART_HEIGHT = 50;

    /**
     * The index of the first item in the container, used when removing a heart.
     */
    private static final int INDEX_OF_FIRST_ITEM = 0;

    /**
     * The container for displaying the heart images in a horizontal layout.
     */
    private HBox container;

    /**
     * The x-coordinate of the container's layout position.
     */
    private double containerXPosition;

    /**
     * The y-coordinate of the container's layout position.
     */
    private double containerYPosition;

    /**
     * The initial number of hearts to display.
     */
    private int numberOfHeartsToDisplay;

    /**
     * Constructs a new {@code HeartDisplay} instance with specified position
     * and the number of hearts to display initially.
     *
     * @param xPosition       the x-coordinate for the container's layout.
     * @param yPosition       the y-coordinate for the container's layout.
     * @param heartsToDisplay the number of hearts to display initially.
     */
    public HeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
        this.containerXPosition = xPosition;
        this.containerYPosition = yPosition;
        this.numberOfHeartsToDisplay = heartsToDisplay;
        initializeContainer();
        initializeHearts();
    }

    /**
     * Initializes the container (HBox) for displaying hearts and sets its layout position.
     */
    private void initializeContainer() {
        container = new HBox();
        container.setLayoutX(containerXPosition);
        container.setLayoutY(containerYPosition);
    }

    /**
     * Adds the specified number of heart images to the container during initialization.
     */
    private void initializeHearts() {
        for (int i = 0; i < numberOfHeartsToDisplay; i++) {
            ImageView heart = new ImageView(new Image(getClass().getResource(HEART_IMAGE_NAME).toExternalForm()));
            heart.setFitHeight(HEART_HEIGHT);
            heart.setPreserveRatio(true);
            container.getChildren().add(heart);
        }
    }

    /**
     * Removes the first heart from the container, if any exist.
     */
    public void removeHeart() {
        if (!container.getChildren().isEmpty())
            container.getChildren().remove(INDEX_OF_FIRST_ITEM);
    }

    /**
     * Gets the container (HBox) that holds the heart images.
     *
     * @return the container with the displayed hearts.
     */
    public HBox getContainer() {
        return container;
    }

    /**
     * Adds a new heart to the container, updating its display immediately.
     */
    public void addHeart() {
        ImageView heart = new ImageView(new Image(getClass().getResource(HEART_IMAGE_NAME).toExternalForm()));
        heart.setFitHeight(HEART_HEIGHT);
        heart.setPreserveRatio(true);
        container.getChildren().add(heart);
        container.applyCss();
        container.layout();
    }

    /**
     * Retrieves the current number of hearts displayed in the container.
     *
     * @return the number of hearts currently displayed.
     */
    public int getCurrentHeartCount() {
        return container.getChildren().size();
    }
}
