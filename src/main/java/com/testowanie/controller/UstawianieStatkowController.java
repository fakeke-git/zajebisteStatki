package com.testowanie.controller;

import com.testowanie.Main;
import com.testowanie.utils.Ustawienia;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.io.IOException;

public class UstawianieStatkowController {
	private Ustawienia ustawienia;

	@FXML
	private Label liczbaCztero;

	@FXML
	private Label liczbaTrzy;

	@FXML
	private Label liczbaDwa;

	@FXML
	private Label liczbaJeden;

	@FXML
	private GridPane plansza1;

	private Button tablicaPrzyciskow[][];
	private boolean czyUstawianie;
	private int iloMasztowy;
	private boolean czyPoziomo;
	private boolean gotoweDoUstawienia;

	public void initialize() {
		ustawienia = (Ustawienia) Main.primaryStage.getUserData();

		tablicaPrzyciskow = new Button[ustawienia.getRozmiarPlansz()][ustawienia.getRozmiarPlansz()];
		czyPoziomo = true;

		dodajP(plansza1, ustawienia.getRozmiarPlansz());
		asd(plansza1, ustawienia.getRozmiarPlansz());

		liczbaCztero.setText(Integer.toString(ustawienia.getIloscStatkowCztero()));
		liczbaTrzy.setText(Integer.toString(ustawienia.getIloscStatkowTrzy()));
		liczbaDwa.setText(Integer.toString(ustawienia.getIloscStatkowDwa()));
		liczbaJeden.setText(Integer.toString(ustawienia.getIloscStatkowJeden()));
	}

	public void dodajP(GridPane p, int rozmiar) {
		for (int i = 0; i < rozmiar; i++) {
			ColumnConstraints col = new ColumnConstraints();
			RowConstraints row = new RowConstraints();
			col.setPercentWidth(100 / rozmiar);
			row.setPercentHeight(100 / rozmiar);
			p.getColumnConstraints().add(col);
			p.getRowConstraints().add(row);
		}
	}

	public void asd(GridPane p, int rozmiar) {
		for (int i = 0; i < rozmiar; i++) {
			for (int j = 0; j < rozmiar; j++) {
				double width = p.getPrefWidth();
				double height = p.getPrefHeight();
				double pv = width / rozmiar;
				double ph = height / rozmiar;
				Button b = new Button();
				tablicaPrzyciskow[i][j] = b;
				b.setStyle("-fx-background-color: aqua");
				b.setPrefWidth(pv);
				b.setPrefHeight(ph);
				p.add(b, j, i);

				b.setUserData(new ButtonProperties(i, j, false, false));

				ustawEventy(b);

//                b.setOnAction((e) -> {
//                    b.setUserData(!((Boolean) b.getUserData()));
//                    if ((Boolean) b.getUserData()) b.setStyle("-fx-background-color: coral");
//                    else b.setStyle("-fx-background-color: aqua");
//                });

			}
		}
	}

	public void cofnij(Event e) throws IOException {
		Scene wyborPrzeciwnika = new Scene(FXMLLoader.load(getClass().getResource("/wyborprzeciwnika.fxml")), 900, 400);
		Node node = (Node) e.getSource();
		Stage stage = (Stage) node.getScene().getWindow();

		stage.setScene(wyborPrzeciwnika);
	}

	public void dalej(Event e) throws IOException {
		Scene gierka = new Scene(FXMLLoader.load(getClass().getResource("/gierka.fxml")), 900, 400);
		Node node = (Node) e.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		ustawienia.setPlanszaGracza1(tablicaPrzyciskow);
		ustawienia.setPlanszaGracza2(tablicaPrzyciskow);

		stage.setScene(gierka);
	}

