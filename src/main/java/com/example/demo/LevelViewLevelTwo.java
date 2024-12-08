package com.example.demo;

import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * The {@code LevelViewLevelTwo} class represents the second level's visual layout in a game. 
 * It extends {@code LevelView} and provides additional functionality for displaying a shield,
 * boss health, and other visual elements specific to this level.
 */
public class LevelViewLevelTwo extends LevelView {

    private static final int SHIELD_X_POSITION = 1150; // X-coordinate for the shield position
    private static final int SHIELD_Y_POSITION = 500; // Y-coordinate for the shield position
    private static final int HEALTH_BAR_WIDTH = 300; // Width of the health bar
    private static final int HEALTH_BAR_HEIGHT = 20; // Height of the health bar
    private static final int HEALTH_BAR_X_POSITION = 950; // X-coordinate for the health bar
    private static final int HEALTH_BAR_Y_POSITION = 70; // Y-coordinate for the health bar

    private final Group root; // The root node for adding elements to the scene
    private final ShieldImage shieldImage; // The shield image element
    private final Text bossHealthText; // The text displaying boss health
    private final Rectangle healthBar; // The health bar showing current boss health
    private final Rectangle healthBarBackground; // The background of the health bar

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
        addImagesToRoot();

        bossHealthText = new Text();
        bossHealthText.setFont(Font.font("Monospaced", FontWeight.BOLD, 20));
        bossHealthText.setFill(Color.WHITE);
        bossHealthText.setX(root.getScene().getWidth() - 300);
        bossHealthText.setY(50);

        bossHealthText.setVisible(true);
        bossHealthText.toFront();

        healthBarBackground = new Rectangle(HEALTH_BAR_X_POSITION, HEALTH_BAR_Y_POSITION, HEALTH_BAR_WIDTH, HEALTH_BAR_HEIGHT);
        healthBarBackground.setFill(Color.GREY);

        healthBar = new Rectangle(HEALTH_BAR_X_POSITION, HEALTH_BAR_Y_POSITION, HEALTH_BAR_WIDTH, HEALTH_BAR_HEIGHT);
        healthBar.setFill(Color.RED);

        root.getChildren().addAll(healthBarBackground, healthBar, bossHealthText);
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
        killsRemainingText.setVisible(false);
    }

    /**
     * Updates the boss's health bar and text to reflect the current health percentage.
     *
     * @param bossHealth the current health of the boss.
     * @param maxHealth  the maximum health of the boss.
     */
    public void updateBossHealth(int bossHealth, int maxHealth) {
        double healthPercentage = (double) bossHealth / maxHealth;
        healthBar.setWidth(HEALTH_BAR_WIDTH * healthPercentage);

        bossHealthText.setText("Boss Health: " + bossHealth + "/" + maxHealth);

        healthBarBackground.toFront();
        healthBar.toFront();
        bossHealthText.toFront();
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