package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * The {@code RedScreenEffect} class provides a red flash effect on the screen.
 * This is useful for visual feedback, such as indicating damage or other significant events in the game.
 */
public class RedScreenEffect {

    private final Rectangle overlay; // The red overlay displayed during the effect
    private final Timeline fadeAnimation; // Animation for fading the red overlay
    private final Group root; // The root group to which the overlay belongs

    /**
     * Constructs a {@code RedScreenEffect} instance with the specified screen dimensions and root group.
     *
     * @param screenWidth  the width of the screen.
     * @param screenHeight the height of the screen.
     * @param root         the root {@code Group} to which the overlay will be added.
     */
    public RedScreenEffect(double screenWidth, double screenHeight, Group root) {
        this.root = root;

        // Create a semi-transparent red overlay
        overlay = new Rectangle(screenWidth, screenHeight);
        overlay.setFill(Color.RED);
        overlay.setOpacity(0); // Initially transparent
        overlay.setMouseTransparent(true); // Prevent blocking clicks

        // Add the overlay to the root group
        root.getChildren().add(overlay);

        // Initialize fade animation
        fadeAnimation = new Timeline(
            new KeyFrame(Duration.ZERO, e -> {
                bringToFront(); // Ensure it's on top when triggered
                overlay.setMouseTransparent(false); // Enable visual interaction temporarily
                overlay.setOpacity(0.0);
            }),
            new KeyFrame(Duration.millis(100), e -> overlay.setOpacity(0.3)),
            new KeyFrame(Duration.millis(300), e -> {
                overlay.setOpacity(0);
                overlay.setMouseTransparent(true); // Restore transparency for interactions
            })
        );
        fadeAnimation.setCycleCount(1); // Play once
    }

    /**
     * Triggers the red screen effect. The effect fades in and out as defined by the animation.
     */
    public void trigger() {
        fadeAnimation.stop(); // Stop any running animation
        fadeAnimation.play(); // Start the animation
    }

    /**
     * Disposes of the red screen effect by stopping the animation and ensuring the overlay is transparent.
     */
    public void dispose() {
        overlay.setOpacity(0); // Ensure it's transparent
        fadeAnimation.stop();
    }

    /**
     * Brings the red overlay to the front of the root group to ensure it is displayed above other elements.
     */
    private void bringToFront() {
        root.getChildren().remove(overlay);
        root.getChildren().add(overlay); // Re-add it to ensure it's on top
    }
}