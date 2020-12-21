package com.testowanie.utils;

public class ButtonProperties {

	private int x;
	private int y;
	private boolean zajety;
	private boolean podwojnieZajety;
	private boolean strzelony;
	private boolean czyMaStatek;

	public ButtonProperties(int x, int y, boolean zajety, boolean strzelony, boolean czyMaStatek,
			boolean podwojnieZajety) {
		this.x = x;
		this.y = y;
		this.zajety = zajety;
		this.strzelony = strzelony;
		this.czyMaStatek = czyMaStatek;
		this.podwojnieZajety = podwojnieZajety;
	}

	@Override
	public String toString() {
		return "ButtonProperties [x=" + x + ", y=" + y + ", zajety=" + zajety + ", podwojnieZajety=" + podwojnieZajety
				+ ", strzelony=" + strzelony + ", czyMaStatek=" + czyMaStatek + "]";
	}

	public boolean isPodwojnieZajety() {
		return podwojnieZajety;
	}

	public void setPodwojnieZajety(boolean podwojnieZajety) {
		this.podwojnieZajety = podwojnieZajety;
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