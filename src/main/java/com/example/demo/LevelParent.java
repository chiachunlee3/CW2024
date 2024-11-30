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


public abstract class LevelParent {

    private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
    private static final int MILLISECOND_DELAY = 50;
    private final double screenHeight;
    private final double screenWidth;
    private final double enemyMaximumYPosition;

    private final Group root;
    private final Timeline timeline;
    private final UserPlane user;
    private final Scene scene;
    private final ImageView background;

    private final List<ActiveActorDestructible> friendlyUnits;
    private final List<ActiveActorDestructible> enemyUnits;
    private final List<ActiveActorDestructible> userProjectiles;
    private final List<ActiveActorDestructible> enemyProjectiles;
    
    private int currentNumberOfEnemies;
    private LevelView levelView;
    private Consumer<String> levelChangeCallback;
    
    private final Group pauseOverlay = new Group();
    private final GaussianBlur blurEffect = new GaussianBlur(10); 
    private RedScreenEffect hitEffect;
    private final Group overlayGroup = new Group();
    
    public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth) {
        this.root = new Group();
        this.scene = new Scene(new Group(root, overlayGroup, pauseOverlay), screenWidth, screenHeight);
        this.timeline = new Timeline();
        this.user = new UserPlane(playerInitialHealth);
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

    protected abstract void initializeFriendlyUnits();

    protected abstract void checkIfGameOver();

    protected abstract void spawnEnemyUnits();

    protected abstract LevelView instantiateLevelView();

    public Scene initializeScene() {
        initializeBackground();
        initializeFriendlyUnits();
        levelView.showHeartDisplay();
        return scene;
    }

    public void startGame() {
        background.requestFocus();
        timeline.play();
    }

    public void setOnLevelChange(Consumer<String> callback) {
        this.levelChangeCallback = callback;
    }

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

    private void initializeTimeline() {
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
        timeline.getKeyFrames().add(gameLoop);
    }

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
                if (kc == KeyCode.P) togglePause(); // Add toggle for pause
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

    private void fireProjectile() {
        ActiveActorDestructible projectile = user.fireProjectile();
        root.getChildren().add(projectile);
        userProjectiles.add(projectile);
    }

    private void generateEnemyFire() {
        enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
    }

    private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
        if (projectile != null) {
            root.getChildren().add(projectile);
            enemyProjectiles.add(projectile);
        }
    }

    private void updateActors() {
        friendlyUnits.forEach(plane -> plane.updateActor());
        enemyUnits.forEach(enemy -> enemy.updateActor());
        userProjectiles.forEach(projectile -> projectile.updateActor());
        enemyProjectiles.forEach(projectile -> projectile.updateActor());
    }

    private void removeAllDestroyedActors() {
        removeDestroyedActors(friendlyUnits);
        removeDestroyedActors(enemyUnits);
        removeDestroyedActors(userProjectiles);
        removeDestroyedActors(enemyProjectiles);
    }

    private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
        List<ActiveActorDestructible> destroyedActors = actors.stream()
                .filter(ActiveActorDestructible::isDestroyed)
                .collect(Collectors.toList());
        root.getChildren().removeAll(destroyedActors);
        actors.removeAll(destroyedActors);
    }

    private void handlePlaneCollisions() {
        handleCollisions(friendlyUnits, enemyUnits);
    }

    private void handleUserProjectileCollisions() {
        handleCollisions(userProjectiles, enemyUnits);
    }

    private void handleEnemyProjectileCollisions() {
        for (ActiveActorDestructible projectile : enemyProjectiles) {
            for (ActiveActorDestructible friendlyUnit : friendlyUnits) {
                if (projectile.getPreciseBounds().intersects(friendlyUnit.getPreciseBounds())) {
                    projectile.takeDamage();
                    friendlyUnit.takeDamage();
                    hitEffect.trigger(); // Trigger red screen effect
                }
            }
        }
    }

    private void handleCollisions(List<ActiveActorDestructible> actors1, List<ActiveActorDestructible> actors2) {
        for (ActiveActorDestructible actor : actors2) {
            for (ActiveActorDestructible otherActor : actors1) {
                if (actor.getPreciseBounds().intersects(otherActor.getPreciseBounds())) {
                    actor.takeDamage();
                    otherActor.takeDamage();
                    if (otherActor instanceof UserPlane || actor instanceof UserPlane) {
                        hitEffect.trigger(); // Trigger red screen effect for plane collisions
                    }
                }
            }
        }
    }


    private void handleEnemyPenetration() {
        for (ActiveActorDestructible enemy : enemyUnits) {
            if (enemyHasPenetratedDefenses(enemy)) {
                user.takeDamage();
                hitEffect.trigger();
                enemy.destroy();
            }
        }
    }

    private void updateLevelView() {
        levelView.removeHearts(user.getHealth());
        int killsToAdvance = LevelOne.getKillsToAdvance();
        int killsRemaining = killsToAdvance - user.getNumberOfKills();
        levelView.updateKillsRemaining(Math.max(0, killsRemaining));
    }

    private void updateKillCount() {
        for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
            user.incrementKillCount();
        }
    }

    private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
        return Math.abs(enemy.getTranslateX()) > screenWidth;
    }

    protected void winGame() {
        timeline.stop();
        levelView.showWinImage();
    }

    protected void loseGame() {
        timeline.stop();
        levelView.showGameOverImage();
    }

    protected UserPlane getUser() {
        return user;
    }

    protected Group getRoot() {
        return root;
    }

    protected int getCurrentNumberOfEnemies() {
        return enemyUnits.size();
    }

    protected void addEnemyUnit(ActiveActorDestructible enemy) {
        enemyUnits.add(enemy);
        root.getChildren().add(enemy);
    }

    protected double getEnemyMaximumYPosition() {
        return enemyMaximumYPosition;
    }

    protected double getScreenWidth() {
        return screenWidth;
    }

    protected boolean userIsDestroyed() {
        return user.isDestroyed();
    }

    private void updateNumberOfEnemies() {
        currentNumberOfEnemies = enemyUnits.size();
    }
    
    private boolean isPaused = false;

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
            levelView.hidePauseText(); // Do not hide the main menu button here.
            root.setEffect(null);
            pauseOverlay.getChildren().clear();
        }
    }


    public boolean isPaused() {
        return isPaused;
    }
    
    protected double getScreenHeight() {
        return screenHeight;
    }
}

