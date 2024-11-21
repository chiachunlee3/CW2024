package com.example.demo;

import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;

public class LevelViewLevelTwo extends LevelView {

	private static final int SHIELD_X_POSITION = 1150;
	private static final int SHIELD_Y_POSITION = 500;
	private final Group root;
	private final ShieldImage shieldImage;
	private final Text bossHealthText;
	
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
        root.getChildren().add(bossHealthText);
        
        bossHealthText.setVisible(true);
        bossHealthText.toFront();
	}
	
	private void addImagesToRoot() {
		root.getChildren().addAll(shieldImage);
	}
	
	public void showShield() {
		shieldImage.showShield();
	}

	public void hideShield() {
		shieldImage.hideShield();
	}
	
	@Override
	public void updateKillsRemaining(int killsRemaining) {
	    killsRemainingText.setVisible(false);
	}
	
	public void updateBossHealth(int bossHealth) {
        bossHealthText.setText("Boss Health: " + bossHealth);
        bossHealthText.toFront();
    }
}
