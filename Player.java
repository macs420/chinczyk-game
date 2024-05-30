import java.awt.*;

//Klasa do zbierania informacji o graczach 
public class Player { 
	int height, width, status, coin; //Wysokość, szerokość, status, liczba monet gracza
	Pawn[] pa = new Pawn[4]; //Tablica 4 pionków dla każdego gracza 

	//Konstruktor klasy Player
	public Player(int height, int width) {
		status = -1; //Inicjalizacja statusu
		coin = 0; //Inicjalizacja liczby monet
		for (int i = 0; i < 4; i++) { //Inicjalizacja pionków dla każdego gracza
			pa[i] = new Pawn(height, width); 
		}
	}

	//Metoda do rysowania gracza (brak implementacji)
	public void draw(Graphics2D g) {
		//Ta metoda będzie implementowana później w zależności od potrzeb
	}
}
