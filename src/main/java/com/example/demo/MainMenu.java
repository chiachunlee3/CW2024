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

/**
 * The {@code MainMenu} class represents the main menu interface for the game.
 * It includes options to start the game or exit, with visual styling and
 * event handling for user interactions.
 */
public class MainMenu {

    private static final String MENU_BACKGROUND_IMAGE = "/com/example/demo/images/background1.jpg"; // Background image path
    private static final double BUTTON_WIDTH = 200; // Width of menu buttons
    private static final double BUTTON_HEIGHT = 50; // Height of menu buttons

    private final Scene scene; // Scene containing the main menu
    private final Group root; // Root group for adding visual elements
    private final double width; // Width of the main menu
    private final double height; // Height of the main menu
    private Runnable onStartGame; // Handler for the "Start Game" action

    /**
     * Constructs a {@code MainMenu} object.
     *
     * @param width  the width of the main menu.
     * @param height the height of the main menu.
     */
    public MainMenu(double width, double height) {
        this.width = width;
        this.height = height;
        this.root = new Group();
        this.scene = new Scene(root, width, height);

        createMenu();
    }

    /**
     * Creates the main menu layout, including background, title, and buttons.
     */
    private void createMenu() {
        // Initialize background
        initializeBackground();

        // Create a container for menu items
        StackPane menuContainer = new StackPane();
        menuContainer.setPrefSize(width, height);

        // Create a vertical box for organizing menu items
        VBox menuBox = new VBox(30);
        menuBox.setAlignment(Pos.CENTER);

        // Create and style the menu title
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

        // Add keyboard controls for quick actions
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.SPACE) {
                handleStartGame();
            }
            if (e.getCode() == KeyCode.ESCAPE) {
                System.exit(0);
            }
        });

        // Assemble the menu layout
        menuBox.getChildren().addAll(title, startButton, exitButton);
        menuContainer.getChildren().add(menuBox);
        root.getChildren().add(menuContainer);
    }

    /**
     * Initializes the background of the main menu. If the background image cannot
     * be loaded, a fallback color is used.
     */
    private void initializeBackground() {
        try {
            ImageView background = new ImageView(new Image(getClass().getResource(MENU_BACKGROUND_IMAGE).toExternalForm()));
            background.setFitWidth(width);
            background.setFitHeight(height);
            root.getChildren().add(background);
        } catch (Exception e) {
            // Fallback to a solid color background if the image fails to load
            scene.setFill(Color.DARKBLUE);
        }
    }

    /**
     * Creates a styled button for the main menu.
     *
     * @param text the text to display on the button.
     * @return a styled {@code Button} instance.
     */
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

    /**
     * Handles the "Start Game" action by running the provided handler, if set.
     */
    private void handleStartGame() {
        if (onStartGame != null) {
            onStartGame.run();
        }
    }

    /**
     * Retrieves the {@code Scene} object for the main menu.
     *
     * @return the {@code Scene} containing the main menu.
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Sets a handler for the "Start Game" action.
     *
     * @param handler a {@code Runnable} to be executed when the game starts.
     */
    public void setOnStartGame(Runnable handler) {
        this.onStartGame = handler;
    }
}