package com.example.demo.managers;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.UserPlane;
import com.example.demo.visuals.RedScreenEffect;

import javafx.scene.Group;

public class ActorManager {
    private final Group root;

    public ActorManager(Group root) {
        this.root = root;
    }

    public void updateActors(List<ActiveActorDestructible> actors) {
        actors.forEach(ActiveActorDestructible::updateActor);
    }

    public void removeDestroyedActors(List<ActiveActorDestructible> actors) {
        List<ActiveActorDestructible> destroyedActors = actors.stream()
                .filter(ActiveActorDestructible::isDestroyed)
                .collect(Collectors.toList());
        root.getChildren().removeAll(destroyedActors);
        actors.removeAll(destroyedActors);
    }

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