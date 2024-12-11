package com.example.demo.managers;

import java.util.List;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.UserPlane;
import com.example.demo.projectiles.HealthProjectile;
import com.example.demo.visuals.RedScreenEffect;

/**
 * Manages collision detection and handling between various game entities
 * such as user planes, friendly units, enemy units, and projectiles.
 */
public class CollisionManager {
    private final RedScreenEffect hitEffect;
    private final UserPlane user;

    /**
     * Constructs a CollisionManager with the specified hit effect and user plane.
     *
     * @param hitEffect the visual effect triggered upon a hit
     * @param user the user's plane
     */
    public CollisionManager(RedScreenEffect hitEffect, UserPlane user) {
        this.hitEffect = hitEffect;
        this.user = user;
    }

    /**
     * Handles collisions between friendly units and enemy units.
     *
     * @param friendlyUnits the list of friendly units
     * @param enemyUnits the list of enemy units
     */
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

    /**
     * Handles collisions between user projectiles and enemy units.
     *
     * @param userProjectiles the list of user projectiles
     * @param enemyUnits the list of enemy units
     */
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

    /**
     * Handles collisions between enemy projectiles and friendly units.
     *
     * @param enemyProjectiles the list of enemy projectiles
     * @param friendlyUnits the list of friendly units
     */
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