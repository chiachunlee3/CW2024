package com.example.demo.levels;

import com.example.demo.actors.Boss;
import com.example.demo.controller.Controller;
import com.example.demo.states.GameOverHandler;

/**
 * Represents the second level of the game.
 * Extends {@link LevelParent} and manages specific features of Level 2 such as
 * the boss, enemy spawning, and transitioning to the next level.
 */
public class LevelTwo extends LevelParent {

    /**
     * Path to the background image used for Level 2.
     */
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";

    /**
     * Initial health of the player for Level 2.
     */
    private static final int PLAYER_INITIAL_HEALTH = 5;

    /**
     * The fully qualified name of the next level to transition to.
     */
    private static final String NEXT_LEVEL = "com.example.demo.levels.LevelThree";

    /**
     * The boss enemy for Level 2.
     */
    private final Boss boss;

    /**
     * The view associated with Level 2.
     */
    private LevelViewLevelTwo levelView;
    
    /**
     * Handles game-over logic for Level 2, determining when the game ends
     * or transitions to the next level.
     */
    private final GameOverHandler gameOverHandler = new GameOverHandler();

    /**
     * Constructs a new LevelTwo instance with the specified dimensions and controller.
     *
     * @param screenHeight the height of the game screen.
     * @param screenWidth  the width of the game screen.
     * @param controller   the controller managing user input and game logic.
     */
    public LevelTwo(double screenHeight, double screenWidth, Controller controller) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, controller);
        double levelThreeShieldProbability = 0.002;
        boss = new Boss(levelView, levelThreeShieldProbability);
    }

    /**
     * Initializes friendly units, specifically the user-controlled character, in Level 2.
     */
    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    /**
     * Checks if the game is over for Level 2, based on the player's and boss's status.
     * If the player is destroyed, the game ends. If the boss is destroyed, the game
     * transitions to the next level.
     */
    @Override
    protected void checkIfGameOver() {
        gameOverHandler.handleGameOver(
            this,
            userIsDestroyed(),
            boss.isDestroyed(),
            NEXT_LEVEL,
            null
        );
    }

    /**
     * Spawns enemy units for Level 2, including the boss.
     * Ensures the boss is added if no enemies are present.
     */
    @Override
    protected void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnit(boss);
        }
    }

    /**
     * Instantiates the view for Level 2.
     *
     * @return the view associated with Level 2.
     */
    @Override
    protected LevelView instantiateLevelView() {
        levelView = new LevelViewLevelTwo(getRoot(), PLAYER_INITIAL_HEALTH);
        return levelView;
    }

    /**
     * Updates the scene for Level 2, including updating the boss's health in the view.
     * Delegates other update logic to the parent class.
     */
    @Override
    protected void updateScene() {
        super.updateScene();
        levelView.updateBossHealth(boss.getHealth(), boss.getMaxHealth());
    }
}