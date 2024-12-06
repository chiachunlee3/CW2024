package com.example.demo;

import com.example.demo.controller.Controller;

public class LevelThree extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background3.jpg";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private static final int TOTAL_ENEMIES = 5;
    private static final double ENEMY_SPAWN_PROBABILITY = 0.15;
    private Boss boss;
    private LevelViewLevelThree levelView;

    public LevelThree(double screenHeight, double screenWidth, Controller controller) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, controller);
    }

    @Override
    protected LevelView instantiateLevelView() {
        // Initialize levelView here
        levelView = new LevelViewLevelThree(getRoot(), PLAYER_INITIAL_HEALTH);
        double levelThreeShieldProbability = 0.01;
        // Initialize the boss after levelView is ready
        boss = new Boss(levelView, levelThreeShieldProbability);

        return levelView;
    }

    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        } else if (boss.isDestroyed() && getUser().getNumberOfKills() >= TOTAL_ENEMIES) {
            winGame();
        }
    }

    @Override
    protected void spawnEnemyUnits() {
        int currentNumberOfEnemies = getCurrentNumberOfEnemies();
        for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
            if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
                double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
                ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
                addEnemyUnit(newEnemy);
            }
        }

        // Add the boss if not already added
        if (!getRoot().getChildren().contains(boss) && boss != null) {
            addEnemyUnit(boss);
        }
    }

    @Override
    protected void updateScene() {
        super.updateScene();
        if (boss != null) {
            levelView.updateBossHealth(boss.getHealth(), boss.getMaxHealth());
        }
    }
}
