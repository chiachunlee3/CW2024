package com.example.demo.managers;

import javafx.scene.Group;
import javafx.scene.text.Text;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InstructionTextManagerTest {

    @Test
    public void testInstructionTextInitialization() {
        Group root = new Group();
        InstructionTextManager manager = new InstructionTextManager(root);

        Text instructionText = manager.getInstructionText();

        assertEquals("R: Restart | P: Pause", instructionText.getText());
        assertEquals(30, instructionText.getX());
        assertEquals(700, instructionText.getY());
        assertEquals("Monospaced", instructionText.getFont().getFamily());
    }
}
