package com.example.demo.projectiles;

/**
 * Represents a projectile fired by an enemy in the game.
 * This class extends {@code Projectile} and defines specific behavior
 * for projectiles launched by enemy planes.
 */
public class EnemyProjectile extends Projectile {

    /**
     * The name of the image file representing the enemy projectile.
     */
    private static final String IMAGE_NAME = "enemyFire.png";

    /**
     * The height of the enemy projectile image in pixels.
     */
    private static final int IMAGE_HEIGHT = 50;

    /**
     * The horizontal velocity of the enemy projectile.
     * A negative value indicates movement to the left.
     */
    private static final int HORIZONTAL_VELOCITY = -10;

    /**
     * Constructs a new {@code EnemyProjectile} instance with specified initial positions.
     *
     * @param initialXPos the initial x-coordinate of the enemy projectile.
     * @param initialYPos the initial y-coordinate of the enemy projectile.
     */
    public EnemyProjectile(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
    }

    /**
     * Updates the position of the enemy projectile by moving it horizontally
     * based on its predefined velocity.
     */
    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

    /**
     * Updates the state of the enemy projectile.
     * This method is called during each game update cycle and updates its position.
     */
    @Override
    public void updateActor() {
        updatePosition();
    }
}
