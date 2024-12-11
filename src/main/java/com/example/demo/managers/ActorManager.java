package com.example.demo.managers;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.UserPlane;
import com.example.demo.visuals.RedScreenEffect;

import javafx.scene.Group;

/**
 * Manager class for handling various actor-related operations such as updating,
 * removing, and managing collisions of actors.
 */
public class ActorManager {
    private final Group root;

    /**
     * Constructs an ActorManager with the specified root group.
     *
     * @param root the root group that contains the visual elements of the actors
     */
    public ActorManager(Group root) {
        this.root = root;
    }

    /**
     * Updates the state of all actors in the provided list.
     *
     * @param actors the list of actors to update
     */
    public void updateActors(List<ActiveActorDestructible> actors) {
        actors.forEach(ActiveActorDestructible::updateActor);
    }

    /**
     * Removes destroyed actors from the provided list and from the root group's
     * children.
     *
     * @param actors the list of actors to check and remove if destroyed
     */
    public void removeDestroyedActors(List<ActiveActorDestructible> actors) {
        List<ActiveActorDestructible> destroyedActors = actors.stream()
                .filter(ActiveActorDestructible::isDestroyed)
                .collect(Collectors.toList());
        root.getChildren().removeAll(destroyedActors);
        actors.removeAll(destroyedActors);
    }

    /**
     * Handles collisions between two lists of actors. If a collision is detected,
     * actors take damage, and if a user plane is involved in the collision, a
     * red screen effect is triggered.
     *
     * @param actors1   the first list of actors to check for collisions
     * @param actors2   the second list of actors to check for collisions
     * @param hitEffect the visual effect triggered on collision
     * @param user      the user plane involved in potential collisions
     */
    public void handleCollisions(
        List<ActiveActorDestructible> actors1, 
        List<ActiveActorDestructible> actors2, 
        RedScreenEffect hitEffect,
        UserPlane user
    ) {
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
}