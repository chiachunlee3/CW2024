/**
 * Abstract class representing a level in a game.
 * Provides core functionality for managing user interaction, game updates, and scene rendering.
 * Each concrete level class should extend this class and implement its abstract methods.
 */
package com.example.demo;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.util.Duration;
import javafx.scene.effect.GaussianBlur;

import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import javafx.scene.effect.DropShadow;
import javafx.animation.PauseTransition;
import com.example.demo.controller.Controller;

public abstract class LevelParent {

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
     * Maximum Y position for enemy units to avoid leaving screen bounds.
     */
    private final Group root;
    
    /**
     * Timeline for handling game loop animations.
     */
    private final Timeline timeline;
    
    /**
     * User-controlled plane in the level.
     */
    private final UserPlane user;
    
    /**
     * The main scene of the level.
     */
    private final Scene scene;
    
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
     * List of projectiles fired by the user.
     */
    private final List<ActiveActorDestructible> userProjectiles;
    
    /**
     * List of projectiles fired by enemies.
     */
    private final List<ActiveActorDestructible> enemyProjectiles;
    
    /**
     * Current number of enemies in the level.
     */
    private int currentNumberOfEnemies;
    
    /**
     * View for displaying level-related information.
     */
    private LevelView levelView;
    
    /**
     * Callback function to handle level transitions.
     */
    private Consumer<String> levelChangeCallback;
    
    /**
     * Overlay group for rendering pause screen effects.
     */
    private final Group pauseOverlay = new Group();
    
    /**
     * Blur effect applied during pause.
     */
    private final GaussianBlur blurEffect = new GaussianBlur(10); 
    
    /**
     * Visual effect for indicating hits.
     */
    private RedScreenEffect hitEffect;
    
    /**
     * Overlay group for additional UI components.
     */
    private final Group overlayGroup = new Group();
    
    /**
     * Controller instance for handling level-specific logic.
     */
    private final Controller controller;
    
    
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
    	this.root = new Group();
        this.scene = new Scene(new Group(root, overlayGroup, pauseOverlay), screenWidth, screenHeight);
        this.timeline = new Timeline();
        levelView = new LevelView(root, playerInitialHealth);
        this.user = new UserPlane(playerInitialHealth, levelView);
        this.friendlyUnits = new ArrayList<>();
        this.enemyUnits = new ArrayList<>();
        this.userProjectiles = new ArrayList<>();
        this.enemyProjectiles = new ArrayList<>();

        this.background = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
        this.levelView = instantiateLevelView();
        this.currentNumberOfEnemies = 0;
        initializeTimeline();
        friendlyUnits.add(user);
        
        pauseOverlay.setMouseTransparent(true);
        
