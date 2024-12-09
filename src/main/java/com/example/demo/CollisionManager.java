package com.example.demo;

import java.util.List;

public class CollisionManager {
    private final RedScreenEffect hitEffect;
    private final UserPlane user;

    public CollisionManager(RedScreenEffect hitEffect, UserPlane user) {
        this.hitEffect = hitEffect;
        this.user = user;
    }

    public void handlePlaneCollisions(List<ActiveActorDestructible> friendlyUnits, List<ActiveActorDestructible> enemyUnits) {
        for (ActiveActorDestructible friendly : friendlyUnits) {
            for (ActiveActorDestructible enemy : enemyUnits) {
                if (friendly.getPreciseBounds().intersects(enemy.getPreciseBounds())) {
                    friendly.takeDamage();
                    enemy.takeDamage();

                    // Trigger hit effect only if the user plane is involved
                    if (friendly instanceof UserPlane || enemy instanceof UserPlane) {
                        hitEffect.trigger();
                    }
                }
            }
        }
    }

    public void handleUserProjectileCollisions(List<ActiveActorDestructible> userProjectiles, List<ActiveActorDestructible> enemyUnits) {
        for (ActiveActorDestructible projectile : userProjectiles) {
            for (ActiveActorDestructible enemy : enemyUnits) {
                if (projectile.getPreciseBounds().intersects(enemy.getPreciseBounds())) {
                    projectile.takeDamage();
                    enemy.takeDamage();
                    // Do not trigger hit effect for user projectiles
                }
            }
        }
    }

    public void handleEnemyProjectileCollisions(List<ActiveActorDestructible> enemyProjectiles, List<ActiveActorDestructible> friendlyUnits) {
        for (ActiveActorDestructible projectile : enemyProjectiles) {
            for (ActiveActorDestructible friendly : friendlyUnits) {
                if (projectile.getPreciseBounds().intersects(friendly.getPreciseBounds())) {
                    // Special case for health projectiles
                    if (projectile instanceof HealthProjectile && friendly instanceof UserPlane) {
                        user.increaseHealth();
                        projectile.destroy();
                        continue;
                    }

                    // Regular collision logic
                    projectile.takeDamage();
                    friendly.takeDamage();

                    // Trigger hit effect only if the user plane is involved
                    if (friendly instanceof UserPlane) {
                        hitEffect.trigger();
                    }
                }
            }
        }
    }
}
