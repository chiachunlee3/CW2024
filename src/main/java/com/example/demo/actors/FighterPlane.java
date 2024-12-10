package com.example.demo.actors;

import com.example.demo.managers.HealthManager;

public abstract class FighterPlane extends ActiveActorDestructible {
    private final HealthManager healthManager;

    public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
        super(imageName, imageHeight, initialXPos, initialYPos);
        this.healthManager = new HealthManager(health);
    }

    public abstract ActiveActorDestructible fireProjectile();

    @Override
    public void takeDamage() {
        healthManager.decrementHealth();
        if (healthManager.isHealthZero()) {
            this.destroy();
        }
    }

    protected double getProjectileXPosition(double xPositionOffset) {
        return getLayoutX() + getTranslateX() + xPositionOffset;
    }

    protected double getProjectileYPosition(double yPositionOffset) {
        return getLayoutY() + getTranslateY() + yPositionOffset;
    }

    public int getHealth() {
        return healthManager.getHealth();
    }

    protected void setHealth(int health) {
        healthManager.setHealth(health);
    }

    protected boolean canIncreaseHealth() {
        return healthManager.getHealth() < getMaxHealth();
    }

    public abstract int getMaxHealth();
}
