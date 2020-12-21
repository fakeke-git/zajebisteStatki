package com.testowanie.utils;

import javafx.scene.control.Button;

import java.util.List;

public class Stateczek {
    public static boolean czyZatonal(Button[][] plansza,Punkt strzal, String kierunek) {
        if (!((ButtonProperties)plansza[strzal.getX()][strzal.getY()].getUserData()).isCzyMaStatek()) return false;

        int rozmiar = plansza.length;

        boolean center = czyNaOkolo(plansza, strzal);
        boolean gora = strzal.getY() > 0 && ((ButtonProperties)plansza[strzal.getX()][strzal.getY()-1].getUserData())
                .isCzyMaStatek() && (kierunek.equals("gora") || kierunek.equals("")) ?
        czyZatonal(plansza, new Punkt(strzal.getX(), strzal.getY()-1), "gora") : true;
                //: czyNaOkolo(plansza, new Punkt(strzal.getX(), strzal.getY() > 0 ? strzal.getY()-1 : strzal.getY()));

        boolean dol =
                strzal.getY() < rozmiar-1 && ((ButtonProperties)plansza[strzal.getX()][strzal.getY()+1].getUserData())
                        .isCzyMaStatek() && (kierunek.equals("dol") || kierunek.equals("")) ?
                        czyZatonal(plansza, new Punkt(strzal.getX(), strzal.getY()+1), "dol") : true;
//                        : czyNaOkolo(plansza, new Punkt(strzal.getX(), strzal.getY() < rozmiar - 1 ? strzal.getY()+1 : strzal.getY()));

        boolean lewo = strzal.getX() > 0 && ((ButtonProperties)plansza[strzal.getX()-1][strzal.getY()].getUserData())
                .isCzyMaStatek() && (kierunek.equals("gora") || kierunek.equals("")) ?
                czyZatonal(plansza, new Punkt(strzal.getX()-1, strzal.getY()), "lewo") : true;
//                : czyNaOkolo(plansza, new Punkt(strzal.getX() > 0 ? strzal.getX()-1 : strzal.getX(), strzal.getY()));


        boolean prawo = strzal.getX() < rozmiar-1 && ((ButtonProperties)plansza[strzal.getX()+1][strzal.getY()].getUserData())
                .isCzyMaStatek() && (kierunek.equals("gora") || kierunek.equals("")) ?
                czyZatonal(plansza, new Punkt(strzal.getX()+1, strzal.getY()), "prawo") : true;
//                : czyNaOkolo(plansza, new Punkt(strzal.getX() < rozmiar - 1 ? strzal.getX()+1 : strzal.getX(), strzal.getY()));

        return center && gora && prawo && lewo && dol;
    }

    private static boolean czyNaOkolo(Button[][] plansza,Punkt strzal) {
        int rozmiar = plansza.length;
        return (
                ((ButtonProperties) plansza[strzal.getX()][strzal.getY()].getUserData()).isCzyMaStatek()
                        && ((ButtonProperties) plansza[strzal.getX()][strzal.getY()].getUserData()).isStrzelony()
                        && ((
                        ((ButtonProperties) plansza[strzal.getX()][strzal.getY() > 0 ? strzal.getY()-1 : strzal.getY()].getUserData()).isCzyMaStatek()
                                && ((ButtonProperties) plansza[strzal.getX()][strzal.getY() > 0 ? strzal.getY()-1 : strzal.getY()].getUserData()).isStrzelony()
                )
                        || (
                        !((ButtonProperties) plansza[strzal.getX()][strzal.getY() > 0 ? strzal.getY()-1 : strzal.getY()].getUserData()).isCzyMaStatek()
                                && ((ButtonProperties) plansza[strzal.getX()][strzal.getY() > 0 ? strzal.getY()-1 : strzal.getY()].getUserData()).isZajety()
                )
                )
                        && ((
                        ((ButtonProperties) plansza[strzal.getX()][strzal.getY() < rozmiar - 1 ? strzal.getY()+1 : strzal.getY()].getUserData()).isCzyMaStatek()
                                && ((ButtonProperties) plansza[strzal.getX()][strzal.getY() < rozmiar - 1 ? strzal.getY()+1 : strzal.getY()].getUserData()).isStrzelony()
                )
                        || (
                        !((ButtonProperties) plansza[strzal.getX()][strzal.getY() < rozmiar - 1 ? strzal.getY()+1 : strzal.getY()].getUserData()).isCzyMaStatek()
                                && ((ButtonProperties) plansza[strzal.getX()][strzal.getY() < rozmiar - 1 ? strzal.getY()+1 : strzal.getY()].getUserData()).isZajety()
                ))
                        && ((
                        ((ButtonProperties) plansza[strzal.getX() > 0 ? strzal.getX()-1 : strzal.getX()][strzal.getY()].getUserData()).isCzyMaStatek()
                                && ((ButtonProperties) plansza[strzal.getX() > 0 ? strzal.getX()-1 : strzal.getX()][strzal.getY()].getUserData()).isStrzelony()
                )
                        || (
                        !((ButtonProperties) plansza[strzal.getX() > 0 ? strzal.getX()-1 : strzal.getX()][strzal.getY()].getUserData()).isCzyMaStatek()
                                && ((ButtonProperties) plansza[strzal.getX() > 0 ? strzal.getX()-1 : strzal.getX()][strzal.getY()].getUserData()).isZajety()
                ))
                        && ((
                        ((ButtonProperties) plansza[strzal.getX() < rozmiar - 1 ? strzal.getX()+1 : strzal.getX()][strzal.getY()].getUserData()).isCzyMaStatek()
                                && ((ButtonProperties) plansza[strzal.getX() < rozmiar - 1 ? strzal.getX()+1 : strzal.getX()][strzal.getY()].getUserData()).isStrzelony()
                )
                        || (
                        !((ButtonProperties) plansza[strzal.getX() < rozmiar - 1 ? strzal.getX()+1 : strzal.getX()][strzal.getY()].getUserData()).isCzyMaStatek()
                                && ((ButtonProperties) plansza[strzal.getX() < rozmiar - 1 ? strzal.getX()+1 : strzal.getX()][strzal.getY()].getUserData()).isZajety()
                ))
        );
    }
}
