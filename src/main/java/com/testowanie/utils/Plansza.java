package com.testowanie.utils;

import java.util.List;

public class Plansza {
    private int rozmiar;
    private List<Stateczek> stateczki;
    private List<List<Komorka>> komorki;

    public Plansza(int rozmiar, List<Stateczek> stateczki, List<List<Komorka>> komorki) {
        this.rozmiar = rozmiar;
        this.stateczki = stateczki;
        this.komorki = komorki;
    }

    public int getRozmiar() {
        return rozmiar;
    }

    public List<Stateczek> getStateczki() {
        return stateczki;
    }

    public List<List<Komorka>> getKomorki() {
        return komorki;
    }

    public void oddajStrzal(int x, int y) {
        komorki.get(y).get(x).ustrzel();
    }

    public boolean czyKoniec() {
        for (var stateczek : stateczki) {
            if (!stateczek.czyZatonal()) return false;
        }
        return true;
    }
}
