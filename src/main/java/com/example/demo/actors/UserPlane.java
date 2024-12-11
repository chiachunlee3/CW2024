package com.example.demo.actors;

import com.example.demo.levels.LevelView;
import com.example.demo.projectiles.UserProjectile;

/**
 * Represents the user's fighter plane in the game.
 * Handles movement, firing projectiles, and tracking kills and health.
 */
public class UserPlane extends FighterPlane {

    /** The image name for the user plane. */
    private static final String IMAGE_NAME = "userplane.png";

    /** The upper boundary for the plane's vertical movement. */
    private static final double Y_UPPER_BOUND = -40;

    /** The lower boundary for the plane's vertical movement. */
    private static final double Y_LOWER_BOUND = 600.0;

    /** The initial x-coordinate position of the plane. */
    private static final double INITIAL_X_POSITION = 5.0;

    /** The initial y-coordinate position of the plane. */
    private static final double INITIAL_Y_POSITION = 300.0;

    /** The height of the plane's image. */
    private static final int IMAGE_HEIGHT = 150;

    /** The velocity for vertical movement. */
    private static final int VERTICAL_VELOCITY = 8;

    /** The x-coordinate for firing projectiles. */
    private static final int PROJECTILE_X_POSITION = 110;

    /** The y-coordinate offset for firing projectiles. */
    private static final int PROJECTILE_Y_POSITION_OFFSET = 20;

    /** The maximum health of the plane. */
    private static final int MAX_HEALTH = 5;

    /** The multiplier for velocity, determines the movement direction. */
    private int velocityMultiplier;

    /** The number of kills by the user's plane. */
    private int numberOfKills;

    /** The level view associated with this plane, used for UI updates. */
    private final LevelView levelView;

    /**
     * Constructs a UserPlane with the specified initial health and level view.
     *
     * @param initialHealth the initial health of the plane.
     * @param levelView the level view to update UI components.
     */
    public UserPlane(int initialHealth, LevelView levelView) {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
        this.levelView = levelView;
        velocityMultiplier = 0;
    }

    /**
     * Updates the position of the plane based on its velocity multiplier.
     * Prevents the plane from moving outside the defined vertical bounds.
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
     * Updates the state of the actor, specifically its position.
     */
    @Override
    public void updateActor() {
        updatePosition();
    }

    /**
     * Fires a projectile from the plane.
     *
     * @return a new projectile fired by the user's plane.
     */
    @Override
    public ActiveActorDestructible fireProjectile() {
        return new UserProjectile(PROJECTILE_X_POSITION, getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
    }

    /**
     * Checks if the plane is currently moving.
     *
     * @return true if the plane is moving, false otherwise.
     */
    private boolean isMoving() {
        return velocityMultiplier != 0;
    }

    /**
     * Initiates upward movement for the plane.
     */
    public void moveUp() {
        velocityMultiplier = -1;
    }

    /**
     * Initiates downward movement for the plane.
     */
    public void moveDown() {
        velocityMultiplier = 1;
    }

    /**
     * Stops the plane's movement.
     */
    public void stop() {
        velocityMultiplier = 0;
    }

    /**
     * Gets the number of kills by the user's plane.
     *
     * @return the number of kills.
     */
    public int getNumberOfKills() {
        return numberOfKills;
    }

    /**
     * Increments the kill count for the user's plane.
     */
    public void incrementKillCount() {
        numberOfKills++;
    }

    /**
     * Gets the maximum health of the user's plane.
     *
     * @return the maximum health.
     */
    @Override
    public int getMaxHealth() {
        return MAX_HEALTH;
    }

    /**
     * Increases the health of the plane, updating the UI if successful.
     */
    public void increaseHealth() {
        if (canIncreaseHealth()) {
            setHealth(getHealth() + 1);
            levelView.updateHeartDisplay(getHealth());
        }
    }
}