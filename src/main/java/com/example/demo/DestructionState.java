package com.example.demo;

/**
 * Represents the state of an entity regarding its destruction status.
 * This class provides methods to query and update whether an entity
 * is destroyed or not.
 */
public class DestructionState {
    /**
     * Indicates whether the entity is destroyed.
     */
    private boolean isDestroyed;

    /**
     * Constructs a new {@code DestructionState} instance with the initial
     * destruction status set to {@code false}.
     */
    public DestructionState() {
        this.isDestroyed = false;
    }

    /**
     * Checks if the entity is currently destroyed.
     *
     * @return {@code true} if the entity is destroyed; {@code false} otherwise.
     */
    public boolean isDestroyed() {
        return isDestroyed;
    }

    /**
     * Updates the destruction status of the entity.
     *
     * @param isDestroyed the new destruction status to set; {@code true}
     *                    if the entity should be marked as destroyed, 
     *                    {@code false} otherwise.
     */
    public void setDestroyed(boolean isDestroyed) {
        this.isDestroyed = isDestroyed;
    }
}
