package com.testowanie.controller;

import com.testowanie.Main;
import com.testowanie.utils.Ustawienia;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class GierkaController {
    private Ustawienia ustawienia;

    @FXML
    private GridPane plansza1;
    @FXML
    private GridPane plansza2;

    public void initialize() {
    	ustawienia = (Ustawienia) Main.primaryStage.getUserData();
        dodajP(plansza1, ustawienia.getRozmiarPlansz());
        dodajP(plansza2, ustawienia.getRozmiarPlansz());
        dodajButtonyNaPlanszyPrzeciwnika(plansza1, ustawienia.getRozmiarPlansz());
        dodajButtonyNaPlanszyGracza(plansza2, ustawienia.getRozmiarPlansz());
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

    public void dodajButtonyNaPlanszyPrzeciwnika(GridPane p, int rozmiar) {
        Button buttons[][] = ustawienia.getPlanszaGracza2();
        for (int i = 0; i < rozmiar; i++) {
            for (int j = 0; j < rozmiar; j++) {
                double width = p.getPrefWidth();
                double height = p.getPrefHeight();
                double pv = width / rozmiar ;
                double ph = height / rozmiar;
                Button b = new Button();
                b.setPrefWidth(pv);
                b.setPrefHeight(ph);
//                if (()buttons[i][j].getUserData())
                p.add(b, j, i);

            }
        }
    }

    public void dodajButtonyNaPlanszyGracza(GridPane p, int rozmiar) {
        for (int i = 0; i < rozmiar; i++) {
            for (int j = 0; j < rozmiar; j++) {
                Button b = ustawienia.getPlanszaGracza1()[i][j];
                b.setDisable(true);
                p.add(b, j, i);
            }
        }
    }
}
