package com.example.demo;

import javafx.scene.Group;

/**
 * The {@code LevelViewLevelTwo} class represents the second level's visual layout in a game.
 */
public class LevelViewLevelTwo extends LevelView {
	private static final int SHIELD_X_POSITION = 1150; // X-coordinate for the shield position
    private static final int SHIELD_Y_POSITION = 500;
    private final Group root; // The root node for adding elements to the scene
    private final ShieldImage shieldImage; // The shield image element
    private final BossHealthManager bossHealthManager; // The boss health manager

    /**
     * Constructs a new {@code LevelViewLevelTwo} object.
     *
     * @param root            the root {@code Group} to which elements will be added.
     * @param heartsToDisplay the number of hearts to display for the player.
     */
    public LevelViewLevelTwo(Group root, int heartsToDisplay) {
        super(root, heartsToDisplay);
        this.root = root;
        this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION);
        this.bossHealthManager = new BossHealthManager(root);
        addImagesToRoot();
    }

    /**
     * Adds the shield image to the root node.
     */
    private void addImagesToRoot() {
        root.getChildren().addAll(shieldImage);
    }

    /**
     * Displays the shield by making it visible and bringing it to the front.
     */
    public void showShield() {
        shieldImage.showShield();
        shieldImage.toFront();
    }

    /**
     * Hides the shield by making it invisible.
     */
    public void hideShield() {
        shieldImage.hideShield();
    }

    /**
     * Updates the kills remaining text. This implementation hides the text.
     *
     * @param killsRemaining the number of kills remaining to complete the level.
     */
    @Override
    public void updateKillsRemaining(int killsRemaining) {
        killsRemainingTextManager.hideKillsRemainingText(); // Use the existing instance
    }

    /**
     * Updates the boss's health by delegating to {@code BossHealthManager}.
     *
     * @param bossHealth the current health of the boss.
     * @param maxHealth  the maximum health of the boss.
     */
    public void updateBossHealth(int bossHealth, int maxHealth) {
        bossHealthManager.updateBossHealth(bossHealth, maxHealth);
    }

    /**
     * Updates the position of the shield on the screen.
     *
     * @param x the new X-coordinate for the shield.
     * @param y the new Y-coordinate for the shield.
     */
    public void updateShieldPosition(double x, double y) {
        shieldImage.setPosition(x, y);
    }
}
