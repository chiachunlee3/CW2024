package com.example.demo.actors;

import com.example.demo.projectiles.EnemyProjectile;

/**
 * Represents an enemy plane in the game. This class extends {@code FighterPlane}
 * and implements behaviors specific to enemy planes, such as movement,
 * firing projectiles, and updating its state.
 */
public class EnemyPlane extends FighterPlane {

    /**
     * The image file name representing the enemy plane.
     */
    private static final String IMAGE_NAME = "enemyplane.png";

    /**
     * The height of the enemy plane image.
     */
    private static final int IMAGE_HEIGHT = 150;

    /**
     * The horizontal velocity of the enemy plane.
     */
    private static final int HORIZONTAL_VELOCITY = -6;

    /**
     * The X-axis offset for the projectile's initial position.
     */
    private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;

    /**
     * The Y-axis offset for the projectile's initial position.
     */
    private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;

    /**
     * The initial health of the enemy plane.
     */
    private static final int INITIAL_HEALTH = 1;

    /**
     * The maximum health of the enemy plane.
     */
    private static final int MAX_HEALTH = 1;

    /**
     * The probability that the enemy plane fires a projectile at each update.
     */
    private static final double FIRE_RATE = 0.01;

    /**
     * Constructs an {@code EnemyPlane} at the specified initial position.
     *
     * @param initialXPos the initial X-coordinate of the enemy plane.
     * @param initialYPos the initial Y-coordinate of the enemy plane.
     */
    public EnemyPlane(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
    }

    /**
     * Updates the position of the enemy plane by moving it horizontally.
     */
    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

    /**
     * Fires a projectile with a probability defined by {@code FIRE_RATE}.
     *
     * @return an {@code EnemyProjectile} if the firing condition is met; otherwise, {@code null}.
     */
    @Override
    public ActiveActorDestructible fireProjectile() {
        if (Math.random() < FIRE_RATE) {
            double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
            double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
            return new EnemyProjectile(projectileXPosition, projectileYPosition);
        }
        return null;
    }

    /**
     * Updates the state of the enemy plane, including its position and any actions like firing projectiles.
     */
    @Override
    public void updateActor() {
        updatePosition();
    }

    /**
     * Retrieves the maximum health of the enemy plane.
     *
     * @return the maximum health value.
     */
    @Override
    public int getMaxHealth() {
        return MAX_HEALTH;
    }
}