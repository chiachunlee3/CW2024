package com.example.demo.levels;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.Boss;
import com.example.demo.actors.EnemyPlane;
import com.example.demo.controller.Controller;
import com.example.demo.states.GameOverHandler;

/**
 * Represents the third level of the game.
 * Extends {@link LevelParent} and manages specific features of Level 3 such as
 * the boss, enemy spawning, and level-specific views.
 */
public class LevelThree extends LevelParent {

    /**
     * Path to the background image used for Level 3.
     */
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background3.jpg";

    /**
     * Initial health of the player for Level 3.
     */
    private static final int PLAYER_INITIAL_HEALTH = 5;

    /**
     * Total number of enemy units in Level 3.
     */
    private static final int TOTAL_ENEMIES = 3;

    /**
     * Probability of spawning an enemy during each update cycle.
     */
    private static final double ENEMY_SPAWN_PROBABILITY = 0.1;

    /**
     * The boss enemy for Level 3.
     */
    private Boss boss;

    /**
     * The view associated with Level 3.
     */
    private LevelViewLevelThree levelView;
    
    /**
     * Handles the game-over logic for Level 3, determining whether the player
     * wins or loses based on the game's state.
     */
    private final GameOverHandler gameOverHandler = new GameOverHandler();

    /**
     * Constructs a new LevelThree instance with the specified dimensions and controller.
     *
     * @param screenHeight the height of the game screen.
     * @param screenWidth  the width of the game screen.
     * @param controller   the controller managing user input and game logic.
     */
    public LevelThree(double screenHeight, double screenWidth, Controller controller) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, controller);
    }

    /**
     * Instantiates the view for Level 3 and initializes the boss entity.
     *
     * @return the view associated with Level 3.
     */
    @Override
    protected LevelView instantiateLevelView() {
        levelView = new LevelViewLevelThree(getRoot(), PLAYER_INITIAL_HEALTH);
        double levelThreeShieldProbability = 0.005;
        boss = new Boss(levelView, levelThreeShieldProbability);
        return levelView;
    }

    /**
     * Initializes friendly units, specifically the user-controlled character, in Level 3.
     */
    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    /**
     * Checks if the game is over for Level 3, based on the player's and boss's status.
     * Ends the game with a win or loss accordingly.
     */
    @Override
    protected void checkIfGameOver() {
        gameOverHandler.handleGameOver(
            this,
            userIsDestroyed(),
            boss.isDestroyed() && getUser().getNumberOfKills() >= TOTAL_ENEMIES,
            null,
            this::winGame
        );
    }

    /**
     * Spawns enemy units for Level 3, including regular enemies and the boss.
     * Ensures that the total number of enemies does not exceed the allowed limit.
     */
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

        if (!getRoot().getChildren().contains(boss) && boss != null) {
            addEnemyUnit(boss);
        }
    }

    /**
     * Updates the scene for Level 3, including updating the boss's health in the view.
     * Delegates other update logic to the parent class.
     */
    @Override
    protected void updateScene() {
        super.updateScene();
        if (boss != null) {
            levelView.updateBossHealth(boss.getHealth(), boss.getMaxHealth());
        }
    }
}
