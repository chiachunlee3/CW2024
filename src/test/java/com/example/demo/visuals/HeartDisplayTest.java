package com.example.demo.visuals;

import javafx.application.Platform;
import javafx.scene.layout.HBox;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HeartDisplayTest {

    private static boolean isJavaFXInitialized = false;

    @BeforeAll
    public static void initJavaFX() {
        if (!isJavaFXInitialized) {
            Platform.startup(() -> {}); // Start JavaFX toolkit
            isJavaFXInitialized = true; // Ensure it initializes only once
        }
    }

    @Test
    public void testHeartDisplayInitialization() {
        HeartDisplay heartDisplay = new HeartDisplay(100, 100, 3);

        HBox container = heartDisplay.getContainer();
        assertNotNull(container, "Container should not be null");
        assertEquals(3, container.getChildren().size(), "Initial heart count should be 3");
    }

    @Test
    public void testAddHeart() {
        HeartDisplay heartDisplay = new HeartDisplay(100, 100, 3);

        heartDisplay.addHeart();
        assertEquals(4, heartDisplay.getCurrentHeartCount(), "Heart count should be 4 after adding a heart");
    }

    @Test
    public void testRemoveHeart() {
        HeartDisplay heartDisplay = new HeartDisplay(100, 100, 3);

        heartDisplay.removeHeart();
        assertEquals(2, heartDisplay.getCurrentHeartCount(), "Heart count should be 2 after removing a heart");
    }

    @Test
    public void testRemoveHeartWhenEmpty() {
        HeartDisplay heartDisplay = new HeartDisplay(100, 100, 0);

        heartDisplay.removeHeart();
        assertEquals(0, heartDisplay.getCurrentHeartCount(), "Heart count should remain 0 when removing from empty container");
    }
}