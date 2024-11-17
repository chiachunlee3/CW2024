package com.example.demo.controller;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import com.example.demo.LevelParent;
import com.example.demo.MainMenu;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Controller {
    private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.LevelOne";
    private final Stage stage;
    private MainMenu mainMenu;

    public Controller(Stage stage) {
        this.stage = stage;
    }

    public void launchGame() {
        showMainMenu();
        stage.show();
    }

    private void showMainMenu() {
        if (mainMenu == null) {
            mainMenu = new MainMenu(stage.getWidth(), stage.getHeight());
            mainMenu.setOnStartGame(() -> {
                try {
                    goToLevel(LEVEL_ONE_CLASS_NAME);
                } catch (Exception e) {
                    showError(e);
                }
            });
        }
        stage.setScene(mainMenu.getScene());
    }

    private void goToLevel(String className) throws ClassNotFoundException, NoSuchMethodException,
            SecurityException, InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        Class<?> myClass = Class.forName(className);
        Constructor<?> constructor = myClass.getConstructor(double.class, double.class);
        LevelParent myLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth());
        myLevel.setOnLevelChange(this::handleLevelChange);
        Scene scene = myLevel.initializeScene();
        stage.setScene(scene);
        myLevel.startGame();
    }

    private void handleLevelChange(String nextLevelClassName) {
        try {
            goToLevel(nextLevelClassName);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
                | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            showError(e);
        }
    }

    private void showError(Exception e) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("An error occurred");
        alert.setContentText(e.getMessage());
        alert.show();
    }
}