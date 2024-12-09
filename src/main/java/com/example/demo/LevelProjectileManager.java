package com.example.demo;

import javafx.scene.Group;
import java.util.List;

public class LevelProjectileManager extends ProjectileManager {
    private final Group root;
    private final List<ActiveActorDestructible> userProjectiles;
    private final List<ActiveActorDestructible> enemyProjectiles;

    public LevelProjectileManager(Group root, List<ActiveActorDestructible> userProjectiles, List<ActiveActorDestructible> enemyProjectiles) {
        this.root = root;
        this.userProjectiles = userProjectiles;
        this.enemyProjectiles = enemyProjectiles;
    }

    public void addUserProjectile(ActiveActorDestructible projectile) {
        if (projectile != null && !root.getChildren().contains(projectile)) {
            root.getChildren().add(projectile);
            userProjectiles.add(projectile);
        }
    }

    public void addEnemyProjectile(ActiveActorDestructible projectile) {
        if (projectile != null && !root.getChildren().contains(projectile)) {
            root.getChildren().add(projectile);
            enemyProjectiles.add(projectile);
        }
    }

    public void updateProjectiles() {
        userProjectiles.forEach(ActiveActorDestructible::updateActor);
        enemyProjectiles.forEach(ActiveActorDestructible::updateActor);
    }

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

    public List<ActiveActorDestructible> getUserProjectiles() {
        return userProjectiles;
    }

    public List<ActiveActorDestructible> getEnemyProjectiles() {
        return enemyProjectiles;
    }
}
