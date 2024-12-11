package com.example.demo.actors;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class BossHealthManagerTest {

    private BossHealthManager bossHealthManager;
    private Group root;

    @BeforeAll
    public static void initJavaFX() {
        // Initialize JavaFX toolkit
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        try {
            latch.await();
        } catch (InterruptedException e) {
            fail("Failed to initialize JavaFX");
        }
    }

    @AfterAll
    public static void teardownJavaFX() {
        Platform.exit();
    }

    @BeforeEach
    public void setUp() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            root = new Group();
            Scene scene = new Scene(root, 1200, 800, Color.BLACK);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            bossHealthManager = new BossHealthManager(root);
            latch.countDown();
        });
        latch.await();
    }

    @Test
    public void testUpdateBossHealth() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            bossHealthManager.updateBossHealth(50, 100);
            latch.countDown();
        });
        latch.await();

        // Fetch health bar and text
        Rectangle healthBar = (Rectangle) root.getChildren().get(1);
        Text healthText = (Text) root.getChildren().get(2);

        // Assert health bar width
        assertEquals(150.0, healthBar.getWidth(), 0.01, "Health bar width mismatch");

        // Assert health text content
        assertEquals("Boss Health: 50/100", healthText.getText(), "Health text mismatch");
    }

    @Test
    public void testHealthBarVisibility() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(latch::countDown);
        latch.await();

        // Fetch health bar and text
        Rectangle healthBarBackground = (Rectangle) root.getChildren().get(0);
        Rectangle healthBar = (Rectangle) root.getChildren().get(1);
        Text healthText = (Text) root.getChildren().get(2);

        // Assert visibility
        assertTrue(healthBarBackground.isVisible(), "Health bar background should be visible");
        assertTrue(healthBar.isVisible(), "Health bar should be visible");
        assertTrue(healthText.isVisible(), "Health text should be visible");
    }
}