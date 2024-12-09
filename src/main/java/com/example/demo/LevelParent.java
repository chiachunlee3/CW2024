/**
 * Abstract class representing a level in a game.
 * Provides core functionality for managing user interaction, game updates, and scene rendering.
 * Each concrete level class should extend this class and implement its abstract methods.
 */
package com.example.demo;

import java.util.*;
import java.util.function.Consumer;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import com.example.demo.controller.Controller;

public abstract class LevelParent {
	
	private final PauseManager pauseManager;

    private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
    private static final int MILLISECOND_DELAY = 50;
    
    /**
     * Height of the screen.
     */
    private final double screenHeight;
    
    /**
     * Width of the screen.
     */
    private final double screenWidth;
    
    /**
     * Maximum Y position for enemy units to avoid leaving screen bounds.
     */
    private final double enemyMaximumYPosition;
    
    /**
     * User-controlled plane in the level.
     */
    private final UserPlane user;
    
    /**
     * Background image for the level.
     */
    private final ImageView background;
    
    /**
     * List of friendly units in the level.
     */
    private final List<ActiveActorDestructible> friendlyUnits;
    
    /**
     * List of enemy units in the level.
     */
    private final List<ActiveActorDestructible> enemyUnits;
    
    /**
     * Current number of enemies in the level.
     */
    private int currentNumberOfEnemies;
    
    /**
     * View for displaying level-related information.
     */
    private LevelView levelView;
    
    /**
     * Visual effect for indicating hits.
     */
    private RedScreenEffect hitEffect;
    
    /**
     * Controller instance for handling level-specific logic.
     */
    private final Controller controller;
    
    /**
     * Indicates whether the game is currently paused.
     */
    private boolean isPaused = false;
    
    //It's constructor adds the background image to the root node and sets up key event listeners.
    private final BackgroundManager backgroundManager;
    
    private final ActorManager actorManager;
    
    private final CollisionManager collisionManager;
    
    private final GameLoopManager gameLoopManager;
    
    private final EnemyManager enemyManager;
    
    private final SceneManager sceneManager;
    
    private final LevelProjectileManager projectileManager;

    private final LevelTransitionManager levelTransitionManager;

