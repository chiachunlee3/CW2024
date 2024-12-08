package com.example.demo;

/**
 * The {@code UserPlane} class represents the player's plane in the game.
 * It extends {@code FighterPlane} and provides functionality for movement,
 * firing projectiles, managing health, and tracking kills.
 */
public class UserPlane extends FighterPlane {

    private static final String IMAGE_NAME = "userplane.png"; // Image representing the user plane
    private static final double Y_UPPER_BOUND = -40; // Upper boundary for the plane's Y position
    private static final double Y_LOWER_BOUND = 600.0; // Lower boundary for the plane's Y position
    private static final double INITIAL_X_POSITION = 5.0; // Initial X position of the plane
    private static final double INITIAL_Y_POSITION = 300.0; // Initial Y position of the plane
    private static final int IMAGE_HEIGHT = 150; // Height of the plane image
    private static final int VERTICAL_VELOCITY = 8; // Vertical speed of the plane
    private static final int PROJECTILE_X_POSITION = 110; // X position for firing projectiles
    private static final int PROJECTILE_Y_POSITION_OFFSET = 20; // Offset for projectile's Y position
    private static final int MAX_HEALTH = 5; // Maximum health of the plane

    private int velocityMultiplier; // Determines the plane's vertical movement direction and speed
    private int numberOfKills; // Tracks the number of enemies destroyed by the plane
    private LevelView levelView; // Reference to the level view for updating UI elements

    /**
     * Constructs a {@code UserPlane} object with the specified initial health and level view.
     *
     * @param initialHealth the initial health of the user plane.
     * @param levelView     the level view for managing UI updates related to the plane.
     */
    public UserPlane(int initialHealth, LevelView levelView) {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
        this.levelView = levelView;
        velocityMultiplier = 0;
    }

    /**
     * Updates the position of the plane. Ensures the plane stays within predefined boundaries.
     */
    @Override
    public void updatePosition() {
        if (isMoving()) {
            double initialTranslateY = getTranslateY();
            this.moveVertically(VERTICAL_VELOCITY * velocityMultiplier);
            double newPosition = getLayoutY() + getTranslateY();
            if (newPosition < Y_UPPER_BOUND || newPosition > Y_LOWER_BOUND) {
                this.setTranslateY(initialTranslateY);
            }
        }
    }

    /**
     * Updates the state of the user plane, including its position.
     */
    @Override
    public void updateActor() {
        updatePosition();
    }

    /**
     * Fires a projectile from the user plane's current position.
     *
     * @return a {@code UserProjectile} object representing the fired projectile.
     */
    @Override
    public ActiveActorDestructible fireProjectile() {
        return new UserProjectile(PROJECTILE_X_POSITION, getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
    }

    /**
     * Checks if the plane is currently moving.
     *
     * @return {@code true} if the plane is moving; {@code false} otherwise.
     */
    private boolean isMoving() {
        return velocityMultiplier != 0;
    }

    /**
     * Moves the plane upwards by setting the velocity multiplier.
     */
    public void moveUp() {
        velocityMultiplier = -1;
    }

    /**
     * Moves the plane downwards by setting the velocity multiplier.
     */
    public void moveDown() {
        velocityMultiplier = 1;
    }

    /**
     * Stops the plane's movement by resetting the velocity multiplier.
     */
    public void stop() {
        velocityMultiplier = 0;
    }

    /**
     * Retrieves the total number of kills made by the user plane.
     *
     * @return the number of kills.
     */
    public int getNumberOfKills() {
        return numberOfKills;
    }

    /**
     * Increments the kill count for the user plane.
     */
    public void incrementKillCount() {
        numberOfKills++;
    }

    /**
     * Retrieves the maximum health of the user plane.
     *
     * @return the maximum health value.
     */
    public int getMaxHealth() {
        return MAX_HEALTH;
    }

    /**
     * Increases the health of the user plane by one, up to the maximum health.
     * Updates the level view to reflect the new health value.
     */
    public void increaseHealth() {
        if (this.health < getMaxHealth()) {
            this.health++;
            levelView.updateHeartDisplay(this.health); // Synchronize heart display
        }
    }
}