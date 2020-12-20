package com.testowanie.utils;


public class ButtonProperties {

	private int x;
	private int y;
	private boolean zajety;
	private boolean strzelony;
	private boolean czyMaStatek;

	public ButtonProperties(int x, int y, boolean zajety, boolean strzelony, boolean czyMaStatek) {
		this.x = x;
		this.y = y;
		this.zajety = zajety;
		this.strzelony = strzelony;
		this.czyMaStatek = czyMaStatek;
	}

	public boolean isCzyMaStatek() {
		return czyMaStatek;
	}

	public void setCzyMaStatek(boolean czyMaStatek) {
		this.czyMaStatek = czyMaStatek;
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

	public boolean isStrzelony() {
		return strzelony;
	}

	public void setStrzelony(boolean strzelony) {
		this.strzelony = strzelony;
	}

}