    /**
     * Constructs a new LevelParent instance with specified parameters.
     *
     * @param backgroundImageName the name of the background image resource.
     * @param screenHeight        the height of the game screen.
     * @param screenWidth         the width of the game screen.
     * @param playerInitialHealth the initial health of the player.
     * @param controller          the controller to manage level logic.
     */
    public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth, Controller controller) {
        this.controller = controller;
        this.sceneManager = new SceneManager(screenWidth, screenHeight);
        
        // Use SceneManager's getters instead of direct field access
        Group root = sceneManager.getRoot();
        //Scene scene = sceneManager.getScene();
        Group overlayGroup = sceneManager.getOverlayGroup();
        Group pauseOverlay = sceneManager.getPauseOverlay();
        this.gameLoopManager = new GameLoopManager(MILLISECOND_DELAY, this::updateScene);
        this.levelTransitionManager = new LevelTransitionManager(sceneManager.getRoot(), screenWidth, screenHeight, gameLoopManager);
        
        this.levelView = instantiateLevelView();
        this.user = new UserPlane(playerInitialHealth, levelView);
        this.friendlyUnits = new ArrayList<>();
        this.enemyUnits = new ArrayList<>();
        this.enemyManager = new EnemyManager(enemyUnits, SCREEN_HEIGHT_ADJUSTMENT, screenWidth, root);

        this.background = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
        
        this.currentNumberOfEnemies = 0;
        friendlyUnits.add(user);

        pauseOverlay.setMouseTransparent(true);

        hitEffect = new RedScreenEffect(screenWidth, screenHeight, overlayGroup);

        this.pauseManager = new PauseManager(gameLoopManager, root, pauseOverlay, levelView);

        this.backgroundManager = new BackgroundManager(
            backgroundImageName, screenHeight, screenWidth, root, user,
            this::fireProjectile, this::togglePause, this::restartGame
        );

        this.actorManager = new ActorManager(root);

        this.collisionManager = new CollisionManager(hitEffect, user);
        
        this.projectileManager = new LevelProjectileManager(
        	    sceneManager.getRoot(),
        	    new ArrayList<>(),
        	    new ArrayList<>()
        	);

    }
    
    /**
     * Initializes the friendly units in the level.
     * To be implemented by subclasses.
     */
    protected abstract void initializeFriendlyUnits();
    
    /**
     * Checks if the game is over and takes appropriate actions.
     * To be implemented by subclasses.
     */
    protected abstract void checkIfGameOver();
    
    /**
     * Spawns enemy units in the level.
     * To be implemented by subclasses.
     */
    protected abstract void spawnEnemyUnits();
    
    /**
     * Instantiates and returns a LevelView object for the level.
     * To be implemented by subclasses.
     *
     * @return the instantiated LevelView object.
     */
    protected abstract LevelView instantiateLevelView();
    
    /**
     * Initializes and returns the scene for this level.
     *
     * @return the initialized scene.
     */
    public Scene initializeScene() {
        initializeFriendlyUnits();
        levelView.showHeartDisplay();
        sceneManager.getRoot().getChildren().add(levelView.getInstructionText());
        levelView.getInstructionText().toFront();
        return sceneManager.getScene();
    }
    
    /**
     * Starts the game by playing the timeline.
     */
    public void startGame() {
        background.requestFocus();
    	gameLoopManager.start();
    }
    
    /**
     * Sets a callback function to handle level changes.
     *
     * @param callback the callback function to be executed.
     */
    public void setOnLevelChange(Consumer<String> callback) {
    	levelTransitionManager.setOnLevelChange(callback);
    }
    
    /**
     * Transitions to the next level with a delay and callback.
     *
     * @param levelName the name of the next level.
     */
    protected void goToNextLevel(String levelName) {
    	levelTransitionManager.goToNextLevel(levelName);
    }
    
    /**
     * Updates the scene by performing game logic and rendering updates.
     */
    protected void updateScene() {
    	if (isPaused) return;
        spawnEnemyUnits();
        updateActors();
        generateEnemyFire();
        updateNumberOfEnemies();
        handleEnemyPenetration();
        handleUserProjectileCollisions();
        handleEnemyProjectileCollisions();
        handlePlaneCollisions();
        removeAllDestroyedActors();
        updateKillCount();
        updateLevelView();
        checkIfGameOver();
    }

    /**
     * Restarts the game by invoking the restart logic in the controller.
     * If the controller is null, a warning message is logged.
     */
    private void restartGame() {
        if (controller != null) {
            controller.restartLevel();
        } else {
            System.err.println("Controller is null, cannot restart game.");
        }
    }

    /**
     * Fires a projectile from the user's plane.
     * The projectile is added to the scene graph and tracked in the user projectiles list.
     */
    private void fireProjectile() {
        ActiveActorDestructible projectile = user.fireProjectile();
        projectileManager.addUserProjectile(projectile);
    }

    /**
     * Generates projectiles fired by enemy units.
     * Each enemy may fire a projectile based on its behavior.
     */
    private void generateEnemyFire() {
        enemyUnits.forEach(enemy -> {
            ActiveActorDestructible projectile = ((FighterPlane) enemy).fireProjectile();
            projectileManager.addEnemyProjectile(projectile);
        });
    }

    /**
     * Updates all active actors in the scene, including friendly units, enemies, and projectiles.
     */
    private void updateActors() {
    	actorManager.updateActors(friendlyUnits);
        actorManager.updateActors(enemyUnits);
        projectileManager.updateProjectiles();
    }

    /**
     * Removes all actors that have been destroyed from the scene and tracking lists.
     * This method ensures the scene graph and actor lists are consistent with the game state.
     */
    private void removeAllDestroyedActors() {
    	actorManager.removeDestroyedActors(friendlyUnits);
    	enemyManager.removeDestroyedEnemies();
    	projectileManager.removeDestroyedProjectiles();
    }
    
    /**
     * Handles collisions between planes (friendly and enemy units).
     * Each collision results in both entities taking damage.
     */
    private void handlePlaneCollisions() {
    	collisionManager.handlePlaneCollisions(friendlyUnits, enemyUnits);
    }
    
    /**
     * Handles collisions between user projectiles and enemy units.
     * Damaged entities are appropriately updated.
     */
    private void handleUserProjectileCollisions() {
    	collisionManager.handleUserProjectileCollisions(
    	        projectileManager.getUserProjectiles(),
    	        enemyUnits
    	    );
    }

    /**
     * Handles collisions between enemy projectiles and friendly units.
     * Includes special handling for health projectiles that heal the user.
     */
    private void handleEnemyProjectileCollisions() {
    	collisionManager.handleEnemyProjectileCollisions(
    	        projectileManager.getEnemyProjectiles(),
    	        friendlyUnits
    	    );
    }

    /**
     * Handles scenarios where an enemy unit penetrates the user's defenses.
     * The user takes damage, and the penetrating enemy is destroyed.
     */
    private void handleEnemyPenetration() {
        for (ActiveActorDestructible enemy : enemyUnits) {
        	if (enemyManager.hasPenetratedDefenses(enemy)) {
                user.takeDamage();
                hitEffect.trigger();
                enemy.destroy();
            }
        }
    }

    /**
     * Updates the level view based on the current game state.
     * Updates health hearts, kill count, and any other level-related UI elements.
     */
    private void updateLevelView() {
        int currentHealth = user.getHealth();
        int currentHeartCount = levelView.getCurrentHeartCount();

        if (currentHeartCount < currentHealth) {
            // Add hearts if health has increased
            for (int i = 0; i < currentHealth - currentHeartCount; i++) {
                levelView.addHeart();
            }
        } else if (currentHeartCount > currentHealth) {
            // Remove hearts if health has decreased
            levelView.removeHearts(currentHealth);
        }

        // Update other level view elements (e.g., kills remaining)
        int killsToAdvance = LevelOne.getKillsToAdvance();
        int killsRemaining = killsToAdvance - user.getNumberOfKills();
        levelView.updateKillsRemaining(Math.max(0, killsRemaining));
    }

    /**
     * Updates the player's kill count by calculating the number of enemies destroyed
     * since the last update. This ensures the player's score is accurate.
     */
    private void updateKillCount() {
        for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
            user.incrementKillCount();
        }
    }
    
    /**
     * Ends the game with a win condition.
     */
    protected void winGame() {
    	gameLoopManager.stop();
        levelView.showWinImage();
    }
    
    /**
     * Ends the game with a lose condition.
     */
    protected void loseGame() {
    	gameLoopManager.stop();
        levelView.showGameOverImage();
    }

    /**
     * Returns the user-controlled plane.
     *
     * @return the user-controlled plane.
     */
    protected UserPlane getUser() {
        return user;
    }
    
    /**
     * Returns the root node of the scene graph.
     *
     * @return the root node.
     */
    protected Group getRoot() {
    	return sceneManager.getRoot();
    }

    /**
     * Returns the current number of enemies in the level.
     *
     * @return the number of enemies.
     */
    protected int getCurrentNumberOfEnemies() {
        return enemyUnits.size();
    }
    
    /**
     * Adds an enemy unit to the level.
     *
     * @param enemy the enemy unit to add.
     */
    protected void addEnemyUnit(ActiveActorDestructible enemy) {
    	enemyManager.spawnEnemy(enemy);
    }

    /**
     * Returns the maximum Y position for enemies.
     *
     * @return the maximum Y position.
     */
    protected double getEnemyMaximumYPosition() {
        return enemyMaximumYPosition;
    }

    /**
     * Returns the screen width.
     *
     * @return the screen width.
     */
    protected double getScreenWidth() {
        return screenWidth;
    }

    /**
     * Checks if the user-controlled plane is destroyed.
     *
     * @return true if the user plane is destroyed, false otherwise.
     */
    protected boolean userIsDestroyed() {
        return user.isDestroyed();
    }
    
    /**
     * Updates the current number of enemies by syncing it with the size of the `enemyUnits` list.
     * This method ensures that the internal tracking of enemy count is consistent with the actual
     * number of active enemies in the scene.
     */
    private void updateNumberOfEnemies() {
    	currentNumberOfEnemies = enemyManager.getCurrentNumberOfEnemies();
    }
    
    /**
     * Toggles the pause state of the game.
     */
    public void togglePause() {
    	pauseManager.togglePause();
    }

    /**
     * Checks if the game is currently paused.
     *
     * @return true if the game is paused, false otherwise.
     */
    public boolean isPaused() {
        return pauseManager.isPaused();
    }
    
    /**
     * Returns the screen height.
     *
     * @return the screen height.
     */
    protected double getScreenHeight() {
        return screenHeight;
    }
}