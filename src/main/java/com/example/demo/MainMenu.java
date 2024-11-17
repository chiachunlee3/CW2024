package com.example.demo;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;

public class MainMenu {
    private static final String MENU_BACKGROUND_IMAGE = "/com/example/demo/images/background1.jpg";
    private static final double BUTTON_WIDTH = 200;
    private static final double BUTTON_HEIGHT = 50;
    
    private final Scene scene;
    private final Group root;
    private final double width;
    private final double height;
    private Runnable onStartGame;

    public MainMenu(double width, double height) {
        this.width = width;
        this.height = height;
        this.root = new Group();
        this.scene = new Scene(root, width, height);
        
        createMenu();
    }

    private void createMenu() {
        // Initialize background
        initializeBackground();
        
        // Create menu container
        StackPane menuContainer = new StackPane();
        menuContainer.setPrefSize(width, height);
        
        // Create vertical box for menu items
        VBox menuBox = new VBox(30);
        menuBox.setAlignment(Pos.CENTER);

        // Create and style title
        Text title = new Text("Sky Battle");
        title.setFont(Font.font("Monospaced", FontWeight.BOLD, 80));
        title.setFill(Color.WHITE);
        title.setEffect(new DropShadow(10, Color.BLACK));

        // Create and style buttons
        Button startButton = createStyledButton("Start Game");
        Button exitButton = createStyledButton("Exit");

        // Add button actions
        startButton.setOnAction(e -> handleStartGame());
        exitButton.setOnAction(e -> System.exit(0));

        // Add keyboard controls
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.SPACE) {
                handleStartGame();
            }
            if (e.getCode() == KeyCode.ESCAPE) {
                System.exit(0);
            }
        });

        // Assemble menu
        menuBox.getChildren().addAll(title, startButton, exitButton);
        menuContainer.getChildren().add(menuBox);
        root.getChildren().add(menuContainer);
    }

    private void initializeBackground() {
        try {
            ImageView background = new ImageView(new Image(getClass().getResource(MENU_BACKGROUND_IMAGE).toExternalForm()));
            background.setFitWidth(width);
            background.setFitHeight(height);
            root.getChildren().add(background);
        } catch (Exception e) {
            // Fallback to solid color if image cannot be loaded
            scene.setFill(Color.DARKBLUE);
        }
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(BUTTON_WIDTH);
        button.setPrefHeight(BUTTON_HEIGHT);
        button.setFont(Font.font("Arial", 24));
        button.setStyle("-fx-background-color: #4a90e2; " +
                       "-fx-text-fill: white; " +
                       "-fx-background-radius: 5; " +
                       "-fx-cursor: hand;");

        // Add hover effect
        button.setOnMouseEntered(e -> 
            button.setStyle(button.getStyle() + "-fx-background-color: #357abd;"));
        button.setOnMouseExited(e -> 
            button.setStyle(button.getStyle() + "-fx-background-color: #4a90e2;"));

        return button;
    }

    private void handleStartGame() {
        if (onStartGame != null) {
            onStartGame.run();
        }
    }

    public Scene getScene() {
        return scene;
    }

    public void setOnStartGame(Runnable handler) {
        this.onStartGame = handler;
    }
}