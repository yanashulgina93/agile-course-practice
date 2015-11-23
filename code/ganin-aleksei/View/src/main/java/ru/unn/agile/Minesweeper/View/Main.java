package ru.unn.agile.Minesweeper.View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static View view = new View();

    @Override
    public void start(final Stage primaryStage) throws Exception {
        view.start(primaryStage);
    }

    public static void main(final String[] args) {
        launch(args);
    }
}