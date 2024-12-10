package com.example.demo;

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

    private static final int HEALTH_BAR_WIDTH = 300; // Width of the health bar
    private static final int HEALTH_BAR_HEIGHT = 20; // Height of the health bar
    private static final int HEALTH_BAR_X_POSITION = 950; // X-coordinate for the health bar
    private static final int HEALTH_BAR_Y_POSITION = 70; // Y-coordinate for the health bar

    private final Text bossHealthText;
    private final Rectangle healthBar;
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
