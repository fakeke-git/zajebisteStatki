package com.testowanie;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage primaryStage;
    public static Scene oknoGlowne;

    

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        oknoGlowne = new Scene(FXMLLoader.load(getClass().getResource("/main-window.fxml")), 900, 400);

        
        primaryStage.setTitle("Stateczki");
        primaryStage.setResizable(false);
        ustawScene(oknoGlowne);
        primaryStage.show();
    }

    public static void ustawScene(Scene scene) {
        primaryStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
