package com.example.demo;

public class ProjectileManager {
    private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;
    private static final double BOSS_FIRE_RATE = 0.04;

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

    private boolean shouldFireInCurrentFrame() {
        return Math.random() < BOSS_FIRE_RATE;
    }

    private double getProjectileInitialPosition(double layoutY, double translateY) {
        return layoutY + translateY + PROJECTILE_Y_POSITION_OFFSET;
    }
}

