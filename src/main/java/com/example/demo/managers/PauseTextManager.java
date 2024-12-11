package com.example.demo.managers;

import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

/**
 * Manages the display of the "Game Paused" text in the game.
 * Provides methods to show, hide, and update the position of the pause text.
 */
public class PauseTextManager {

    private final Text pauseText;

    /**
     * Constructor for PauseTextManager.
     * Initializes the "Game Paused" text and adds it to the specified root group.
     * 
     * @param root the Group to which the pause text will be added.
     */
    public PauseTextManager(Group root) {
        // Initialize pauseText
        this.pauseText = new Text("Game Paused!");
        pauseText.setFont(Font.font("Monospaced", FontWeight.BOLD, 80));
        pauseText.setFill(Color.WHITE);
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.BLACK);
        shadow.setRadius(10);
        shadow.setOffsetX(3);
        shadow.setOffsetY(3);
        pauseText.setEffect(shadow);
        updatePauseTextPosition();
        pauseText.setVisible(false);

        // Add to root
        root.getChildren().add(pauseText);
    }

    /**
     * Updates the position of the pause text on the screen.
     */
    public void updatePauseTextPosition() {
        pauseText.setX(360);
        pauseText.setY(350);
    }

    /**
     * Makes the pause text visible on the screen.
     * Updates its position and brings it to the front.
     */
    public void showPauseText() {
        updatePauseTextPosition();
        pauseText.setVisible(true);
        pauseText.toFront();
    }

    /**
     * Hides the pause text from the screen.
     */
    public void hidePauseText() {
        pauseText.setVisible(false);
    }

    /**
     * Gets the Text object representing the pause text.
     * 
     * @return the Text object for the pause text.
     */
    public Text getPauseText() {
        return pauseText;
    }
}