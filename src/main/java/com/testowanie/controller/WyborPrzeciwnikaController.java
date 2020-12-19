package com.testowanie.controller;

import java.io.IOException;

import com.testowanie.Main;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WyborPrzeciwnikaController {

	public void doGierki(Event event) throws IOException{
		Scene gierka = new Scene(FXMLLoader.load(getClass().getResource("/gierka.fxml")), 900, 400);
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.setScene(gierka);
	}

	public void doGlownego() {

		Main.ustawScene(Main.oknoGlowne);
	}
}
