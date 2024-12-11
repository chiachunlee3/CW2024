package com.example.demo.managers;

import javafx.scene.Group;
import java.util.List;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.projectiles.ProjectileManager;

/**
 * Manages projectiles in a level, including user and enemy projectiles.
 */
public class LevelProjectileManager extends ProjectileManager {
    private final Group root;
    private final List<ActiveActorDestructible> userProjectiles;
    private final List<ActiveActorDestructible> enemyProjectiles;

    /**
     * Constructs a LevelProjectileManager.
     *
     * @param root the root group containing all projectiles
     * @param userProjectiles the list of user projectiles
     * @param enemyProjectiles the list of enemy projectiles
     */
    public LevelProjectileManager(Group root, List<ActiveActorDestructible> userProjectiles, List<ActiveActorDestructible> enemyProjectiles) {
        this.root = root;
        this.userProjectiles = userProjectiles;
        this.enemyProjectiles = enemyProjectiles;
    }

    /**
     * Adds a user projectile to the scene.
     *
     * @param projectile the projectile to add
     */
    public void addUserProjectile(ActiveActorDestructible projectile) {
        if (projectile != null && !root.getChildren().contains(projectile)) {
            root.getChildren().add(projectile);
            userProjectiles.add(projectile);
        }
    }

    /**
     * Adds an enemy projectile to the scene.
     *
     * @param projectile the projectile to add
     */
    public void addEnemyProjectile(ActiveActorDestructible projectile) {
        if (projectile != null && !root.getChildren().contains(projectile)) {
            root.getChildren().add(projectile);
            enemyProjectiles.add(projectile);
        }
    }

    /**
     * Updates all projectiles by calling their update method.
     */
    public void updateProjectiles() {
        userProjectiles.forEach(ActiveActorDestructible::updateActor);
        enemyProjectiles.forEach(ActiveActorDestructible::updateActor);
    }

    /**
     * Removes all projectiles that have been destroyed.
     */
    public void removeDestroyedProjectiles() {
        userProjectiles.removeIf(projectile -> {
            if (projectile.isDestroyed()) {
                root.getChildren().remove(projectile);
                return true;
            }
            return false;
        });

        enemyProjectiles.removeIf(projectile -> {
            if (projectile.isDestroyed()) {
                root.getChildren().remove(projectile);
                return true;
            }
            return false;
        });
    }

    /**
     * Retrieves the list of user projectiles.
     *
     * @return the list of user projectiles
     */
    public List<ActiveActorDestructible> getUserProjectiles() {
        return userProjectiles;
    }

    /**
     * Retrieves the list of enemy projectiles.
     *
     * @return the list of enemy projectiles
     */
    public List<ActiveActorDestructible> getEnemyProjectiles() {
        return enemyProjectiles;
    }
}