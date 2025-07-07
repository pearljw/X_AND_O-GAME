package ui;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class X_AND_O {
    JFrame welcome, idScreen, gameWindow, winWindow;
    JTextField player1Field, player2Field;
    String player1Name = "P1";
    String player2Name = "P2";
    int currentTurn = 0;
    ArrayList<Integer> p1Moves = new ArrayList<>();
    ArrayList<Integer> p2Moves = new ArrayList<>();
    JButton[] cells = new JButton[9];
    JLabel gameStatus;

    public static void main(String[] args) {
        X_AND_O ttt = new X_AND_O();
        ttt.showWelcome();
    }

    public void showWelcome() {
        welcome = new JFrame("Tic Tac Toe :)");
        welcome.setSize(400, 300);
        welcome.setLayout(new BorderLayout());

        JPanel top = new JPanel();
        top.setBackground(Color.PINK);
        JLabel title = new JLabel("Welcome!!");
        title.setFont(new Font("Jokerman", Font.BOLD, 30));
        title.setForeground(Color.YELLOW);
        top.add(title);
        welcome.add(top, BorderLayout.NORTH);

        JPanel middle = new JPanel();
        middle.setBackground(new Color(255, 228, 225));
        JLabel text = new JLabel("Click Start");
        text.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        text.setForeground(Color.BLUE);
        middle.add(text);
        welcome.add(middle, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        JButton start = new JButton("Start");
        start.setFocusPainted(false);
        start.setBackground(Color.GREEN);
        start.setForeground(Color.WHITE);
        start.setFont(new Font("Arial Black", Font.BOLD, 16));
        JButton quit = new JButton("Quit");
        quit.setBackground(Color.RED);
        quit.setForeground(Color.WHITE);
        quit.setFont(new Font("Arial Black", Font.BOLD, 16));
        bottom.add(start);
        bottom.add(quit);
        welcome.add(bottom, BorderLayout.SOUTH);

        start.addActionListener(e -> {
            welcome.dispose();
            showIdScreen();
        });

        quit.addActionListener(e -> System.exit(0));

        welcome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcome.setVisible(true);
    }
