package com.testowanie.utils;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;

public class Ustawienia {

	private int rozmiarPlansz;

	private int iloscStatkowCztero;

	private int iloscStatkowTrzy;

	private int iloscStatkowDwa;

	private int iloscStatkowJeden;

	private Button[][] planszaGracza1 = null;

	private Button[][] planszaGracza2 = null;

	private boolean czyGraZKomputerem = false;

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

	public Button[][] getPlanszaGracza1() {
		return planszaGracza1;
	}

	public void setPlanszaGracza1(Button[][] planszaGracza1) {
		this.planszaGracza1 = planszaGracza1;
	}

	public Button[][] getPlanszaGracza2() {
		return planszaGracza2;
	}

	public void setPlanszaGracza2(Button[][] planszaGracza2) {
		this.planszaGracza2 = planszaGracza2;
	}

	public boolean isCzyGraZKomputerem() {
		return czyGraZKomputerem;
	}

	public void setCzyGraZKomputerem(boolean czyGraZKomputerem) {
		this.czyGraZKomputerem = czyGraZKomputerem;
	}

	@Override
	public String toString() {
		return "Ustawienia [rozmiarPlansz=" + rozmiarPlansz + ", iloscStatkowCztero=" + iloscStatkowCztero
				+ ", iloscStatkowTrzy=" + iloscStatkowTrzy + ", iloscStatkowDwa=" + iloscStatkowDwa
				+ ", iloscStatkowJeden=" + iloscStatkowJeden + "]";
	}

}
