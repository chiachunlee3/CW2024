package com.example.demo;

import javafx.scene.image.ImageView;

public abstract class ActiveActor extends ImageView {

    public ActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos) {
        ImageHandler.setImageProperties(this, imageName, imageHeight);
        this.setLayoutX(initialXPos);
        this.setLayoutY(initialYPos);
    }

    public abstract void updatePosition();

    public void moveHorizontally(double horizontalMove) {
        MovementManager.moveHorizontally(this, horizontalMove);
    }

    public void moveVertically(double verticalMove) {
        MovementManager.moveVertically(this, verticalMove);
    }

    public javafx.geometry.Bounds getPreciseBounds() {
        return BoundsHandler.getPreciseBounds(this);
    }
}
