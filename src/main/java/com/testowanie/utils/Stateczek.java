package com.testowanie.utils;

import com.testowanie.Main;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class Stateczek {
    public static void rozmiescLosowo(Button[][] przyciski, int czteromasztowcow, int trzymasztowcow, int dwumasztowcow, int jednomasztowcow) {
        while (czteromasztowcow > 0) {
            ustawStatek(przyciski, 4);
            czteromasztowcow--;
        }
        while (trzymasztowcow > 0) {
            ustawStatek(przyciski, 3);
            trzymasztowcow--;
        }
        while (dwumasztowcow > 0) {
            ustawStatek(przyciski, 2);
            dwumasztowcow--;
        }
        while (jednomasztowcow > 0) {
            ustawStatek(przyciski, 1);
            jednomasztowcow--;
        }

    }

    private static void ustawStatek(Button[][] przyciski, int ileMasztow) {
        List<Punkt> listaPunktow = new ArrayList<>();
        int rozmiar = przyciski.length;
        boolean czyPoziomo;
        int x;
        int y;
        do {
            listaPunktow.clear();

            do {
                czyPoziomo = Math.floor(Math.random() * 2) == 1;
                x = czyPoziomo ? (int) Math.floor(Math.random() * (rozmiar - 4)) : (int) Math.floor(Math.random() * rozmiar);
                y = !czyPoziomo ? (int) Math.floor(Math.random() * (rozmiar - 4)) : (int) Math.floor(Math.random() * rozmiar);
            } while (((ButtonProperties) przyciski[x][y].getUserData()).isZajety());
            listaPunktow.add(new Punkt(x, y));

            while (listaPunktow.size() < ileMasztow) {
                x = czyPoziomo ? x + 1 : x;
                y = !czyPoziomo ? y + 1 : y;
                if (((ButtonProperties) przyciski[x][y].getUserData()).isZajety()) break;
                listaPunktow.add(new Punkt(x, y));
            }

        } while (listaPunktow.size() < ileMasztow);

        ustawCzyZajetyICzyMaStatek(przyciski, listaPunktow, czyPoziomo);
        pokoloruj(przyciski);
    }

    private static void ustawCzyZajetyICzyMaStatek(Button[][] przyciski, List<Punkt> listaPunktow, boolean czyPoziomo) {
        for (int i = 0; i < listaPunktow.size(); i++) {
            int x = listaPunktow.get(i).getX();
            int y = listaPunktow.get(i).getY();

            if (i == 0) {
                for (int j = -1; j < 2; j++) {
                    try {
                        ButtonProperties bp = (ButtonProperties) przyciski[czyPoziomo ? x - 1 : x + j][!czyPoziomo ? y - 1 : y + j].getUserData();
                        if (bp.isZajety()) bp.setPodwojnieZajety(true);
                        else bp.setZajety(true);
                    } catch (IndexOutOfBoundsException ex) {
                    }
                }
            }

            for (int j = -1; j < 2; j++) {
                try {
                    Button button = przyciski[czyPoziomo ? x : x + j][!czyPoziomo ? y : y + j];
                    ButtonProperties bp = (ButtonProperties) button.getUserData();
                    if (bp.isZajety()) bp.setPodwojnieZajety(true);
                    else bp.setZajety(true);
                    if (j==0) ((ButtonProperties) button.getUserData()).setCzyMaStatek(true);
                } catch (IndexOutOfBoundsException ex) {
                }
            }

            if (i == listaPunktow.size()-1) {
                for (int j = -1; j < 2; j++) {
                    try {
                        ButtonProperties bp = (ButtonProperties) przyciski[czyPoziomo ? x + 1 : x + j][!czyPoziomo ? y + 1 : y + j].getUserData();
                        if (bp.isZajety()) bp.setPodwojnieZajety(true);
                        else bp.setZajety(true);
                    } catch (IndexOutOfBoundsException ex) {
                    }
                }
            }
        }
    }

    private static void pokoloruj(Button[][] przyciski) {
        for (var buttons : przyciski) {
            for (var b : buttons) {
                if (((ButtonProperties) b.getUserData()).isCzyMaStatek()) b.setStyle("-fx-background-color: coral");
            }
        }
    }

    public static boolean czyZatonal(Button[][] plansza, Punkt strzal, String kierunek) {
        if (!((ButtonProperties) plansza[strzal.getX()][strzal.getY()].getUserData()).isCzyMaStatek()) return false;

        int rozmiar = plansza.length;

        boolean center = czyNaOkolo(plansza, strzal);
        boolean gora = strzal.getY() <= 0 || !((ButtonProperties) plansza[strzal.getX()][strzal.getY() - 1].getUserData())
                .isCzyMaStatek() || (!kierunek.equals("gora") && !kierunek.equals("")) || czyZatonal(plansza, new Punkt(strzal.getX(), strzal.getY() - 1), "gora");

        boolean dol =
                strzal.getY() >= rozmiar - 1 || !((ButtonProperties) plansza[strzal.getX()][strzal.getY() + 1].getUserData())
                        .isCzyMaStatek() || (!kierunek.equals("dol") && !kierunek.equals("")) || czyZatonal(plansza, new Punkt(strzal.getX(), strzal.getY() + 1), "dol");

        boolean lewo = strzal.getX() <= 0 || !((ButtonProperties) plansza[strzal.getX() - 1][strzal.getY()].getUserData())
                .isCzyMaStatek() || (!kierunek.equals("gora") && !kierunek.equals("")) || czyZatonal(plansza, new Punkt(strzal.getX() - 1, strzal.getY()), "lewo");


        boolean prawo = strzal.getX() >= rozmiar - 1 || !((ButtonProperties) plansza[strzal.getX() + 1][strzal.getY()].getUserData())
                .isCzyMaStatek() || (!kierunek.equals("gora") && !kierunek.equals("")) || czyZatonal(plansza, new Punkt(strzal.getX() + 1, strzal.getY()), "prawo");

        return center && gora && prawo && lewo && dol;
    }

    private static boolean czyNaOkolo(Button[][] plansza, Punkt strzal) {
        int rozmiar = plansza.length;
        return (
                ((ButtonProperties) plansza[strzal.getX()][strzal.getY()].getUserData()).isCzyMaStatek()
                        && ((ButtonProperties) plansza[strzal.getX()][strzal.getY()].getUserData()).isStrzelony()
                        && ((
                        ((ButtonProperties) plansza[strzal.getX()][strzal.getY() > 0 ? strzal.getY() - 1 : strzal.getY()].getUserData()).isCzyMaStatek()
                                && ((ButtonProperties) plansza[strzal.getX()][strzal.getY() > 0 ? strzal.getY() - 1 : strzal.getY()].getUserData()).isStrzelony()
                )
                        || (
                        !((ButtonProperties) plansza[strzal.getX()][strzal.getY() > 0 ? strzal.getY() - 1 : strzal.getY()].getUserData()).isCzyMaStatek()
                                && ((ButtonProperties) plansza[strzal.getX()][strzal.getY() > 0 ? strzal.getY() - 1 : strzal.getY()].getUserData()).isZajety()
                )
                )
                        && ((
                        ((ButtonProperties) plansza[strzal.getX()][strzal.getY() < rozmiar - 1 ? strzal.getY() + 1 : strzal.getY()].getUserData()).isCzyMaStatek()
                                && ((ButtonProperties) plansza[strzal.getX()][strzal.getY() < rozmiar - 1 ? strzal.getY() + 1 : strzal.getY()].getUserData()).isStrzelony()
                )
                        || (
                        !((ButtonProperties) plansza[strzal.getX()][strzal.getY() < rozmiar - 1 ? strzal.getY() + 1 : strzal.getY()].getUserData()).isCzyMaStatek()
                                && ((ButtonProperties) plansza[strzal.getX()][strzal.getY() < rozmiar - 1 ? strzal.getY() + 1 : strzal.getY()].getUserData()).isZajety()
                ))
                        && ((
                        ((ButtonProperties) plansza[strzal.getX() > 0 ? strzal.getX() - 1 : strzal.getX()][strzal.getY()].getUserData()).isCzyMaStatek()
                                && ((ButtonProperties) plansza[strzal.getX() > 0 ? strzal.getX() - 1 : strzal.getX()][strzal.getY()].getUserData()).isStrzelony()
                )
                        || (
                        !((ButtonProperties) plansza[strzal.getX() > 0 ? strzal.getX() - 1 : strzal.getX()][strzal.getY()].getUserData()).isCzyMaStatek()
                                && ((ButtonProperties) plansza[strzal.getX() > 0 ? strzal.getX() - 1 : strzal.getX()][strzal.getY()].getUserData()).isZajety()
                ))
                        && ((
                        ((ButtonProperties) plansza[strzal.getX() < rozmiar - 1 ? strzal.getX() + 1 : strzal.getX()][strzal.getY()].getUserData()).isCzyMaStatek()
                                && ((ButtonProperties) plansza[strzal.getX() < rozmiar - 1 ? strzal.getX() + 1 : strzal.getX()][strzal.getY()].getUserData()).isStrzelony()
                )
                        || (
                        !((ButtonProperties) plansza[strzal.getX() < rozmiar - 1 ? strzal.getX() + 1 : strzal.getX()][strzal.getY()].getUserData()).isCzyMaStatek()
                                && ((ButtonProperties) plansza[strzal.getX() < rozmiar - 1 ? strzal.getX() + 1 : strzal.getX()][strzal.getY()].getUserData()).isZajety()
                ))
        );
    }
}
