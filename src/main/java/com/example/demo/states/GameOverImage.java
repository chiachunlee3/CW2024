package com.example.demo.states;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents the "Game Over" image displayed in the game.
 * This class extends {@code ImageView} and allows the positioning
 * and sizing of the "Game Over" image on the screen.
 */
public class GameOverImage extends ImageView {

    /**
     * The path to the "Game Over" image resource file.
     */
    private static final String IMAGE_NAME = "/com/example/demo/images/gameover.png";

    /**
     * Constructs a new {@code GameOverImage} instance and initializes it
     * with the specified position and dimensions.
     *
     * @param xPosition the x-coordinate of the top-left corner of the image.
     * @param yPosition the y-coordinate of the top-left corner of the image.
     * @param width     the width of the image in pixels.
     * @param height    the height of the image in pixels.
     */
    public GameOverImage(double xPosition, double yPosition, double width, double height) {
        setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
        setFitWidth(width);
        setFitHeight(height);
        setLayoutX(xPosition);
        setLayoutY(yPosition);
    }
}
