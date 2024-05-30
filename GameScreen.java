import javax.swing.*;
import java.awt.*;

public class GameScreen {
    //Główna klasa uruchamiająca aplikację
    public static void main(String[] args) {
        //Tworzenie nowego okna JFrame
        JFrame jframe = new JFrame();
        //Ustawianie rozmiaru i położenia okna
        jframe.setBounds(10, 10, 1000, 600);
        //Ustawianie koloru tła okna
        jframe.setBackground(new Color(0X228B22));
        //Ustawianie tytułu okna
        jframe.setTitle("Gra Chińczyk");
        //Wyłączenie możliwości zmiany rozmiaru okna
        jframe.setResizable(false);
        //Ustawienie działania aplikacji do zamknięcia okna
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Tworzenie obiektu klasy GameMoves
        GameMoves gm = new GameMoves();
        //Ustawianie możliwości otrzymywania focusu przez obiekt GameMoves
        gm.setFocusable(true);
        //Dodawanie obsługi zdarzeń klawiatury do obiektu GameMoves
        gm.addKeyListener(gm);
        //Dodawanie obsługi zdarzeń myszy do obiektu GameMoves
        gm.addMouseListener(gm);
        //Dodawanie obiektu GameMoves do okna JFrame
        jframe.add(gm);
        //Ustawianie widoczności okna JFrame
        jframe.setVisible(true);
    }
}
