package com.example.demo;

import javafx.scene.image.*;
import javafx.geometry.Bounds;

public abstract class ActiveActor extends ImageView {
    
    private static final String IMAGE_LOCATION = "/com/example/demo/images/";

    public ActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos) {
        this.setImage(new Image(getClass().getResource(IMAGE_LOCATION + imageName).toExternalForm()));
        this.setLayoutX(initialXPos);
        this.setLayoutY(initialYPos);
        this.setFitHeight(imageHeight);
        this.setPreserveRatio(true);
    }

    public abstract void updatePosition();

    protected void moveHorizontally(double horizontalMove) {
        this.setTranslateX(getTranslateX() + horizontalMove);
    }

    protected void moveVertically(double verticalMove) {
        this.setTranslateY(getTranslateY() + verticalMove);
    }
    
    // Get precise bounds with reduced vertical hitbox
    public Bounds getPreciseBounds() {
        Bounds bounds = this.getBoundsInParent();
        // Reduce height by 70% (making hitbox only 30% of original height)
        double verticalReduction = 0.8;
        double heightReduction = bounds.getHeight() * verticalReduction;
        return new javafx.geometry.BoundingBox(
            bounds.getMinX(),
            bounds.getMinY() + heightReduction/2,  // Center the reduced hitbox vertically
            bounds.getWidth(),
            bounds.getHeight() - heightReduction
        );
    }
}