package com.testowanie.controller;

import java.io.IOException;

import com.testowanie.Main;
import com.testowanie.utils.Ustawienia;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.stage.Stage;

public class UstawieniaController {

	Ustawienia ustawienia = null;

	@FXML
	Spinner<Integer> rozmiarPlansz;

	@FXML
	Spinner<Integer> iloscStatkowCztero;

	@FXML
	Spinner<Integer> iloscStatkowTrzy;

	@FXML
	Spinner<Integer> iloscStatkowDwa;

	@FXML
	Spinner<Integer> iloscStatkowJeden;

	public void initialize() {
		rozmiarPlansz.getValueFactory().setValue(10);
		iloscStatkowCztero.getValueFactory().setValue(1);
		iloscStatkowTrzy.getValueFactory().setValue(2);
		iloscStatkowDwa.getValueFactory().setValue(3);
		iloscStatkowJeden.getValueFactory().setValue(4);
	}

	public void doWyborPrzeciwnika(Event event) throws IOException{

//    	System.out.println(event);
//    	System.out.println(rozmiarPlansz.getValue());
//    	System.out.println(iloscStatkowCztero.getValue());
//    	System.out.println(iloscStatkowTrzy.getValue());
//    	System.out.println(iloscStatkowDwa.getValue());
//    	System.out.println(iloscStatkowJeden.getValue());
		if(iloscStatkowCztero.getValue() == 0 && iloscStatkowTrzy.getValue() == 0 && iloscStatkowDwa.getValue() == 0 && iloscStatkowJeden.getValue() == 0) 
			return;
		
		
		ustawienia = new Ustawienia(rozmiarPlansz.getValue(), iloscStatkowCztero.getValue(),
				iloscStatkowTrzy.getValue(), iloscStatkowDwa.getValue(), iloscStatkowJeden.getValue());

		Node node = (Node) event.getSource();

		
		Scene wyborPrzeciwnika = new Scene(FXMLLoader.load(getClass().getResource("/wyborprzeciwnika.fxml")), 900, 400);

		Stage stage = (Stage) node.getScene().getWindow();

		stage.setUserData(ustawienia);

		stage.setScene(wyborPrzeciwnika);

	}

	public void doGlownego() {
		Main.ustawScene(Main.oknoGlowne);
	}

}
