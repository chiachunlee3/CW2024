/**
 * Represents an active actor that can be destroyed within the game environment.
 * This class extends {@link ActiveActor} and implements {@link Destructible},
 * providing functionality to manage an actor's destruction state and behavior.
 */
package com.example.demo.actors;

import com.example.demo.states.DestructionState;

/**
 * Abstract class representing a destructible active actor.
 * This class defines shared behavior for all destructible actors in the game.
 * It handles the destruction state and requires concrete implementations
 * for updating position, updating the actor's state, and taking damage.
 */
public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {
    
    /**
     * The state object representing the destruction status of the actor.
     */
    private final DestructionState destructionState;

    /**
     * Constructs a new destructible active actor.
     *
     * @param imageName   the name of the image associated with this actor.
     * @param imageHeight the height of the image.
     * @param initialXPos the initial X position of the actor.
     * @param initialYPos the initial Y position of the actor.
     */
    public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos) {
        super(imageName, imageHeight, initialXPos, initialYPos);
        this.destructionState = new DestructionState();
    }

    /**
     * Updates the position of the actor.
     * This method must be implemented by subclasses to define position update logic.
     */
    @Override
    public abstract void updatePosition();

    /**
     * Updates the actor's state.
     * Subclasses should provide specific logic for actor state updates.
     */
    public abstract void updateActor();

    /**
     * Handles taking damage by the actor.
     * Subclasses must implement this method to define damage-handling logic.
     */
    @Override
    public abstract void takeDamage();

    /**
     * Marks the actor as destroyed.
     * This method updates the destruction state to indicate that the actor is destroyed.
     */
    @Override
    public void destroy() {
        destructionState.setDestroyed(true);
    }

    /**
     * Checks if the actor is destroyed.
     *
     * @return {@code true} if the actor is destroyed; {@code false} otherwise.
     */
    public boolean isDestroyed() {
        return destructionState.isDestroyed();
    }
}
