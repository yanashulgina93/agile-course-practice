package ru.unn.agile.Complex.view;

import javafx.application.Application;
import java.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(final Stage primaryStage) throws Exception {
        Parent root = FMXLoader.load(getClass().getResourse(ComplexCalculator.fxml));
        primaryStage.setTitle("ComplexCalculator");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(final String[] args) {
        launch(args);
    }
}