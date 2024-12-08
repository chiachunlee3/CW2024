package com.example.demo;

/**
 * Represents an abstract fighter plane in the game.
 * This class provides common functionality for all fighter planes, including
 * health management, firing projectiles, and calculating projectile positions.
 * Subclasses must define specific behavior for firing projectiles.
 */
public abstract class FighterPlane extends ActiveActorDestructible {

    /**
     * The health of the fighter plane, indicating how many hits it can take before being destroyed.
     */
    protected int health;

    /**
     * Constructs a new {@code FighterPlane} instance with specified properties.
     *
     * @param imageName   the name of the image representing the fighter plane.
     * @param imageHeight the height of the image in pixels.
     * @param initialXPos the initial x-coordinate of the fighter plane.
     * @param initialYPos the initial y-coordinate of the fighter plane.
     * @param health      the initial health of the fighter plane.
     */
    public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
        super(imageName, imageHeight, initialXPos, initialYPos);
        this.health = health;
    }

    /**
     * Fires a projectile from the fighter plane.
     * Subclasses must implement this method to define specific projectile behavior.
     *
     * @return an instance of {@code ActiveActorDestructible} representing the projectile,
     * or {@code null} if no projectile is fired.
     */
    public abstract ActiveActorDestructible fireProjectile();

    /**
     * Applies damage to the fighter plane by decrementing its health.
     * If the health reaches zero, the plane is destroyed.
     */
    @Override
    public void takeDamage() {
        health--;
        if (healthAtZero()) {
            this.destroy();
        }
    }

    /**
     * Calculates the x-coordinate of a projectile based on a specified offset.
     *
     * @param xPositionOffset the offset to apply to the fighter plane's current position.
     * @return the calculated x-coordinate for the projectile.
     */
    protected double getProjectileXPosition(double xPositionOffset) {
        return getLayoutX() + getTranslateX() + xPositionOffset;
    }

    /**
     * Calculates the y-coordinate of a projectile based on a specified offset.
     *
     * @param yPositionOffset the offset to apply to the fighter plane's current position.
     * @return the calculated y-coordinate for the projectile.
     */
    protected double getProjectileYPosition(double yPositionOffset) {
        return getLayoutY() + getTranslateY() + yPositionOffset;
    }

    /**
     * Checks if the health of the fighter plane has reached zero.
     *
     * @return {@code true} if the health is zero, {@code false} otherwise.
     */
    private boolean healthAtZero() {
        return health == 0;
    }

    /**
     * Retrieves the current health of the fighter plane.
     *
     * @return the current health value.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets the health of the fighter plane to a specified value.
     *
     * @param health the new health value to assign.
     */
    protected void setHealth(int health) {
        this.health = health;
    }
}
