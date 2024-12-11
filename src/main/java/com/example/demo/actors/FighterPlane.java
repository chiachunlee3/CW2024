package com.example.demo.actors;

import com.example.demo.managers.HealthManager;

/**
 * Represents an abstract fighter plane in the game.
 * This class manages the health and projectile firing behavior of a fighter plane.
 */
public abstract class FighterPlane extends ActiveActorDestructible {
    /**
     * Manages the health of the fighter plane.
     */
    private final HealthManager healthManager;

    /**
     * Constructs a new FighterPlane with specified properties.
     *
     * @param imageName    The name of the image representing the fighter plane.
     * @param imageHeight  The height of the image.
     * @param initialXPos  The initial X position of the fighter plane.
     * @param initialYPos  The initial Y position of the fighter plane.
     * @param health       The initial health of the fighter plane.
     */
    public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
        super(imageName, imageHeight, initialXPos, initialYPos);
        this.healthManager = new HealthManager(health);
    }

    /**
     * Fires a projectile from the fighter plane.
     *
     * @return An instance of {@link ActiveActorDestructible} representing the projectile.
     */
    public abstract ActiveActorDestructible fireProjectile();

    /**
     * Reduces the health of the fighter plane when it takes damage.
     * Destroys the plane if its health reaches zero.
     */
    @Override
    public void takeDamage() {
        healthManager.decrementHealth();
        if (healthManager.isHealthZero()) {
            this.destroy();
        }
    }

    /**
     * Calculates the X position for firing a projectile.
     *
     * @param xPositionOffset The offset to apply to the current X position.
     * @return The X position adjusted by the offset.
     */
    protected double getProjectileXPosition(double xPositionOffset) {
        return getLayoutX() + getTranslateX() + xPositionOffset;
    }

    /**
     * Calculates the Y position for firing a projectile.
     *
     * @param yPositionOffset The offset to apply to the current Y position.
     * @return The Y position adjusted by the offset.
     */
    protected double getProjectileYPosition(double yPositionOffset) {
        return getLayoutY() + getTranslateY() + yPositionOffset;
    }

    /**
     * Retrieves the current health of the fighter plane.
     *
     * @return The current health of the fighter plane.
     */
    public int getHealth() {
        return healthManager.getHealth();
    }

    /**
     * Sets the health of the fighter plane.
     *
     * @param health The new health value to set.
     */
    protected void setHealth(int health) {
        healthManager.setHealth(health);
    }

    /**
     * Checks if the health of the fighter plane can be increased.
     *
     * @return {@code true} if the health can be increased, otherwise {@code false}.
     */
    protected boolean canIncreaseHealth() {
        return healthManager.getHealth() < getMaxHealth();
    }

    /**
     * Retrieves the maximum health of the fighter plane.
     *
     * @return The maximum health of the fighter plane.
     */
    public abstract int getMaxHealth();
}