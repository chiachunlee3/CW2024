package com.example.demo.managers;

import java.util.List;

import com.example.demo.actors.ActiveActorDestructible;

import javafx.scene.Group;

/**
 * Manages a collection of enemy units and their interactions within the game.
 */
public class EnemyManager {
    private final List<ActiveActorDestructible> enemyUnits;
    private final double screenWidth;
    private final Group root;

    /**
     * Constructs an EnemyManager instance.
     * 
     * @param enemyUnits           List of destructible enemy units.
     * @param screenHeightAdjustment Unused adjustment for screen height.
     * @param screenWidth          The width of the screen boundary.
     * @param root                 The root group for rendering the enemies.
     */
    public EnemyManager(List<ActiveActorDestructible> enemyUnits, double screenHeightAdjustment, double screenWidth, Group root) {
        this.enemyUnits = enemyUnits;
        this.screenWidth = screenWidth;
        this.root = root;
    }

    /**
     * Spawns a new enemy and adds it to the game.
     * 
     * @param enemy The enemy to be spawned.
     */
    public void spawnEnemy(ActiveActorDestructible enemy) {
        enemyUnits.add(enemy);
        root.getChildren().add(enemy);
    }

    /**
     * Checks if a given enemy has penetrated the defensive boundary.
     * 
     * @param enemy The enemy to be checked.
     * @return {@code true} if the enemy has crossed the screen boundary; otherwise, {@code false}.
     */
    public boolean hasPenetratedDefenses(ActiveActorDestructible enemy) {
        return Math.abs(enemy.getTranslateX()) > screenWidth;
    }

    /**
     * Gets the current number of enemy units.
     * 
     * @return The number of active enemy units.
     */
    public int getCurrentNumberOfEnemies() {
        return enemyUnits.size();
    }

    /**
     * Removes destroyed enemies from the game.
     * Cleans up both the internal list and the rendering group.
     */
    public void removeDestroyedEnemies() {
        enemyUnits.removeIf(enemy -> {
            if (enemy.isDestroyed()) {
                root.getChildren().remove(enemy); // Remove from scene graph
                return true;
            }
            return false;
        });
    }
}