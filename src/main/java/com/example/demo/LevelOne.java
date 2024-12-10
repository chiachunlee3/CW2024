package com.example.demo;

import com.example.demo.controller.Controller;

/**
 * Represents the first level of the game. This class extends {@code LevelParent}
 * and provides specific configurations, such as background, enemy behavior, and
 * conditions for advancing to the next level or losing the game.
 */
public class LevelOne extends LevelParent {

    /**
     * The file path to the background image for this level.
     */
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";

    /**
     * The fully qualified name of the next level to load after completing this level.
     */
    private static final String NEXT_LEVEL = "com.example.demo.LevelTwo";

    /**
     * The total number of enemy units allowed in the level.
     */
    private static final int TOTAL_ENEMIES = 5;

    /**
     * The number of kills required by the player to advance to the next level.
     */
    private static final int KILLS_TO_ADVANCE = 10;

    /**
     * The probability of spawning an enemy unit during each spawn cycle.
     */
    private static final double ENEMY_SPAWN_PROBABILITY = 0.20;

    /**
     * The initial health of the player at the start of this level.
     */
    private static final int PLAYER_INITIAL_HEALTH = 5;
    
    private final GameOverHandler gameOverHandler = new GameOverHandler();

    /**
     * Constructs a new {@code LevelOne} instance with specified screen dimensions
     * and game controller.
     *
     * @param screenHeight the height of the game screen in pixels.
     * @param screenWidth  the width of the game screen in pixels.
     * @param controller   the game controller managing this level.
     */
    public LevelOne(double screenHeight, double screenWidth, Controller controller) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, controller);
    }

    /**
     * Checks the game's state to determine if the player has lost or if the level is completed.
     * If the player is destroyed, the game ends. If the kill target is reached, the next level starts.
     */
    @Override
    protected void checkIfGameOver() {
        gameOverHandler.handleGameOver(
            this,
            userIsDestroyed(),
            userHasReachedKillTarget(),
            NEXT_LEVEL,
            null
        );
    }

    /**
     * Initializes the player's units and adds them to the level's display.
     */
    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    /**
     * Spawns enemy units into the level. The number of enemies spawned is determined
     * by the remaining allowed units and the spawn probability.
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
    }

    /**
     * Instantiates and returns the level view associated with this level.
     *
     * @return the {@code LevelView} for this level.
     */
    @Override
    protected LevelView instantiateLevelView() {
        return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
    }

    /**
     * Determines if the player has achieved the required number of kills to advance to the next level.
     *
     * @return {@code true} if the player has reached or exceeded the kill target, {@code false} otherwise.
     */
    private boolean userHasReachedKillTarget() {
        return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
    }

    /**
     * Retrieves the number of kills required to advance to the next level.
     *
     * @return the kill target for advancing to the next level.
     */
    public static int getKillsToAdvance() {
        return KILLS_TO_ADVANCE;
    }
}
