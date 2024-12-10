package com.example.demo.projectiles;

/**
 * The {@code UserProjectile} class represents a projectile fired by the user plane.
 * It extends the {@code Projectile} class and defines the behavior of the projectile,
 * including its movement.
 */
public class UserProjectile extends Projectile {

    private static final String IMAGE_NAME = "userfire.png"; // Image representing the projectile
    private static final int IMAGE_HEIGHT = 125; // Height of the projectile image
    private static final int HORIZONTAL_VELOCITY = 15; // Speed of the projectile's horizontal movement

    /**
     * Constructs a {@code UserProjectile} object with the specified initial position.
     *
     * @param initialXPos the initial X-coordinate of the projectile.
     * @param initialYPos the initial Y-coordinate of the projectile.
     */
    public UserProjectile(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
    }

    /**
     * Updates the position of the projectile by moving it horizontally at a constant velocity.
     */
    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

    /**
     * Updates the state of the projectile, which currently involves only updating its position.
     */
    @Override
    public void updateActor() {
        updatePosition();
    }
}