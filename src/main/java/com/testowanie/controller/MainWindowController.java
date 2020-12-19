package com.testowanie.controller;

import java.io.IOException;

import com.testowanie.Main;

import com.testowanie.utils.Ustawienia;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainWindowController {
    Ustawienia ustawienia = null;

    public void doTrybuKlasycznego(Event event) throws IOException {
        ustawienia = new Ustawienia(10, 1,
                2, 3, 4);
        

        Scene gierka = new Scene(FXMLLoader.load(getClass().getResource("/gierka.fxml")), 900, 400);
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        stage.setUserData(ustawienia);

        stage.setScene(gierka);
    }

    public void doTrybuCustomowego(Event event) throws IOException {
        Scene ustawienia = new Scene(FXMLLoader.load(getClass().getResource("/ustawienia.fxml")), 900, 400);
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(ustawienia);
    }

    public void wyjdz() {
        Main.primaryStage.close();
    }
}
