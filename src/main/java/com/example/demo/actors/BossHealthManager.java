package com.example.demo.actors;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Manages the boss health display, including the health bar and health text.
 */
public class BossHealthManager {
	/**
     * The width of the health bar.
     */
    private static final int HEALTH_BAR_WIDTH = 300;

    /**
     * The height of the health bar.
     */
    private static final int HEALTH_BAR_HEIGHT = 20;

    /**
     * The X-coordinate for the health bar.
     */
    private static final int HEALTH_BAR_X_POSITION = 950;

    /**
     * The Y-coordinate for the health bar.
     */
    private static final int HEALTH_BAR_Y_POSITION = 70;

    /**
     * The text element displaying the boss's health status.
     */
    private final Text bossHealthText;

    /**
     * The rectangle representing the boss's current health bar.
     */
    private final Rectangle healthBar;

    /**
     * The rectangle representing the background of the health bar.
     */
    private final Rectangle healthBarBackground;

    /**
     * Constructs a new {@code BossHealthManager}.
     *
     * @param root the root {@code Group} to which elements will be added.
     */
    public BossHealthManager(Group root) {
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
}