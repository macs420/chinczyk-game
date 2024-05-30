import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Klasa definiująca ruchy w grze
public class GameMoves extends JPanel implements KeyListener, ActionListener, MouseListener {
    private static final long serialVersionUID = 1L;
    Layout la; //Obiekt klasy Layout, odpowiadający za układ
    Build_Player p; //Obiekt klasy Build_Player, odpowiadający za budowę gracza
    Timer time; //Obiekt klasy Timer, odpowiadający za czas gry
    int delay = 10; //Opóźnienie dla timera
    int current_player, dice; //Zmienna przechowująca aktualnego gracza i wynik rzutu kostką
    int flag = 0, roll, kill = 0; //Flaga dla rzutu kostką i zmienna kill

    public GameMoves() {
        setFocusTraversalKeysEnabled(false);
        requestFocus();
        current_player = 0; //Pierwszy gracz to czerwony
        la = new Layout(80, 50);
        p = new Build_Player(la.height, la.width);
        dice = 0;
        flag = 0;
        roll = 0;
        kill = 0;
    }

    //Metoda do rysowania gry
    public void paint(Graphics g) {
        la.draw((Graphics2D) g);
        p.draw((Graphics2D) g);
        if (p.pl[current_player].coin == 4) { //Warunek zwycięstwa
            g.setColor(Color.WHITE);
            g.fillRect(590, 100, 380, 130);
            if (current_player == 0) {
                g.setColor(Color.RED);
            } else if (current_player == 1) {
                g.setColor(Color.GREEN);
            } else if (current_player == 2) {
                g.setColor(Color.YELLOW);
            } else if (current_player == 3) {
                g.setColor(Color.BLUE);
            }
            g.setFont(new Font("WEST JAVA", Font.BOLD, 40));
            g.drawString("Gracz " + (current_player + 1) + " zwycięża.", 600, 350);
            g.drawString("Gratulacje.", 600, 400);
            current_player = 0;
            la = new Layout(80, 50);
            p = new Build_Player(la.height, la.width);
            dice = 0;
            flag = 0;
            roll = 0;
            kill = 0;
        } else if (dice != 0) { //Warunek dla rzutu kostką
            g.setColor(Color.pink);
            g.fillRect(590, 300, 260, 200);
            if (current_player == 0) {
                g.setColor(Color.RED);
            } else if (current_player == 1) {
                g.setColor(Color.GREEN);
            } else if (current_player == 2) {
                g.setColor(Color.YELLOW);
            } else if (current_player == 3) {
                g.setColor(Color.BLUE);
            }

            g.setFont(new Font("WEST JAVA", Font.BOLD, 20));
            if (current_player == 0) {
                g.drawString("Tura CZERWONEGO:", 600, 350);
            } else if (current_player == 1) {
                g.drawString("Tura ZIELONEGO:", 600, 350);
            } else if (current_player == 2) {
                g.drawString("Tura ŻÓŁTEGO:", 600, 350);
            } else if (current_player == 3) {
                g.drawString("Tura NIEBIESKIEGO:", 600, 350);
            }

            //Rysowanie kostki
            g.setColor(Color.BLACK);
            g.drawRect(670, 370, 100, 100);
            g.setFont(new Font("WEST JAVA", Font.BOLD, 50));
            g.drawString("" + dice, 700, 440);
        }
        if (flag == 0 && dice != 0 && dice != 6 && kill == 0) {
            current_player = (current_player + 1) % 4;
        }
        kill = 0;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER && flag == 0) { //Jeśli naciśnięto Enter, kostka jest rzucana
            roll = 0;
            dice = 1 + (int) (Math.random() * 6); //Generowanie losowej liczby dla rzutu kostką
            repaint();
            for (int i = 0; i < 4; i++) {
                if (p.pl[current_player].pa[i].current != -1 && p.pl[current_player].pa[i].current != 56 && (p.pl[current_player].pa[i].current + dice) <= 56) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 0 && dice == 6) {
                for (int i = 0; i < 4; i++) {
                    if (p.pl[current_player].pa[i].current == -1) {
                        flag = 1;
                        break;
                    }
                }
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
        if (flag == 1) {
            int x = e.getX();
            int y = e.getY();
            x = x - 80;
            y = y - 50;
            x = x / 30;
            y = y / 30;
            int value = -1;
            //Sprawdzenie czy kostka wylosowała 6
            if (dice == 6) {
                for (int i = 0; i < 4; i++) {
                    if (p.pl[current_player].pa[i].x == x && p.pl[current_player].pa[i].y == y && (p.pl[current_player].pa[i].current + dice) <= 56) {
                        value = i;
                        flag = 0;
                        break;
                    }
                }
                if (value != -1) {
                    p.pl[current_player].pa[value].current += dice;
                    if (p.pl[current_player].pa[value].current == 56) {
                        p.pl[current_player].coin++;
                    }
                    int k = 0;
                    int hou = p.pl[current_player].pa[value].current;
                    if ((hou % 13) != 0 && (hou % 13) != 8 && hou < 51) {
                        for (int i = 0; i < 4; i++) {
                            if (i != current_player) {
                                for (int j = 0; j < 4; j++) {
                                    int tem1 = Path.ax[current_player][p.pl[current_player].pa[value].current], tem2 = Path.ay[current_player][p.pl[current_player].pa[value].current];
                                    if (p.pl[i].pa[j].x == tem1 && p.pl[i].pa[j].y == tem2) {
                                        p.pl[i].pa[j].current = -1;
                                        kill = 1;
                                        k = 1;
                                        break;
                                    }
                                }
                            }
                            if (k == 1)
                                break;
                        }
                    }
                } else {
                    for (int i = 0; i < 4; i++) {
                        if (p.pl[current_player].pa[i].current == -1) {
                            p.pl[current_player].pa[i].current = 0;
                            flag = 0;
                            break;
                        }
                    }
                }
            } else {
                for (int i = 0; i < 4; i++) {
                    if (p.pl[current_player].pa[i].x == x && p.pl[current_player].pa[i].y == y && (p.pl[current_player].pa[i].current + dice) <= 56) {
                        value = i;
                        flag = 0;
                        break;
                    }
                }
                if (value != -1) {
                    p.pl[current_player].pa[value].current += dice;
                    if (p.pl[current_player].pa[value].current == 56) {
                        p.pl[current_player].coin++;
                    }
                    int k = 0;
                    int hou = p.pl[current_player].pa[value].current;
                    if ((hou % 13) != 0 && (hou % 13) != 8 && hou < 51) {
                        for (int i = 0; i < 4; i++) {
                            if (i != current_player) {
                                for (int j = 0; j < 4; j++) {
                                    int tem1 = Path.ax[current_player][p.pl[current_player].pa[value].current], tem2 = Path.ay[current_player][p.pl[current_player].pa[value].current];
                                    if (p.pl[i].pa[j].x == tem1 && p.pl[i].pa[j].y == tem2) {
                                        p.pl[i].pa[j].current = -1;
                                        kill = 1;
                                        k = 1;
                                        break;
                                    }
                                }
                            }
                            if (k == 1)
                                break;
                        }
                    }
                }
            }
            repaint();
        }
    }

    //Te metody nie są używane, ale są obowiązkowe do zdefiniowania, ponieważ są to abstrakcyjne metody MouseListener
    public void actionPerformed(ActionEvent e) {
    }

    public void keyReleased(KeyEvent arg0) {
    }

    public void keyTyped(KeyEvent arg0) {
    }

    public void mouseEntered(MouseEvent arg0) {
    }

    public void mouseExited(MouseEvent arg0) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent arg0) {
    }
}
