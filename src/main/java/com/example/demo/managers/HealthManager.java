package com.example.demo.managers;

public class HealthManager {
    private int health;

    public HealthManager(int initialHealth) {
        this.health = initialHealth;
    }

    public void decrementHealth() {
        health--;
    }

    public boolean isHealthZero() {
        return health == 0;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
