package com.example.demo.states;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The {@code WinImage} class represents the "You Win" image displayed when the player wins the game.
 * It extends {@code ImageView} and provides functionality to control the visibility of the image.
 */
public class WinImage extends ImageView {

    private static final String IMAGE_NAME = "/com/example/demo/images/youwin.png"; // Path to the "You Win" image
    private static final int HEIGHT = 500; // Height of the image
    private static final int WIDTH = 600; // Width of the image

    /**
     * Constructs a {@code WinImage} object with the specified position.
     *
     * @param xPosition the initial X-coordinate of the "You Win" image.
     * @param yPosition the initial Y-coordinate of the "You Win" image.
     */
    public WinImage(double xPosition, double yPosition) {
        this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
        this.setVisible(false); // Initially invisible
        this.setFitHeight(HEIGHT);
        this.setFitWidth(WIDTH);
        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);
    }

    /**
     * Makes the "You Win" image visible on the screen.
     */
    public void showWinImage() {
        this.setVisible(true);
    }
}