package com.testowanie.utils;

public class Gracz {
    private int telefony = 5;
    private int dzwonki = 5;
    private boolean jestWPozadku = true;
    private Plansza plansza;

    public Gracz(Plansza plansza) {
        this.plansza = plansza;
    }

    public void oddajStrzal(int x, int y) {
        plansza.oddajStrzal(x, y);
    }
}
