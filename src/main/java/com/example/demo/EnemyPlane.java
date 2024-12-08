package com.example.demo;

/**
 * Represents an enemy plane in the game. This class extends {@code FighterPlane}
 * and implements behaviors specific to enemy planes, such as movement,
 * firing projectiles, and updating its state.
 */
public class EnemyPlane extends FighterPlane {

    /**
     * The name of the image file representing the enemy plane.
     */
    private static final String IMAGE_NAME = "enemyplane.png";

    /**
     * The height of the enemy plane image in pixels.
     */
    private static final int IMAGE_HEIGHT = 150;

    /**
     * The horizontal velocity of the enemy plane.
     * A negative value indicates movement to the left.
     */
    private static final int HORIZONTAL_VELOCITY = -6;

    /**
     * The x-axis offset for the position of the projectile fired by the enemy plane.
     */
    private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;

    /**
     * The y-axis offset for the position of the projectile fired by the enemy plane.
     */
    private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;

    /**
     * The initial health of the enemy plane.
     */
    private static final int INITIAL_HEALTH = 1;

    /**
     * The probability (rate) of firing a projectile during each update.
     */
    private static final double FIRE_RATE = 0.01;

    /**
     * Constructs a new {@code EnemyPlane} instance with specified initial positions.
     *
     * @param initialXPos the initial x-coordinate of the enemy plane.
     * @param initialYPos the initial y-coordinate of the enemy plane.
     */
    public EnemyPlane(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
    }

    /**
     * Updates the position of the enemy plane by moving it horizontally
     * based on its predefined velocity.
     */
    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

    /**
     * Fires a projectile from the enemy plane with a probability defined by {@code FIRE_RATE}.
     * The projectile's initial position is determined by predefined offsets.
     *
     * @return a new {@code EnemyProjectile} if fired, or {@code null} if not fired.
     */
    @Override
    public ActiveActorDestructible fireProjectile() {
        if (Math.random() < FIRE_RATE) {
            double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
            double projectileYPostion = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
            return new EnemyProjectile(projectileXPosition, projectileYPostion);
        }
        return null;
    }

    /**
     * Updates the state of the enemy plane, including its position.
     * This method is called during each game update cycle.
     */
    @Override
    public void updateActor() {
        updatePosition();
    }

}
