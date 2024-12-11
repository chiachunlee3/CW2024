package com.example.demo.states;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DestructionStateTest {

    private DestructionState destructionState;

    @BeforeEach
    public void setUp() {
        // Initialize the object before each test
        destructionState = new DestructionState();
    }

    @Test
    public void testInitialStateIsNotDestroyed() {
        // Verify that the initial state is false
        assertFalse(destructionState.isDestroyed(), "The initial state should be not destroyed (false).");
    }

    @Test
    public void testSetDestroyedToTrue() {
        // Change the state to true
        destructionState.setDestroyed(true);
        // Verify that the state is updated correctly
        assertTrue(destructionState.isDestroyed(), "The state should be destroyed (true) after setting it to true.");
    }

    @Test
    public void testSetDestroyedToFalse() {
        // Set the state to true first
        destructionState.setDestroyed(true);
        // Now set it back to false
        destructionState.setDestroyed(false);
        // Verify that the state is updated correctly
        assertFalse(destructionState.isDestroyed(), "The state should be not destroyed (false) after setting it to false.");
    }
}