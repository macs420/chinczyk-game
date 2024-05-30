import java.awt.*;

//Klasa do określania pionka 
public class Pawn {
    int x, y;
    int current;
    int height, width;

    public Pawn(int h, int w) { //Konstruktor do inicjalizacji pionka
        current = -1;
        x = -1;
        y = -1;
        height = h;
        width = w;
    }

    //Funkcja do rysowania pionka na planszy
    public void draw(Graphics2D g, int i, int j, int play) {
        if (current == -1) { //Jeśli pionek nie jest na planszy
            int temp1 = 80 + (height / 2), temp2 = 50 + (width / 2); //Początkowa pozycja pionka
            x = i; //Współrzędna x pionka
            y = j; //Współrzędna y pionka
            if (play == 0) { //Jeśli gracz 1
                g.setColor(Color.RED); //Kolor pionka
            } else if (play == 1) { //Jeśli gracz 2
                g.setColor(Color.GREEN); //Kolor pionka
            } else if (play == 2) { //Jeśli gracz 3
                g.setColor(Color.YELLOW); //Kolor pionka
            } else if (play == 3) { //Jeśli gracz 4
                g.setColor(Color.BLUE); //Kolor pionka
            }
            g.fillOval(temp1 + 5 + (i * width), temp2 + 5 + (j * height), width - 10, height - 10); //Rysowanie pionka na planszy
            g.setStroke(new BasicStroke(2)); //Grubość obramowania
            g.setColor(Color.BLACK); //Kolor obramowania
            g.drawOval(temp1 + 5 + (i * width), temp2 + 5 + (j * height), width - 10, height - 10); //Rysowanie obramowania pionka
        } else { //Jeśli pionek jest na planszy
            int temp1 = 80, temp2 = 50; //Początkowa pozycja planszy
            x = Path.ax[play][current]; //Współrzędna x pionka
            y = Path.ay[play][current]; //Współrzędna y pionka
            if (play == 0) {
                g.setColor(Color.RED);
            } else if (play == 1) {
                g.setColor(Color.GREEN);
            } else if (play == 2) {
                g.setColor(Color.YELLOW);
            } else if (play == 3) {
                g.setColor(Color.BLUE);
            }
            g.fillOval(temp1 + 5 + (x * width), temp2 + 5 + (y * height), width - 10, height - 10); //Rysowanie pionka na planszy
            g.setStroke(new BasicStroke(2)); //Grubość obramowania
            g.setColor(Color.BLACK);
            g.drawOval(temp1 + 5 + (x * width), temp2 + 5 + (y * height), width - 10, height - 10); //Rysowanie obramowania pionka
        }
    }
}
