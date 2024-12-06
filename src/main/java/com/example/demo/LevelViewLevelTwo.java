package com.example.demo;

import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LevelViewLevelTwo extends LevelView {

	private static final int SHIELD_X_POSITION = 1150;
	private static final int SHIELD_Y_POSITION = 500;
	private static final int HEALTH_BAR_WIDTH = 300;
	private static final int HEALTH_BAR_HEIGHT = 20;
	private static final int HEALTH_BAR_X_POSITION = 950;
	private static final int HEALTH_BAR_Y_POSITION = 70;

	private final Group root;
	private final ShieldImage shieldImage;
	private final Text bossHealthText;
	private final Rectangle healthBar;
	private final Rectangle healthBarBackground;
	
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
	
	private void addImagesToRoot() {
		root.getChildren().addAll(shieldImage);
	}
	
	public void showShield() {
		shieldImage.showShield();
		shieldImage.toFront();
	}

	public void hideShield() {
		shieldImage.hideShield();
	}
	
	@Override
	public void updateKillsRemaining(int killsRemaining) {
	    killsRemainingText.setVisible(false);
	}
	
	public void updateBossHealth(int bossHealth, int maxHealth) {

        double healthPercentage = (double) bossHealth / maxHealth;
        healthBar.setWidth(HEALTH_BAR_WIDTH * healthPercentage);

        bossHealthText.setText("Boss Health: " + bossHealth + "/" + maxHealth);

        healthBarBackground.toFront();
        healthBar.toFront();
        bossHealthText.toFront();
    }

	public void updateShieldPosition(double x, double y) {
	    shieldImage.setPosition(x, y);
	}
}