package com.testowanie.utils;

import javafx.fxml.FXML;
import javafx.scene.control.Spinner;

public class Ustawienia {

	private int rozmiarPlansz;

	private int iloscStatkowCztero;

	private int iloscStatkowTrzy;

	private int iloscStatkowDwa;

	private int iloscStatkowJeden;

	public Ustawienia(int rozmiarPlansz, int iloscStatkowCztero, int iloscStatkowTrzy, int iloscStatkowDwa,
			int iloscStatkowJeden) {
		this.rozmiarPlansz = rozmiarPlansz;
		this.iloscStatkowCztero = iloscStatkowCztero;
		this.iloscStatkowTrzy = iloscStatkowTrzy;
		this.iloscStatkowDwa = iloscStatkowDwa;
		this.iloscStatkowJeden = iloscStatkowJeden;
	}

	public int getRozmiarPlansz() {
		return rozmiarPlansz;
	}

	public void setRozmiarPlansz(int rozmiarPlansz) {
		this.rozmiarPlansz = rozmiarPlansz;
	}

	public int getIloscStatkowCztero() {
		return iloscStatkowCztero;
	}

	public void setIloscStatkowCztero(int iloscStatkowCztero) {
		this.iloscStatkowCztero = iloscStatkowCztero;
	}

	public int getIloscStatkowTrzy() {
		return iloscStatkowTrzy;
	}

	public void setIloscStatkowTrzy(int iloscStatkowTrzy) {
		this.iloscStatkowTrzy = iloscStatkowTrzy;
	}

	public int getIloscStatkowDwa() {
		return iloscStatkowDwa;
	}

	public void setIloscStatkowDwa(int iloscStatkowDwa) {
		this.iloscStatkowDwa = iloscStatkowDwa;
	}

	public int getIloscStatkowJeden() {
		return iloscStatkowJeden;
	}

	public void setIloscStatkowJeden(int iloscStatkowJeden) {
		this.iloscStatkowJeden = iloscStatkowJeden;
	}

	@Override
	public String toString() {
		return "Ustawienia [rozmiarPlansz=" + rozmiarPlansz + ", iloscStatkowCztero=" + iloscStatkowCztero
				+ ", iloscStatkowTrzy=" + iloscStatkowTrzy + ", iloscStatkowDwa=" + iloscStatkowDwa
				+ ", iloscStatkowJeden=" + iloscStatkowJeden + "]";
	}

}
