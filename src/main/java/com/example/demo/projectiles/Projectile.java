package com.example.demo.projectiles;

import com.example.demo.actors.ActiveActorDestructible;

/**
 * The {@code Projectile} class is an abstract representation of a destructible
 * projectile in the game. It extends {@code ActiveActorDestructible} and defines
 * common behavior for all projectiles, including how they take damage and the need
 * to update their position.
 */
public abstract class Projectile extends ActiveActorDestructible {

    /**
     * Constructs a new {@code Projectile}.
     *
     * @param imageName   the name of the image used to visually represent the projectile.
     * @param imageHeight the height of the projectile's image.
     * @param initialXPos the initial X-coordinate of the projectile.
     * @param initialYPos the initial Y-coordinate of the projectile.
     */
    public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
        super(imageName, imageHeight, initialXPos, initialYPos);
    }

    /**
     * Handles the behavior when the projectile takes damage. The projectile is destroyed
     * when this method is called.
     */
    @Override
    public void takeDamage() {
        this.destroy();
    }

    /**
     * Updates the position of the projectile. This method must be implemented by
     * subclasses to define specific movement behavior.
     */
    @Override
    public abstract void updatePosition();
}