package com.testowanie.utils;

import javafx.scene.control.Button;

import java.util.LinkedList;
import java.util.List;

public class Komputer {
    private Button[][] buttonyGracza;
    private Button[][] kopiaPlanszyGracza;
    private int rozmiarPlanszy;
    private boolean znalezionoStatek = false;
    private List<Punkt> strzalyWStatek = new LinkedList<>();

    public Komputer(Button[][] buttonyGracza) {
        this.buttonyGracza = buttonyGracza;
        rozmiarPlanszy = buttonyGracza.length;
        zrobKopiePlanszyGracza();
    }
    
    private void zrobKopiePlanszyGracza() {
        kopiaPlanszyGracza = new Button[rozmiarPlanszy][rozmiarPlanszy];
        
        for (int i = 0; i < rozmiarPlanszy; i++) {
            for (int j = 0; j < rozmiarPlanszy; j++) {
                kopiaPlanszyGracza[i][j] = new Button();
                kopiaPlanszyGracza[i][j].setUserData(false);
            }
        }
    }

    public void strzel() {
        if (znalezionoStatek) {
            Punkt ostatniCelny = strzalyWStatek.get(strzalyWStatek.size()-1);
            
            if (ostatniCelny.getX() > 0 && 
                    !((boolean) kopiaPlanszyGracza[ostatniCelny.getX()-1][ostatniCelny.getY()].getUserData())) {
                szczel(ostatniCelny.getX()-1, ostatniCelny.getY());
            } else if (ostatniCelny.getX() < rozmiarPlanszy - 1 &&
                    !((boolean) kopiaPlanszyGracza[ostatniCelny.getX()+1][ostatniCelny.getY()].getUserData())) {
                szczel(ostatniCelny.getX()+1, ostatniCelny.getY());
            } else if (ostatniCelny.getY() > 0 &&
                    !((boolean) kopiaPlanszyGracza[ostatniCelny.getX()][ostatniCelny.getY()-1].getUserData())) {
                szczel(ostatniCelny.getX(), ostatniCelny.getY()-1);
            } else if (ostatniCelny.getY() < rozmiarPlanszy - 1 &&
                    !((boolean) kopiaPlanszyGracza[ostatniCelny.getX()][ostatniCelny.getY()+1].getUserData())) {
                szczel(ostatniCelny.getX(), ostatniCelny.getY()+1);
            } else {
                //todo poprawic, na razie to jakis debilny algorytm

                znalezionoStatek = false;
                strzalyWStatek.clear();
                strzelLosowo();
            }

            
        } else {
            strzelLosowo();
        }
    }

    private void strzelLosowo() {
        int x;
        int y;
//        Button wylosowanyButton;
//        ButtonProperties pripertisWylosowanegoButtona;

        do {
            x = (int) Math.floor(Math.random() * rozmiarPlanszy);
            y = (int) Math.floor(Math.random() * rozmiarPlanszy);
//            wylosowanyButton = buttonyGracza[x][y];
//            pripertisWylosowanegoButtona = ((ButtonProperties) wylosowanyButton.getUserData());
        } while ((boolean) kopiaPlanszyGracza[x][y].getUserData());

        szczel(x, y);

        /*pripertisWylosowanegoButtona.setStrzelony(true);
        wylosowanyButton.setText("X");
        kopiaPlanszyGracza[x][y].setUserData(true);
        
        if (((ButtonProperties) buttonyGracza[x][y].getUserData()).isCzyMaStatek()) {
            strzalyWStatek.add(new Punkt(x, y));
            znalezionoStatek = true;

            if (Stateczek.czyZatonal(buttonyGracza, new Punkt(x, y), "")) {
                ustawTrue();
            }
        }*/
    }

    private void szczel(int x, int y) {
        Button wylosowanyButton = buttonyGracza[x][y];
        ButtonProperties pripertisWylosowanegoButtona = ((ButtonProperties) wylosowanyButton.getUserData());
        pripertisWylosowanegoButtona.setStrzelony(true);
        wylosowanyButton.setText("X");
        kopiaPlanszyGracza[x][y].setUserData(true);

        if (((ButtonProperties) buttonyGracza[x][y].getUserData()).isCzyMaStatek()) {
            strzalyWStatek.add(new Punkt(x, y));
            znalezionoStatek = true;

            if (Stateczek.czyZatonal(buttonyGracza, new Punkt(x, y), "")) {
                ustawTrue();
            }
        }
    }
    
    private void ustawTrue() {
        for (var p : strzalyWStatek) {
           for (int i = -1; i < 2; i++) {
               for (int j = -1; j < 2; j++) {
                   try {
                       kopiaPlanszyGracza[p.getX() + i][p.getY() + j].setUserData(true);
                   } catch (IndexOutOfBoundsException e) {
                       
                   }
               }
           }
        }
        znalezionoStatek = false;
        strzalyWStatek.clear();
    }
}
