package com.example.demo;

import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class PauseTextManager {

    private final Text pauseText;

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

    public void updatePauseTextPosition() {
        pauseText.setX(360);
        pauseText.setY(350);
    }

    public void showPauseText() {
        updatePauseTextPosition();
        pauseText.setVisible(true);
        pauseText.toFront();
    }

    public void hidePauseText() {
        pauseText.setVisible(false);
    }

    public Text getPauseText() {
        return pauseText;
    }
}
