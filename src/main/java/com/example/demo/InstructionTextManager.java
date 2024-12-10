package com.example.demo;

import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Manages the instruction text display for the game level.
 */
public class InstructionTextManager {

    private final Text instructionText;

    /**
     * Constructs an InstructionTextManager.
     *
     * @param root the root group to add the instruction text to.
     */
    public InstructionTextManager(Group root) {
        instructionText = new Text("R: Restart | P: Pause");
        instructionText.setFont(Font.font("Monospaced", FontWeight.BOLD, 18));
        instructionText.setFill(Color.BLACK);
        instructionText.setX(30);
        instructionText.setY(700);

    }

    /**
     * Returns the instruction text node.
     *
     * @return the instruction text node.
     */
    public Text getInstructionText() {
        return instructionText;
    }
}
