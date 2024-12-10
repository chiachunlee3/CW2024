package com.example.demo.levels;

import javafx.scene.Group;

/**
 * Represents the visual components and user interface specific to level three.
 * Extends {@link LevelViewLevelTwo} to add or modify functionalities for level three.
 */
public class LevelViewLevelThree extends LevelViewLevelTwo {

    /**
     * Constructs a new {@code LevelViewLevelThree} instance with the specified root group
     * and initial number of hearts to display.
     *
     * @param root           the root group for the level's visual elements.
     * @param heartsToDisplay the number of hearts to display initially.
     */
    public LevelViewLevelThree(Group root, int heartsToDisplay) {
        super(root, heartsToDisplay);
    }

}