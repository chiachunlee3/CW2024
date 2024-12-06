package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class RedScreenEffect {
    private final Rectangle overlay;
    private final Timeline fadeAnimation;
    private final Group root;

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


    public void trigger() {
        fadeAnimation.stop(); // Stop any running animation
        fadeAnimation.play(); // Start the animation
    }

    public void dispose() {
        overlay.setOpacity(0); // Ensure it's transparent
        fadeAnimation.stop();
    }

    private void bringToFront() {
        root.getChildren().remove(overlay);
        root.getChildren().add(overlay); // Re-add it to ensure it's on top
    }
}
