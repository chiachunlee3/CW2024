package com.example.demo;

public class HealthProjectile extends Projectile {
    
    private static final String IMAGE_NAME = "heart.png";
    private static final int IMAGE_HEIGHT = 50;
    private static final int HORIZONTAL_VELOCITY = -10;
    private static final int INITIAL_X_POSITION = 950;

    public HealthProjectile(double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
    }

    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

    @Override
    public void updateActor() {
        updatePosition();
    }
}
