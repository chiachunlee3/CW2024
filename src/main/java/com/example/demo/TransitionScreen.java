package com.example.demo;

import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class TransitionScreen {
    private final Scene scene;

    public TransitionScreen(double width, double height, String levelTextContent, Runnable onFinish) {
        /// Create a root layout for the transition screen
    	StackPane root = new StackPane();

    	// Set the background of the root to black
    	root.setStyle("-fx-background-color: black;");

        // Set scene size and ensure the background is black
        scene = new Scene(root, width, height, Color.WHITE);

        // Create a full-screen black rectangle
        Rectangle background = new Rectangle(width + 700, height + 400);
        background.setFill(Color.WHITE);

        // Create and style the level text
        Text levelText = new Text(levelTextContent);
        levelText.setFont(Font.font("Monospaced", FontWeight.BOLD, 80));
        levelText.setFill(Color.BLACK);

        levelText.setTranslateX(-300); // Center horizontally
        levelText.setTranslateY(-200); // Center vertically

        // Add the black background and level text to the root layout
        root.getChildren().addAll(background, levelText);

        // Create fade-in and fade-out transitions
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), levelText);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), levelText);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);

     // Create fade-in and fade-out transitions for the text
        FadeTransition fadeInText = new FadeTransition(Duration.seconds(1), levelText);
        fadeInText.setFromValue(0);
        fadeInText.setToValue(1);

        FadeTransition fadeOutText = new FadeTransition(Duration.seconds(1), levelText);
        fadeOutText.setFromValue(1);
        fadeOutText.setToValue(0);

        // Create fade-out transition for the background
        FadeTransition fadeOutBackground = new FadeTransition(Duration.seconds(0.5), background);
        fadeOutBackground.setFromValue(1);
        fadeOutBackground.setToValue(0);
        
     // Create fade-out transition for the root node
        FadeTransition fadeOutRoot = new FadeTransition(Duration.seconds(0.5), root);
        fadeOutRoot.setFromValue(1);
        fadeOutRoot.setToValue(0);

        // Add a delay before switching scenes to allow a smoother fade-out
        fadeOutRoot.setOnFinished(e -> {
            try {
                // Adding a small delay to let the fade-out complete fully
                Thread.sleep(100); // Delay in milliseconds
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            onFinish.run();
        });

        // Chain the transitions: fade-in -> fade-out text -> fade-out root -> load next scene
        fadeInText.setOnFinished(e -> fadeOutText.play());
        fadeOutText.setOnFinished(e -> fadeOutRoot.play());

        fadeInText.play();
    }

    public Scene getScene() {
        return scene;
    }
}
