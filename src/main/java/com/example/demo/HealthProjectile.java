package com.example.demo;

/**
 * Represents a health projectile in the game. 
 * This type of projectile typically restores health to the player upon collection.
 * It extends the {@code Projectile} class and defines specific behavior for movement and initialization.
 */
public class HealthProjectile extends Projectile {

    /**
     * The name of the image file representing the health projectile.
     */
    private static final String IMAGE_NAME = "heart.png";

    /**
     * The height of the health projectile image in pixels.
     */
    private static final int IMAGE_HEIGHT = 50;

    /**
     * The horizontal velocity of the health projectile.
     * A negative value indicates movement to the left.
     */
    private static final int HORIZONTAL_VELOCITY = -10;

    /**
     * The initial x-coordinate of the health projectile.
     */
    private static final int INITIAL_X_POSITION = 950;

    /**
     * Constructs a new {@code HealthProjectile} instance with a specified initial y-coordinate.
     *
     * @param initialYPos the initial y-coordinate of the health projectile.
     */
    public HealthProjectile(double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
    }

    /**
     * Updates the position of the health projectile by moving it horizontally
     * based on its predefined velocity.
     */
    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

    /**
     * Updates the state of the health projectile.
     * This method is called during each game update cycle and updates its position.
     */
    @Override
    public void updateActor() {
        updatePosition();
    }
}