        hitEffect = new RedScreenEffect(screenWidth, screenHeight, overlayGroup);
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
        initializeBackground();
        initializeFriendlyUnits();
        levelView.showHeartDisplay();
        root.getChildren().add(levelView.getInstructionText());
        levelView.getInstructionText().toFront();
        return scene;
    }
    
    /**
     * Starts the game by playing the timeline.
     */
    public void startGame() {
        background.requestFocus();
        timeline.play();
    }
    
    /**
     * Sets a callback function to handle level changes.
     *
     * @param callback the callback function to be executed.
     */
    public void setOnLevelChange(Consumer<String> callback) {
        this.levelChangeCallback = callback;
    }
    
    /**
     * Transitions to the next level with a delay and callback.
     *
     * @param levelName the name of the next level.
     */
    protected void goToNextLevel(String levelName) {
        if (levelChangeCallback != null) {
            // Stop game updates
            timeline.stop();

            // Display "Level Cleared" message
            Text levelClearedText = new Text("Level Cleared!");
            levelClearedText.setFont(Font.font("Monospaced", FontWeight.BOLD,100));
            levelClearedText.setFill(Color.RED);
            levelClearedText.setEffect(new DropShadow(5, Color.BLACK));
            levelClearedText.setX(screenWidth / 2 - 420); // Center horizontally
            levelClearedText.setY(screenHeight / 2);      // Center vertically

            root.getChildren().add(levelClearedText);

            // Delay before transitioning
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> {
                root.getChildren().remove(levelClearedText); // Clean up the message
                levelChangeCallback.accept(levelName);
            });
            pause.play();
        }
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
     * Initializes the game timeline that drives the game loop.
     * The timeline executes the `updateScene` method at regular intervals.
     */
    private void initializeTimeline() {
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
        timeline.getKeyFrames().add(gameLoop);
    }

    /**
     * Sets up the background image for the level and initializes key event handlers.
     * The handlers respond to user inputs such as moving the player, firing projectiles, and pausing or restarting the game.
     */
    private void initializeBackground() {
        background.setFocusTraversable(true);
        background.setFitHeight(screenHeight);
        background.setFitWidth(screenWidth);
        background.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                KeyCode kc = e.getCode();
                if (kc == KeyCode.UP) user.moveUp();
                if (kc == KeyCode.DOWN) user.moveDown();
                if (kc == KeyCode.SPACE) fireProjectile();
                if (kc == KeyCode.P) togglePause(); // Pause the game
                if (kc == KeyCode.R) restartGame(); // Restart the game
            }
        });

        background.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                KeyCode kc = e.getCode();
                if (kc == KeyCode.UP || kc == KeyCode.DOWN) user.stop();
            }
        });
        root.getChildren().add(background);
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
        root.getChildren().add(projectile);
        userProjectiles.add(projectile);
    }

    /**
     * Generates projectiles fired by enemy units.
     * Each enemy may fire a projectile based on its behavior.
     */
    private void generateEnemyFire() {
        enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
    }

    /**
     * Spawns a projectile for an enemy unit.
     * Adds the projectile to the scene graph and tracks it in the enemy projectiles list.
     *
     * @param projectile the projectile to spawn.
     */
    private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
        if (projectile != null) {
            root.getChildren().add(projectile);
            enemyProjectiles.add(projectile);
        }
    }

    /**
     * Updates all active actors in the scene, including friendly units, enemies, and projectiles.
     */
    private void updateActors() {
        friendlyUnits.forEach(plane -> plane.updateActor());
        enemyUnits.forEach(enemy -> enemy.updateActor());
        userProjectiles.forEach(projectile -> projectile.updateActor());
        enemyProjectiles.forEach(projectile -> projectile.updateActor());
    }

    /**
     * Removes all actors that have been destroyed from the scene and tracking lists.
     * This method ensures the scene graph and actor lists are consistent with the game state.
     */
    private void removeAllDestroyedActors() {
        removeDestroyedActors(friendlyUnits);
        removeDestroyedActors(enemyUnits);
        removeDestroyedActors(userProjectiles);
        removeDestroyedActors(enemyProjectiles);
    }
    
    /**
     * Removes destroyed actors from a given list and the scene graph.
     *
     * @param actors the list of actors to check for destruction.
     */
    private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
        List<ActiveActorDestructible> destroyedActors = actors.stream()
                .filter(ActiveActorDestructible::isDestroyed)
                .collect(Collectors.toList());
        root.getChildren().removeAll(destroyedActors);
        actors.removeAll(destroyedActors);
    }
    
    /**
     * Handles collisions between planes (friendly and enemy units).
     * Each collision results in both entities taking damage.
     */
    private void handlePlaneCollisions() {
        handleCollisions(friendlyUnits, enemyUnits);
    }
    
    /**
     * Handles collisions between user projectiles and enemy units.
     * Damaged entities are appropriately updated.
     */
    private void handleUserProjectileCollisions() {
        handleCollisions(userProjectiles, enemyUnits);
    }

    /**
     * Handles collisions between enemy projectiles and friendly units.
     * Includes special handling for health projectiles that heal the user.
     */
    private void handleEnemyProjectileCollisions() {
        for (ActiveActorDestructible projectile : enemyProjectiles) {
            for (ActiveActorDestructible friendlyUnit : friendlyUnits) {
                if (projectile instanceof HealthProjectile &&
                    projectile.getPreciseBounds().intersects(friendlyUnit.getPreciseBounds())) {
                    // Increase health and destroy the projectile
                    getUser().increaseHealth();
                    projectile.destroy();
                    continue;
                }
                if (projectile.getPreciseBounds().intersects(friendlyUnit.getPreciseBounds())) {
                    projectile.takeDamage();
                    friendlyUnit.takeDamage();
                    hitEffect.trigger();
                }
            }
        }
    }

    /**
     * Handles collisions between two lists of actors.
     * Any collision results in both actors taking damage, with special handling for user plane collisions.
     *
     * @param actors1 the first list of actors.
     * @param actors2 the second list of actors.
     */
    private void handleCollisions(List<ActiveActorDestructible> actors1, List<ActiveActorDestructible> actors2) {
        for (ActiveActorDestructible actor : actors2) {
            for (ActiveActorDestructible otherActor : actors1) {
                if (actor.getPreciseBounds().intersects(otherActor.getPreciseBounds())) {
                    actor.takeDamage();
                    otherActor.takeDamage();
                    if (otherActor instanceof UserPlane || actor instanceof UserPlane) {
                        hitEffect.trigger(); 
                    }
                }
            }
        }
    }

    /**
     * Handles scenarios where an enemy unit penetrates the user's defenses.
     * The user takes damage, and the penetrating enemy is destroyed.
     */
    private void handleEnemyPenetration() {
        for (ActiveActorDestructible enemy : enemyUnits) {
            if (enemyHasPenetratedDefenses(enemy)) {
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
     * Determines if an enemy unit has penetrated the user's defenses by checking its X position.
     *
     * @param enemy the enemy unit to check.
     * @return true if the enemy has penetrated defenses, false otherwise.
     */
    private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
        return Math.abs(enemy.getTranslateX()) > screenWidth;
    }
    
    /**
     * Ends the game with a win condition.
     */
    protected void winGame() {
        timeline.stop();
        levelView.showWinImage();
    }
    
    /**
     * Ends the game with a lose condition.
     */
    protected void loseGame() {
        timeline.stop();
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
        return root;
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
        enemyUnits.add(enemy);
        root.getChildren().add(enemy);
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
        currentNumberOfEnemies = enemyUnits.size();
    }
    
    /**
     * Indicates whether the game is currently paused.
     */
    private boolean isPaused = false;
    
    /**
     * Toggles the pause state of the game.
     */
    public void togglePause() {
        isPaused = !isPaused;
        if (isPaused) {
            timeline.pause();
            levelView.showPauseText();
            root.setEffect(blurEffect);
            pauseOverlay.getChildren().add(levelView.getPauseText());
            pauseOverlay.getChildren().add(levelView.getMainMenuButton());
            pauseOverlay.setMouseTransparent(false);
        } else {
            timeline.play();
            levelView.hidePauseText();
            root.setEffect(null);
            pauseOverlay.getChildren().clear();
        }
    }

    /**
     * Checks if the game is currently paused.
     *
     * @return true if the game is paused, false otherwise.
     */
    public boolean isPaused() {
        return isPaused;
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