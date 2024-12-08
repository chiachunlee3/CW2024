package com.example.demo;

/**
 * Represents an entity that can take damage and be destroyed.
 * This interface is typically implemented by objects or entities
 * that need to exhibit destructible behavior in an application.
 */
public interface Destructible {

    /**
     * Applies damage to the entity. The implementation should define
     * how damage is taken and any relevant side effects or changes
     * to the entity's state.
     */
    void takeDamage();

    /**
     * Destroys the entity. The implementation should specify the
     * behavior when the entity is destroyed, such as removing it
     * from a system or triggering destruction effects.
     */
    void destroy();

}
