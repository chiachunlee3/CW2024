package com.example.demo.states;

import com.example.demo.levels.LevelParent;

/**
 * Handles the game over scenarios by determining the next steps based on the game state.
 */
public class GameOverHandler {
    
    /**
     * Processes the game over state and performs the appropriate actions such as losing the game, 
     * advancing to the next level, or executing a custom win action.
     *
     * @param level the current level instance where the game is being played
     * @param userDestroyed a flag indicating if the user has lost the game
     * @param conditionToAdvance a flag indicating if the conditions to advance to the next level are met
     * @param nextLevel the identifier for the next level to proceed to
     * @param winAction a Runnable containing the actions to perform on winning the level
     */
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