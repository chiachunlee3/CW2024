package com.example.demo.managers;

/**
 * The HealthManager class manages the health state of an entity.
 * It provides methods to decrement health, check if health is zero,
 * and get or set the current health value.
 */
public class HealthManager {

    /**
     * The current health value.
     */
    private int health;

    /**
     * Constructs a new HealthManager with the specified initial health.
     *
     * @param initialHealth the initial health value
     */
    public HealthManager(int initialHealth) {
        this.health = initialHealth;
    }

    /**
     * Decrements the health by one.
     */
    public void decrementHealth() {
        health--;
    }

    /**
     * Checks if the health is zero.
     *
     * @return true if the health is zero; false otherwise
     */
    public boolean isHealthZero() {
        return health == 0;
    }

    /**
     * Gets the current health value.
     *
     * @return the current health value
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets the health to the specified value.
     *
     * @param health the new health value
     */
    public void setHealth(int health) {
        this.health = health;
    }
}