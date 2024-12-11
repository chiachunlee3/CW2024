package com.example.demo.levels;

import static org.junit.jupiter.api.Assertions.*;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.text.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

// Mock GameLoopManager for testing
class MockGameLoopManager extends com.example.demo.managers.GameLoopManager {
    public MockGameLoopManager() {
        super(0, null); // Use default constructor with dummy values
    }

    @Override
    public void stop() {
        // Mock implementation of stop, no real functionality
    }
}

public class LevelTransitionManagerTest {

    private LevelTransitionManager levelTransitionManager;
    private Group root;

    @BeforeEach
    public void setUp() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);

        // Initialize JavaFX toolkit
        Platform.startup(() -> latch.countDown());
        latch.await();

        root = new Group();
        MockGameLoopManager mockGameLoopManager = new MockGameLoopManager();
        levelTransitionManager = new LevelTransitionManager(root, 800, 600, mockGameLoopManager);
    }

    @Test
    public void testGoToNextLevel_DisplaysMessage() throws Exception {
        // Run test on JavaFX application thread
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                String nextLevelName = "LevelTwo";
                levelTransitionManager.setOnLevelChange(levelName -> {});
                levelTransitionManager.goToNextLevel(nextLevelName);

                // Verify the "Level Cleared" message is displayed
                assertFalse(root.getChildren().isEmpty(), "The root group should contain elements.");
                Text levelClearedText = (Text) root.getChildren().get(0);
                assertEquals("Level Cleared!", levelClearedText.getText());
            } finally {
                latch.countDown();
            }
        });

        latch.await();
    }
}