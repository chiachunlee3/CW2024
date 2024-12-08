package com.example.demo;

/**
 * The {@code ShieldManager} class handles the activation, positioning, and deactivation
 * of a shield in the game. The shield is displayed with a certain probability and remains
 * active for a limited number of frames.
 */
public class ShieldManager {

    private static final int MAX_FRAMES_WITH_SHIELD = 300; // Maximum number of frames the shield can stay active
    private final LevelViewLevelTwo levelView; // The level view to control the shield's visibility and position
    private final double shieldProbability; // The probability of activating the shield
    private boolean isShielded = false; // Indicates whether the shield is currently active
    private int framesWithShieldActivated = 0; // Counter for the number of frames the shield has been active

    /**
     * Constructs a {@code ShieldManager} object.
     *
     * @param levelView         the level view that manages the shield display.
     * @param shieldProbability the probability of activating the shield each update.
     */
    public ShieldManager(LevelViewLevelTwo levelView, double shieldProbability) {
        this.levelView = levelView;
        this.shieldProbability = shieldProbability;
    }

    /**
     * Updates the shield's position and activation state.
     * If the shield is active, its position is updated based on the provided coordinates.
     * If the shield is inactive, it may be activated based on the probability.
     *
     * @param x the X-coordinate to position the shield.
     * @param y the Y-coordinate to position the shield.
     */
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

    /**
     * Checks whether the shield should be activated based on the random probability.
     *
     * @return {@code true} if the shield should be activated; {@code false} otherwise.
     */
    private boolean shouldActivateShield() {
        return Math.random() < shieldProbability;
    }

    /**
     * Checks whether the shield has been active for the maximum allowed frames.
     *
     * @return {@code true} if the shield's activation period is exhausted; {@code false} otherwise.
     */
    private boolean isShieldExhausted() {
        return framesWithShieldActivated == MAX_FRAMES_WITH_SHIELD;
    }

    /**
     * Activates the shield, making it visible and marking it as active.
     */
    private void activateShield() {
        isShielded = true;
        levelView.showShield();
    }

    /**
     * Deactivates the shield, hiding it and resetting the activation counter.
     */
    private void deactivateShield() {
        isShielded = false;
        framesWithShieldActivated = 0;
        levelView.hideShield();
    }

    /**
     * Checks whether the shield is currently active.
     *
     * @return {@code true} if the shield is active; {@code false} otherwise.
     */
    public boolean isShielded() {
        return isShielded;
    }
}