import java.awt.*;

//Klasa do określania pozycji pionków 
public class Build_Player {
    Player[] pl=new Player[4]; //Tablica przechowująca graczy
    int[][] initialx= { //Tablica na początkowe pozycje x graczy
        {1,1,3,3}, //Wiersz 0 dla gracza 1
        {10,10,12,12}, //Wiersz 1 dla gracza 2
        {10,10,12,12}, //Wiersz 2 dla gracza 3
        {1,1,3,3} //Wiersz 3 dla gracza 4
    };
    int[][] initialy= { //Tablica na początkowe pozycje y graczy
        {1,3,1,3}, //Wiersz 0 dla gracza 1
        {1,3,1,3}, //Wiersz 1 dla gracza 2
        {10,12,10,12}, //Wiersz 2 dla gracza 3
        {10,12,10,12} //Wiersz 3 dla gracza 4
    };

    //Konstruktor do inicjalizacji pozycji graczy
    public Build_Player(int height, int width) {
        for(int i=0;i<4;i++) {
            pl[i]=new Player(height,width);
        }
    }

    //Metoda do rysowania graczy
    public void draw(Graphics2D g) {
        for(int i=0;i<4;i++) {
            for(int j=0;j<4;j++) {
                pl[i].pa[j].draw(g,initialx[i][j],initialy[i][j],i); //Rysuje gracza na odpowiedniej pozycji
            }
        }
    }
}
