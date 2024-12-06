package com.example.demo;

import javafx.scene.Group;

public class LevelViewLevelThree extends LevelViewLevelTwo {

    public LevelViewLevelThree(Group root, int heartsToDisplay) {
        super(root, heartsToDisplay);
    }

    @Override
    public void updateKillsRemaining(int killsRemaining) {
        super.updateKillsRemaining(killsRemaining);
        killsRemainingText.setText("Kills Remaining for Enemies: " + killsRemaining);
        killsRemainingText.setVisible(false);
        killsRemainingText.toFront();
    }
}
