package com.testowanie.utils;

public class DaneKoncaGry {
    private boolean granoZKomputerem;
    private int graczZwyciezca;

    public DaneKoncaGry(boolean granoZKomputerem, int gracz) {
        this.granoZKomputerem = granoZKomputerem;
        this.graczZwyciezca = gracz;
    }

    public boolean isGranoZKomputerem() {
        return granoZKomputerem;
    }

    public int getGraczZwyciezca() {
        return graczZwyciezca;
    }
}
