package com.testowanie.controller;

import com.testowanie.Main;
import com.testowanie.utils.DaneKoncaGry;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class KoniecGryController {
    @FXML
    Label ktoWygralLabel;

    public void initialize() {
        DaneKoncaGry dane = (DaneKoncaGry) Main.primaryStage.getUserData();
        if (dane.isGranoZKomputerem() && dane.getGraczZwyciezca() == 2) ktoWygralLabel.setText("Komputer");
        else if (!dane.isGranoZKomputerem() && dane.getGraczZwyciezca() == 2) ktoWygralLabel.setText("Gracz 2");
        else ktoWygralLabel.setText("Gracz 1");
    }

    @FXML
    private void doMenu() {
        Main.ustawScene(Main.oknoGlowne);
    }
}