	public void ustawStatek(Event e) {

		iloMasztowy = ((HBox) e.getSource()).getChildren().size();
		int dostepnosc = 0;

		switch (iloMasztowy) {
		case 1:
			dostepnosc = Integer.valueOf(liczbaJeden.getText());
			break;
		case 2:
			dostepnosc = Integer.valueOf(liczbaDwa.getText());
			break;
		case 3:
			dostepnosc = Integer.valueOf(liczbaTrzy.getText());
			break;
		case 4:
			dostepnosc = Integer.valueOf(liczbaCztero.getText());
			break;
		default:
			break;
		}

		if (dostepnosc > 0)
			czyUstawianie = true;

	}

	public void obrocStatek() {
		if (czyUstawianie) {
			czyPoziomo = !czyPoziomo;
		}
	}

	private class ButtonProperties {

		private int x;
		private int y;
		private boolean zajety;
		private boolean strzelony;

		public ButtonProperties(int x, int y, boolean zajety, boolean strzelony) {
			this.x = x;
			this.y = y;
			this.zajety = zajety;
			this.strzelony = strzelony;
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

	private void ustawEventy(Button b) {
		ustawNajechanie(b);
		ustawWyjechanie(b);
		ustawPrawy(b);
		ustawLewy(b);
	}

	private void ustawNajechanie(Button b) {
		b.setOnMouseEntered(e -> {
//			ButtonProperties test = (ButtonProperties) ((Button) e.getSource()).getUserData();
//			System.out.println(test.isZajety());

			if (czyUstawianie) {
				ButtonProperties buttonProperties = (ButtonProperties) ((Button) e.getSource()).getUserData();
				int x = buttonProperties.getX();
				int y = buttonProperties.getY();

				if (czyPoziomo) {
					if (!(iloMasztowy + y > ustawienia.getRozmiarPlansz()) && (!buttonProperties.isZajety())
							&& !(((ButtonProperties) tablicaPrzyciskow[x][y + iloMasztowy - 1].getUserData()).isZajety())) {
						for (int k = iloMasztowy; k > 0; k--) {
							tablicaPrzyciskow[x][y].setStyle("-fx-background-color: coral");
							y++;
						}
						gotoweDoUstawienia = true;
					} else
						gotoweDoUstawienia = false;
				} else {
					if (!(iloMasztowy + x > ustawienia.getRozmiarPlansz()) && (!buttonProperties.isZajety())
							&& !(((ButtonProperties) tablicaPrzyciskow[x + iloMasztowy - 1][y].getUserData()).isZajety())) {
						for (int k = iloMasztowy; k > 0; k--) {
							tablicaPrzyciskow[x][y].setStyle("-fx-background-color: coral");
							x++;
						}
						gotoweDoUstawienia = true;
					} else
						gotoweDoUstawienia = false;
				}

			}
		});
	}

	private void ustawWyjechanie(Button b) {
		b.setOnMouseExited(e -> {
			czyszczenie(e.getSource());
		});
	}

	private void czyszczenie(Object source) {
		if (czyUstawianie) {
			ButtonProperties buttonProperties = (ButtonProperties) ((Button) source).getUserData();
			int x = buttonProperties.getX();
			int y = buttonProperties.getY();
			if (czyPoziomo) {
				if (!(iloMasztowy + y > ustawienia.getRozmiarPlansz()) && (!buttonProperties.isZajety())
						&& !(((ButtonProperties) tablicaPrzyciskow[x][y + iloMasztowy - 1].getUserData()).isZajety())) {
					for (int k = iloMasztowy; k > 0; k--) {
						tablicaPrzyciskow[x][y].setStyle("-fx-background-color: aqua");
						y++;
					}
				}
			} else {
				if (!(iloMasztowy + x > ustawienia.getRozmiarPlansz()) && (!buttonProperties.isZajety())
						&& !(((ButtonProperties) tablicaPrzyciskow[x + iloMasztowy - 1][y].getUserData()).isZajety())) {
					for (int k = iloMasztowy; k > 0; k--) {
						tablicaPrzyciskow[x][y].setStyle("-fx-background-color: aqua");
						x++;
					}
				}
			}
		}
	}

	private void ustawPrawy(Button b) {
		b.setOnContextMenuRequested(e -> {
			czyszczenie(e.getSource());
			obrocStatek();
			MouseEvent event = new MouseEvent(MouseEvent.MOUSE_ENTERED, b.getLayoutX(), b.getLayoutY(), b.getLayoutX(),
					b.getLayoutY(), MouseButton.SECONDARY, 1, false, false, false, false, false, false, true, false,
					false, true, false, true, null);
			b.fireEvent(event);
		});
	}

	private void ustawLewy(Button b) {
		b.setOnAction(e -> {

			ButtonProperties buttonProperties = (ButtonProperties) ((Button) e.getSource()).getUserData();
			int x = buttonProperties.getX();
			int y = buttonProperties.getY();

			if (gotoweDoUstawienia) {
				for (int k = iloMasztowy; k > 0; k--) {
					if (czyPoziomo) {

						((ButtonProperties) tablicaPrzyciskow[x][y].getUserData()).setZajety(true);

						if (x - 1 >= 0) {
							((ButtonProperties) tablicaPrzyciskow[x - 1][y].getUserData()).setZajety(true);
						}

						if (x + 1 < ustawienia.getRozmiarPlansz()) {
							((ButtonProperties) tablicaPrzyciskow[x + 1][y].getUserData()).setZajety(true);
						}

						if (k == iloMasztowy) {
							for (int i = -1; i <= 1; i++) {
								if (y - 1 >= 0 && x + i >= 0 && x + i < ustawienia.getRozmiarPlansz()) {
									((ButtonProperties) tablicaPrzyciskow[x + i][y - 1].getUserData()).setZajety(true);
								}

							}
						}

						if (k == 1) {
							for (int i = -1; i <= 1; i++) {
								if (y + 1 < ustawienia.getRozmiarPlansz() && x + i >= 0
										&& x + i < ustawienia.getRozmiarPlansz()) {
									((ButtonProperties) tablicaPrzyciskow[x + i][y + 1].getUserData()).setZajety(true);
								}

							}
						}

						y++;
					} else {

						((ButtonProperties) tablicaPrzyciskow[x][y].getUserData()).setZajety(true);

						if (y - 1 >= 0) {
							((ButtonProperties) tablicaPrzyciskow[x][y - 1].getUserData()).setZajety(true);
						}

						if (y + 1 < ustawienia.getRozmiarPlansz()) {
							((ButtonProperties) tablicaPrzyciskow[x][y + 1].getUserData()).setZajety(true);
						}

						if (k == iloMasztowy) {
							for (int i = -1; i <= 1; i++) {
								if (x - 1 >= 0 && y + i >= 0 && y + i < ustawienia.getRozmiarPlansz()) {
									((ButtonProperties) tablicaPrzyciskow[x - 1][y + i].getUserData()).setZajety(true);
								}

							}
						}

						if (k == 1) {
							for (int i = -1; i <= 1; i++) {
								if (x + 1 < ustawienia.getRozmiarPlansz() && y + i >= 0
										&& y + i < ustawienia.getRozmiarPlansz()) {
									((ButtonProperties) tablicaPrzyciskow[x + 1][y + i].getUserData()).setZajety(true);
								}

							}
						}

						x++;
					}
				}
				czyUstawianie = false;
				gotoweDoUstawienia = false;
				switch (iloMasztowy) {
				case 1:
					liczbaJeden.setText(Integer.toString(Integer.valueOf(liczbaJeden.getText()) - 1));
					break;
				case 2:
					liczbaDwa.setText(Integer.toString(Integer.valueOf(liczbaDwa.getText()) - 1));
					break;
				case 3:
					liczbaTrzy.setText(Integer.toString(Integer.valueOf(liczbaTrzy.getText()) - 1));
					break;
				case 4:
					liczbaCztero.setText(Integer.toString(Integer.valueOf(liczbaCztero.getText()) - 1));
					break;
				default:
					break;
				}
			}
		});
	}

}
