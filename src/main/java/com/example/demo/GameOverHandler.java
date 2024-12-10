package com.example.demo;

public class GameOverHandler {
    
    public void handleGameOver(LevelParent level, boolean userDestroyed, boolean conditionToAdvance, String nextLevel, Runnable winAction) {
        if (userDestroyed) {
            level.loseGame();
        } else if (conditionToAdvance) {
            if (winAction != null) {
                winAction.run();
            } else {
                level.goToNextLevel(nextLevel);
            }
        }
    }
}
