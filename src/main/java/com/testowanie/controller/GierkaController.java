package com.testowanie.controller;

import com.testowanie.Main;
import com.testowanie.utils.*;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class GierkaController {
    private Ustawienia ustawienia;
    private int gracz = 1;

    @FXML
    private GridPane plansza1;
    @FXML
    private GridPane plansza2;
    @FXML
    private VBox planszaPrzeciwnikaKontener;
    @FXML
    private VBox planszaGraczaKontener;
    @FXML
    private Label czyZniszczonoStatekLabel;
    @FXML
    private Label turaGraczaLabel;
    @FXML
    private GridPane przejscieMiedzyTurami;

    public void initialize() {
    	ustawienia = (Ustawienia) Main.primaryStage.getUserData();
        dodajP(plansza1, ustawienia.getRozmiarPlansz());
        dodajP(plansza2, ustawienia.getRozmiarPlansz());
        dodajButtonyNaPlanszyPrzeciwnika(ustawienia.getRozmiarPlansz());
        dodajButtonyNaPlanszyGracza(ustawienia.getRozmiarPlansz());
        wyswietlPrzejscieMiedzyTurami(null);
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

    public void dodajButtonyNaPlanszyPrzeciwnika(int rozmiar) {
        Button buttons[][] =  gracz == 1 ? ustawienia.getPlanszaGracza2() : ustawienia.getPlanszaGracza1();
        for (int i = 0; i < rozmiar; i++) {
            for (int j = 0; j < rozmiar; j++) {
                double width = plansza1.getPrefWidth();
                double height = plansza1.getPrefHeight();
                double pv = width / rozmiar ;
                double ph = height / rozmiar;
                Button b = new Button();
                b.setPrefWidth(pv);
                b.setPrefHeight(ph);
                b.setUserData(new Punkt(i, j));
                ustawAkcje(b);
                if (((ButtonProperties)buttons[i][j].getUserData()).isStrzelony()) b.setText("X");
                plansza1.add(b, j, i);

            }
        }
    }

    public void ustawAkcje(Button b) {
        b.setOnAction((e) -> {
            Punkt punkt = (Punkt) b.getUserData();
            Button[][] buttonyPrzeciwnika = gracz == 1 ? ustawienia.getPlanszaGracza2() : ustawienia.getPlanszaGracza1();
            Button button = buttonyPrzeciwnika[punkt.getX()][punkt.getY()];
            if (((ButtonProperties) button.getUserData()).isStrzelony()) return;
            ((ButtonProperties) button.getUserData()).setStrzelony(true);
            if (czyGraSkonczona()) {
                Node node = (Node) e.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                Scene ekranKoncowy = null;
                stage.setUserData(new DaneKoncaGry(ustawienia.isCzyGraZKomputerem(), gracz));
                try {
                    ekranKoncowy = new Scene(FXMLLoader.load(getClass().getResource("/koniec-gry.fxml")), 900, 400);
                } catch (IOException ex) {
                    Main.ustawScene(Main.oknoGlowne);
                }
                stage.setScene(ekranKoncowy);
                return;
            }
            button.setText("X");
            gracz = gracz == 1 ? 2 : 1;

            plansza1.getChildren().clear();
            plansza2.getChildren().clear();
            dodajButtonyNaPlanszyPrzeciwnika(ustawienia.getRozmiarPlansz());
            dodajButtonyNaPlanszyGracza(ustawienia.getRozmiarPlansz());

            wyswietlPrzejscieMiedzyTurami(punkt);
        });
    }

    private boolean czyGraSkonczona() {
        Button[][] buttonyPrzeciwnika = gracz == 1 ? ustawienia.getPlanszaGracza2() : ustawienia.getPlanszaGracza1();
        for (var buttons : buttonyPrzeciwnika) {
            for (var b : buttons) {
                ButtonProperties ustawienia = (ButtonProperties) b.getUserData();
                if (ustawienia.isCzyMaStatek() && !ustawienia.isStrzelony()) return false;
            }
        }
        return true;
    }


    public void dodajButtonyNaPlanszyGracza(int rozmiar) {
        for (int i = 0; i < rozmiar; i++) {
            for (int j = 0; j < rozmiar; j++) {
                Button b = gracz == 1 ? ustawienia.getPlanszaGracza1()[i][j] : ustawienia.getPlanszaGracza2()[i][j];
                b.setDisable(true);
                plansza2.add(b, j, i);
            }
        }
    }

    private void wyswietlPrzejscieMiedzyTurami(Punkt punktOstatniegoStrzalu) {
        Button[][] plansza =  gracz == 1 ? ustawienia.getPlanszaGracza1() : ustawienia.getPlanszaGracza2();
        boolean zniszczonoStatek = punktOstatniegoStrzalu != null ? Stateczek.czyZatonal(plansza, punktOstatniegoStrzalu, "") : false;
        planszaGraczaKontener.setMaxWidth(0.0);
        planszaPrzeciwnikaKontener.setMaxWidth(0.0);
        planszaGraczaKontener.setVisible(false);
        planszaPrzeciwnikaKontener.setVisible(false);
        przejscieMiedzyTurami.setVisible(true);
        if (zniszczonoStatek) czyZniszczonoStatekLabel.setText("Zniszczono statek!");
        else czyZniszczonoStatekLabel.setText("");
        przejscieMiedzyTurami.setMaxWidth(przejscieMiedzyTurami.getPrefWidth());
        turaGraczaLabel.setText(gracz == 1 ? "Grzacz 1" : "Gracz 2");
    }

    @FXML
    private void ukryjPrzejscieMiedzyTurami() {
        planszaGraczaKontener.setMaxWidth(planszaGraczaKontener.getPrefWidth());
        planszaPrzeciwnikaKontener.setMaxWidth(planszaPrzeciwnikaKontener.getPrefWidth());
        planszaGraczaKontener.setVisible(true);
        planszaPrzeciwnikaKontener.setVisible(true);
        przejscieMiedzyTurami.setMaxWidth(0.0);
        przejscieMiedzyTurami.setVisible(false);
    }
}
