package com.example.demo;

public class ShieldManager {
    private static final int MAX_FRAMES_WITH_SHIELD = 300;
    private final LevelViewLevelTwo levelView;
    private final double shieldProbability;
    private boolean isShielded = false;
    private int framesWithShieldActivated = 0;

    public ShieldManager(LevelViewLevelTwo levelView, double shieldProbability) {
        this.levelView = levelView;
        this.shieldProbability = shieldProbability;
    }

    public void updateShield(double x, double y) {
        if (isShielded) {
            framesWithShieldActivated++;
            levelView.updateShieldPosition(x - 60, y + 60);
        } else if (shouldActivateShield()) {
            activateShield();
        }
        if (isShieldExhausted()) {
            deactivateShield();
        }
    }

    private boolean shouldActivateShield() {
        return Math.random() < shieldProbability;
    }

    private boolean isShieldExhausted() {
        return framesWithShieldActivated == MAX_FRAMES_WITH_SHIELD;
    }

    private void activateShield() {
        isShielded = true;
        levelView.showShield();
    }

    private void deactivateShield() {
        isShielded = false;
        framesWithShieldActivated = 0;
        levelView.hideShield();
    }

    public boolean isShielded() {
        return isShielded;
    }
}

