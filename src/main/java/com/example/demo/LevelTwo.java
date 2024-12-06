package com.example.demo;

import com.example.demo.controller.Controller;

public class LevelTwo extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private final Boss boss;
	private LevelViewLevelTwo levelView;
	private static final String NEXT_LEVEL = "com.example.demo.LevelThree";

	public LevelTwo(double screenHeight, double screenWidth, Controller controller) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, controller);
		levelView.showShield();
		double levelThreeShieldProbability = 0.002;
		boss = new Boss(levelView, levelThreeShieldProbability);
	}

	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	@Override
	protected void checkIfGameOver() {
	    if (userIsDestroyed()) {
	        loseGame();
	    } else if (boss.isDestroyed()) {
	        goToNextLevel(NEXT_LEVEL);
	    }
	}

	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(boss);
		}
	}

	@Override
	protected LevelView instantiateLevelView() {
		levelView = new LevelViewLevelTwo(getRoot(), PLAYER_INITIAL_HEALTH);
		return levelView;
	}
	
	@Override
	protected void updateScene() {
	    super.updateScene();
	    levelView.updateBossHealth(boss.getHealth(), boss.getMaxHealth());
	}
}
