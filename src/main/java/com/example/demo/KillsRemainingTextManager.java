package com.example.demo;

import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;

/**
 * Manages the display and updates of the "Kills Remaining" text in the game.
 */
public class KillsRemainingTextManager {
    private final Text killsRemainingText;

    /**
     * Constructs a new KillsRemainingTextManager.
     *
     * @param root the root group where the text will be displayed.
     */
    public KillsRemainingTextManager(Group root) {
        killsRemainingText = new Text();
        killsRemainingText.setFont(Font.font("Monospaced", FontWeight.BOLD, 20));
        killsRemainingText.setFill(Color.WHITE);
        killsRemainingText.setX(50);
        killsRemainingText.setY(50);
        root.getChildren().add(killsRemainingText);
        killsRemainingText.toFront();
    }

    /**
     * Updates the text to show the remaining kills required.
     *
     * @param killsRemaining the number of kills remaining.
     */
    public void updateKillsRemaining(int killsRemaining) {
        double padding = 20;
        double textWidthMargin = 300;
        double xPosition = 1300 - textWidthMargin - padding;
        double yPosition = padding + 30;

        killsRemainingText.setText("Kills Remaining: " + killsRemaining);
        killsRemainingText.setX(xPosition);
        killsRemainingText.setY(yPosition);

        killsRemainingText.setVisible(true);
        killsRemainingText.toFront();
    }

    /**
     * Returns the kills remaining text node.
     *
     * @return the text node displaying the kills remaining.
     */
    public Text getKillsRemainingText() {
        return killsRemainingText;
    }
    
    public void hideKillsRemainingText() {
        killsRemainingText.setVisible(false);
    }
}
