package com.testowanie.utils;

public class Komorka {
    private Punkt punkt;
    private boolean czyStrzelona = false;
    private boolean czyMaStatek = false;

    public Komorka(Punkt punkt) {
        this.punkt = punkt;
    }

    public Punkt getPunkt() {
        return punkt;
    }

    public boolean isCzyStrzelona() {
        return czyStrzelona;
    }

    public boolean isCzyMaStatek() {
        return czyMaStatek;
    }

    public void ustrzel() {czyStrzelona = true;}
}
