package com.example.demo.states;

import com.example.demo.levels.LevelParent;

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
