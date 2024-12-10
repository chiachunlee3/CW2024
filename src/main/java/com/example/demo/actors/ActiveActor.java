package com.example.demo.actors;

import com.example.demo.managers.MovementManager;
import com.example.demo.utility.BoundsHandler;
import com.example.demo.utility.ImageHandler;

import javafx.scene.image.ImageView;

/**
 * The {@code ActiveActor} class represents an abstract actor in a graphical application.
 * It extends {@link ImageView} to handle image representation and provides functionality
 * for positioning and movement. Subclasses must define their specific behavior for 
 * updating the position.
 */
public abstract class ActiveActor extends ImageView {

    /**
     * Constructs an {@code ActiveActor} with the specified image, size, and initial position.
     *
     * @param imageName   the name of the image file to be used for the actor
     * @param imageHeight the height of the image in pixels
     * @param initialXPos the initial X-coordinate of the actor
     * @param initialYPos the initial Y-coordinate of the actor
     */
    public ActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos) {
        ImageHandler.setImageProperties(this, imageName, imageHeight);
        this.setLayoutX(initialXPos);
        this.setLayoutY(initialYPos);
    }

    /**
     * Updates the position of the actor. Subclasses must implement this method to define
     * their specific behavior for position updates.
     */
    public abstract void updatePosition();

    /**
     * Moves the actor horizontally by the specified amount.
     *
     * @param horizontalMove the amount to move the actor along the X-axis
     */
    public void moveHorizontally(double horizontalMove) {
        MovementManager.moveHorizontally(this, horizontalMove);
    }

    /**
     * Moves the actor vertically by the specified amount.
     *
     * @param verticalMove the amount to move the actor along the Y-axis
     */
    public void moveVertically(double verticalMove) {
        MovementManager.moveVertically(this, verticalMove);
    }

    /**
     * Retrieves the precise bounds of the actor for collision detection or other spatial calculations.
     *
     * @return the precise bounds of the actor as a {@link javafx.geometry.Bounds} object
     */
    public javafx.geometry.Bounds getPreciseBounds() {
        return BoundsHandler.getPreciseBounds(this);
    }
}
