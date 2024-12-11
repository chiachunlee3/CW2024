package com.example.demo.managers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HealthManagerTest {

    @Test
    void testInitialHealth() {
        HealthManager healthManager = new HealthManager(100);
        assertEquals(100, healthManager.getHealth(), "Initial health should match the given value.");
    }

    @Test
    void testDecrementHealth() {
        HealthManager healthManager = new HealthManager(10);
        healthManager.decrementHealth();
        assertEquals(9, healthManager.getHealth(), "Health should decrement by 1.");
    }

    @Test
    void testIsHealthZero() {
        HealthManager healthManager = new HealthManager(1);
        assertFalse(healthManager.isHealthZero(), "Health should not be zero initially.");
        healthManager.decrementHealth();
        assertTrue(healthManager.isHealthZero(), "Health should be zero after decrementing to zero.");
    }

    @Test
    void testSetHealth() {
        HealthManager healthManager = new HealthManager(50);
        healthManager.setHealth(75);
        assertEquals(75, healthManager.getHealth(), "Health should be updated to the new value.");
    }
}
