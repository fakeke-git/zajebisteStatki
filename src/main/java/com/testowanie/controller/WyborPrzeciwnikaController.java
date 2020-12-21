package com.testowanie.controller;

import java.io.IOException;

import com.testowanie.Main;

import com.testowanie.utils.Ustawienia;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WyborPrzeciwnikaController {

	@FXML
	private void doGierkiZKomputerem(Event e) throws IOException {
		Node node = (Node) e.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		((Ustawienia) stage.getUserData()).setCzyGraZKomputerem(true);
		Scene gierka = new Scene(FXMLLoader.load(getClass().getResource("/ustawianie-statkow.fxml")), 900, 400);
		stage.setScene(gierka);
	}

	public void doGierki(Event event) throws IOException{
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		((Ustawienia) stage.getUserData()).setCzyGraZKomputerem(false);
		Scene gierka = new Scene(FXMLLoader.load(getClass().getResource("/ustawianie-statkow.fxml")), 900, 400);
		stage.setScene(gierka);
	}

	public void doGlownego() {
		Main.ustawScene(Main.oknoGlowne);
	}
}
