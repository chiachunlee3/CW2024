package com.example.demo;

/**
 * The {@code ProjectileManager} class is responsible for managing the firing
 * of projectiles in the game. It determines when projectiles should be fired
 * and creates the appropriate type of projectile.
 */
public class ProjectileManager {

    private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0; // Offset for the initial Y-position of projectiles
    private static final double BOSS_FIRE_RATE = 0.04; // Probability of firing a projectile in a given frame

    /**
     * Fires a projectile based on the current frame and randomness.
     * If firing is triggered, either a {@code HealthProjectile} or {@code BossProjectile}
     * is created based on a probability.
     *
     * @param layoutY     the layout Y-coordinate of the firing entity.
     * @param translateY  the translation Y-coordinate of the firing entity.
     * @return the fired {@code ActiveActorDestructible} projectile, or {@code null} if no projectile is fired.
     */
    public ActiveActorDestructible fireProjectile(double layoutY, double translateY) {
        if (shouldFireInCurrentFrame()) {
            if (Math.random() < 0.2) {
                return new HealthProjectile(getProjectileInitialPosition(layoutY, translateY));
            } else {
                return new BossProjectile(getProjectileInitialPosition(layoutY, translateY));
            }
        }
        return null;
    }

    /**
     * Determines if a projectile should be fired in the current frame.
     *
     * @return {@code true} if a projectile should be fired; {@code false} otherwise.
     */
    private boolean shouldFireInCurrentFrame() {
        return Math.random() < BOSS_FIRE_RATE;
    }

    /**
     * Calculates the initial Y-position for a projectile based on the firing entity's position.
     *
     * @param layoutY    the layout Y-coordinate of the firing entity.
     * @param translateY the translation Y-coordinate of the firing entity.
     * @return the calculated initial Y-position for the projectile.
     */
    private double getProjectileInitialPosition(double layoutY, double translateY) {
        return layoutY + translateY + PROJECTILE_Y_POSITION_OFFSET;
    }
}