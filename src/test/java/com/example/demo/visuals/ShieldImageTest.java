package com.example.demo.visuals;

import javafx.application.Platform;
import javafx.scene.image.Image;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShieldImageTest {

    @Test
    public void testShieldInitialization() throws Exception {
        // Use a latch to wait for the test to complete
        final Object lock = new Object();
        Platform.startup(() -> {
            ShieldImage shieldImage = new ShieldImage(100.0, 200.0);

            assertEquals(100.0, shieldImage.getLayoutX(), "Initial X position should match the constructor input");
            assertEquals(200.0, shieldImage.getLayoutY(), "Initial Y position should match the constructor input");
            assertFalse(shieldImage.isVisible(), "Shield should be invisible initially");
            assertNotNull(shieldImage.getImage(), "Shield should have an image set");
            assertTrue(shieldImage.getImage() instanceof Image, "Shield's image should be an instance of Image");

            synchronized (lock) {
                lock.notify(); // Notify the main thread that the test is done
            }
        });

        synchronized (lock) {
            lock.wait(); // Wait for the JavaFX thread to complete
        }
    }
}