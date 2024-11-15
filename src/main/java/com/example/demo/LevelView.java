package com.example.demo;

import javafx.scene.Group;

public class LevelView {
	
	private static final double HEART_DISPLAY_X_POSITION = 5;
	private static final double HEART_DISPLAY_Y_POSITION = 25;
	private static final int WIN_IMAGE_X_POSITION = 355;
	private static final int WIN_IMAGE_Y_POSITION = 175;
	private static final double GAME_OVER_IMAGE_WIDTH = 1000;
	private static final double GAME_OVER_IMAGE_HEIGHT = 850;
	private final Group root;
	private final WinImage winImage;
	private final GameOverImage gameOverImage;
	private final HeartDisplay heartDisplay;
	
	public LevelView(Group root, int heartsToDisplay) {
		this.root = root;
		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
		this.winImage = new WinImage(WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION);
		// Calculate the center position dynamically
	    double windowWidth = root.getScene().getWidth();
	    double windowHeight = root.getScene().getHeight();
	    int lossScreenXPosition = (int) (windowWidth / 2 - GAME_OVER_IMAGE_WIDTH / 2);
	    int lossScreenYPosition = (int) (windowHeight / 2 - GAME_OVER_IMAGE_HEIGHT / 2 - 40);
		this.gameOverImage = new GameOverImage(
				lossScreenXPosition, 
			    lossScreenYPosition,
			    GAME_OVER_IMAGE_WIDTH,
			    GAME_OVER_IMAGE_HEIGHT
			);
	}
	
	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}

	public void showWinImage() {
		root.getChildren().add(winImage);
		winImage.showWinImage();
	}
	
	public void showGameOverImage() {
		root.getChildren().add(gameOverImage);
	}
	
	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}

}
