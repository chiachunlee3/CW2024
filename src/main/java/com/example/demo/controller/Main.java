package com.example.demo.controller;

import java.lang.reflect.InvocationTargetException;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The {@code Main} class serves as the entry point for the Sky Battle game.
 * It initializes the application window, sets up the game controller, and starts the game.
 */
public class Main extends Application {

    /**
     * The width of the application window in pixels.
     */
    private static final int SCREEN_WIDTH = 1300;

    /**
     * The height of the application window in pixels.
     */
    private static final int SCREEN_HEIGHT = 750;

    /**
     * The title of the application window.
     */
    private static final String TITLE = "Sky Battle";

    /**
     * The game controller responsible for managing the game flow.
     */
    private Controller myController;

    /**
     * The main entry point for the JavaFX application.
     * 
     * @param stage the primary stage for the application.
     * @throws ClassNotFoundException    if a required class cannot be found.
     * @throws NoSuchMethodException     if a required method cannot be found.
     * @throws SecurityException         if a security violation occurs.
     * @throws InstantiationException    if an object cannot be instantiated.
     * @throws IllegalAccessException    if access to a method or field is denied.
     * @throws IllegalArgumentException  if an invalid argument is passed.
     * @throws InvocationTargetException if a reflection method invocation fails.
     */
    @Override
    public void start(Stage stage) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        stage.setTitle(TITLE);
        stage.setResizable(false);
        stage.setHeight(SCREEN_HEIGHT);
        stage.setWidth(SCREEN_WIDTH);
        myController = new Controller(stage);
        myController.launchGame();
    }

    /**
     * The main method to launch the JavaFX application.
     * 
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        launch();
    }
}