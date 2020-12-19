package com.testowanie.utils;

import java.util.List;

public class Stateczek {
    private int rozmiar;
    private boolean czyJestWPionie;
    private List<Komorka> komorki;

    public Stateczek(int rozmiar, boolean czyJestWPionie, List<Komorka> komorki) {
        this.rozmiar = rozmiar;
        this.czyJestWPionie = czyJestWPionie;
        this.komorki = komorki;
    }

    public int getRozmiar() {
        return rozmiar;
    }

    public boolean isCzyJestWPionie() {
        return czyJestWPionie;
    }

    public List<Komorka> getKomorki() {
        return komorki;
    }

    public boolean czyZatonal() {
        for (var komorka : komorki) {
            if (!komorka.isCzyStrzelona()) return false;
        }
        return true;
    }
}
