package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The {@code ShieldImage} class represents a shield graphic in the game.
 * It extends {@code ImageView} and provides functionality to control the visibility
 * and position of the shield.
 */
public class ShieldImage extends ImageView {

    private static final String IMAGE_NAME = "/com/example/demo/images/shield.png"; // Path to the shield image
    private static final int SHIELD_SIZE = 200; // Size (width and height) of the shield

    /**
     * Constructs a {@code ShieldImage} object with the specified position.
     *
     * @param xPosition the initial X-coordinate of the shield.
     * @param yPosition the initial Y-coordinate of the shield.
     */
    public ShieldImage(double xPosition, double yPosition) {
        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);
        this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
        this.setVisible(false); // Initially invisible
        this.setFitHeight(SHIELD_SIZE);
        this.setFitWidth(SHIELD_SIZE);
    }

    /**
     * Makes the shield visible on the screen.
     */
    public void showShield() {
        this.setVisible(true);
    }

    /**
     * Hides the shield from the screen.
     */
    public void hideShield() {
        this.setVisible(false);
    }

    /**
     * Updates the position of the shield.
     *
     * @param x the new X-coordinate for the shield.
     * @param y the new Y-coordinate for the shield.
     */
    public void setPosition(double x, double y) {
        this.setLayoutX(x);
        this.setLayoutY(y);
    }
}