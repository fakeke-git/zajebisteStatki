package com.testowanie.controller;

import com.testowanie.Main;
import com.testowanie.utils.Ustawienia;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.io.IOException;

public class UstawianieStatkowController {
    private Ustawienia ustawienia;

    @FXML
    private Label liczbaCztero;

    @FXML
    private Label liczbaTrzy;

    @FXML
    private Label liczbaDwa;

    @FXML
    private Label liczbaJeden;

    @FXML
    private GridPane plansza1;

    public void initialize() {
        ustawienia = (Ustawienia) Main.primaryStage.getUserData();

        dodajP(plansza1, ustawienia.getRozmiarPlansz());
        asd(plansza1, ustawienia.getRozmiarPlansz());

        liczbaCztero.setText(Integer.toString(ustawienia.getIloscStatkowCztero()));
        liczbaTrzy.setText(Integer.toString(ustawienia.getIloscStatkowTrzy()));
        liczbaDwa.setText(Integer.toString(ustawienia.getIloscStatkowDwa()));
        liczbaJeden.setText(Integer.toString(ustawienia.getIloscStatkowJeden()));
    }

    public void dodajP(GridPane p, int rozmiar) {
        for (int i = 0; i < rozmiar; i++) {
            ColumnConstraints col = new ColumnConstraints();
            RowConstraints row = new RowConstraints();
            col.setPercentWidth(100 / rozmiar);
            row.setPercentHeight(100 / rozmiar);
            p.getColumnConstraints().add(col);
            p.getRowConstraints().add(row);
        }
    }

    public void asd(GridPane p, int rozmiar) {
        for (int i = 0; i < rozmiar; i++) {
            for (int j = 0; j < rozmiar; j++) {
                double width = p.getPrefWidth();
                double height = p.getPrefHeight();
                double pv = width / rozmiar ;
                double ph = height / rozmiar;
                Button b = new Button();
                b.setStyle("-fx-background-color: aqua");
                b.setPrefWidth(pv);
                b.setPrefHeight(ph);
                p.add(b, j, i);

                b.setUserData(false);

                b.setOnAction((e) -> {
                    b.setUserData(!((Boolean) b.getUserData()));
                    if ((Boolean) b.getUserData()) b.setStyle("-fx-background-color: coral");
                    else b.setStyle("-fx-background-color: aqua");
                });
            }
        }
    }

    public void cofnij(Event e) throws IOException {
        Scene wyborPrzeciwnika = new Scene(FXMLLoader.load(getClass().getResource("/wyborprzeciwnika.fxml")), 900, 400);
        Node node = (Node) e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        stage.setScene(wyborPrzeciwnika);
    }

    public void dalej(Event e) throws IOException {
        Scene gierka = new Scene(FXMLLoader.load(getClass().getResource("/gierka.fxml")), 900, 400);
        Node node = (Node) e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        stage.setScene(gierka);
    }
}
