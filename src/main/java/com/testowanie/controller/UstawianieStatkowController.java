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
import javafx.scene.layout.HBox;
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
    private HBox czteroHbox;
    
    @FXML
    private GridPane plansza1;
    
    private Button tablicaPrzyciskow[][];
    private boolean czyUstawianie;
    private int iloMasztowy;

    public void initialize() {
        ustawienia = (Ustawienia) Main.primaryStage.getUserData();

        tablicaPrzyciskow = new Button[ustawienia.getRozmiarPlansz()][ustawienia.getRozmiarPlansz()];
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
                tablicaPrzyciskow[i][j] = b;
                b.setStyle("-fx-background-color: aqua");
                b.setPrefWidth(pv);
                b.setPrefHeight(ph);
                p.add(b, j, i);

                b.setUserData(new ButtonProperties(i, j, false));

//                b.setOnAction((e) -> {
//                    b.setUserData(!((Boolean) b.getUserData()));
//                    if ((Boolean) b.getUserData()) b.setStyle("-fx-background-color: coral");
//                    else b.setStyle("-fx-background-color: aqua");
//                });
                
                b.setOnMouseEntered(e -> {
                	if(czyUstawianie) {
                		ButtonProperties buttonProperties = (ButtonProperties)((Button)e.getSource()).getUserData();
                		int x = buttonProperties.getX();
                		int y = buttonProperties.getY();

                		if(!(iloMasztowy + y > ustawienia.getRozmiarPlansz())) { 
                		for(int k = iloMasztowy; k > 0; k--) {
                			
                			tablicaPrzyciskow[x][y].setStyle("-fx-background-color: coral");
                			y++;
                			
                		}
                		
                	}}
                });
                
                b.setOnMouseExited(e -> {
                	if(czyUstawianie) {
                		ButtonProperties buttonProperties = (ButtonProperties)((Button)e.getSource()).getUserData();
                		int x = buttonProperties.getX();
                		int y = buttonProperties.getY();
                		for(int k = iloMasztowy; k > 0; k--) {
                			if(!(k + y > ustawienia.getRozmiarPlansz())) {
                			tablicaPrzyciskow[x][y].setStyle("-fx-background-color: aqua");
                			y++;
                			}
                		}
                		
                	}
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
    
    public void ustawStatek(Event e) {
    	czyUstawianie = true;
    	iloMasztowy = ((HBox) e.getSource()).getChildren().size();
    	
    }
    
    private class ButtonProperties {
    	private int x;
    	private int y;
    	private boolean zajety;
    	
		public ButtonProperties(int x, int y, boolean zajety) {
			this.x = x;
			this.y = y;
			this.zajety = zajety;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public boolean isZajety() {
			return zajety;
		}

		public void setZajety(boolean zajety) {
			this.zajety = zajety;
		}
    	
    
    }
    

}
