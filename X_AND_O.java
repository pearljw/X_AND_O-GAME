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

    public void showIdScreen() {
        idScreen = new JFrame("Player IDs");
        idScreen.setSize(400, 300);
        idScreen.setLayout(new GridLayout(4, 1));

        JPanel p1Panel = new JPanel();
        JLabel p1Label = new JLabel("Player 1 Name (X): ");
        p1Label.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        player1Field = new JTextField(8);
        p1Panel.add(p1Label);
        p1Panel.add(player1Field);
        p1Panel.setBackground(new Color(255, 239, 213));

        JPanel p2Panel = new JPanel();
        JLabel p2Label = new JLabel("Player 2 Name (O): ");
        p2Label.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        player2Field = new JTextField(8);
        p2Panel.add(p2Label);
        p2Panel.add(player2Field);
        p2Panel.setBackground(new Color(255, 239, 213));

        JPanel btnPanel = new JPanel();
        JButton goBtn = new JButton("Go!");
        goBtn.setBackground(new Color(18, 186, 236));
        goBtn.setForeground(Color.WHITE);
        goBtn.setFont(new Font("Arial Black", Font.BOLD, 16));
        JButton quitBtn = new JButton("Quit");
        quitBtn.setBackground(new Color(241, 44, 11));
        quitBtn.setForeground(Color.WHITE);
        quitBtn.setFont(new Font("Arial Black", Font.BOLD, 16));
        btnPanel.setBackground(new Color(255, 239, 213));
        btnPanel.add(goBtn);
        btnPanel.add(quitBtn);

        idScreen.add(new JLabel(" Enter Names Below ", SwingConstants.CENTER));
        idScreen.add(p1Panel);
        idScreen.add(p2Panel);
        idScreen.add(btnPanel);

        goBtn.addActionListener(e -> {
            String n1 = player1Field.getText().trim();
            String n2 = player2Field.getText().trim();
            if (!n1.equals("") && !n2.equals("")) {
                player1Name = n1;
                player2Name = n2;
                idScreen.dispose();
                showGame();
            } else {
                JOptionPane.showMessageDialog(idScreen, "Names required!");
            }
        });

        quitBtn.addActionListener(e -> System.exit(0));

        idScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        idScreen.getContentPane().setBackground(new Color(255, 239, 213));  // Light peachy background
        idScreen.setVisible(true);
    }

public void showGame() {
        gameWindow = new JFrame("Tic Tac Toe");
        gameWindow.setSize(450, 500);
        gameWindow.setLayout(new BorderLayout());

        JPanel grid = new JPanel(new GridLayout(3,3,10,10));
        Color[] tileColors = {
                new Color(255,179,186), new Color(255,223,186), new Color(255,255,186),
                new Color(186,255,201), new Color(186,225,255), new Color(255,186,250),
                new Color(255,204,229), new Color(204,255,229), new Color(204,229,255)
        };

        for (int i=0; i<9; i++) {
            JButton b = new JButton();
            b.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
            b.setBackground(tileColors[i]);
            int pos = i + 1;
            b.addActionListener(e -> cellClicked(b, pos));
            cells[i] = b;
            grid.add(b);
            b.setFocusPainted(false);
        }

        gameStatus = new JLabel("Turn: " + player1Name, SwingConstants.CENTER);
        gameStatus.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        gameStatus.setOpaque(true);
        gameStatus.setBackground(Color.PINK);

        gameWindow.add(gameStatus, BorderLayout.NORTH);
        gameWindow.add(grid, BorderLayout.CENTER);

        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setVisible(true);
    }

        public void cellClicked(JButton btn, int pos) {
        if (!btn.getText().equals("")) return;

        if (currentTurn == 0) {
            btn.setText("X");
            btn.setForeground(Color.RED);
            p1Moves.add(pos);
            gameStatus.setText("Turn: " + player2Name);
            currentTurn = 1;
        } else {
            btn.setText("O");
            btn.setForeground(Color.BLUE);
            p2Moves.add(pos);
            gameStatus.setText("Turn: " + player1Name);
            currentTurn = 0;
        }

        checkWin();
    }

    public void checkWin() {
        int[][] wins = {
                {1,2,3}, {4,5,6}, {7,8,9},
                {1,4,7}, {2,5,8}, {3,6,9},
                {1,5,9}, {3,5,7}
        };

        for (int[] w : wins) {
            if (p1Moves.contains(w[0]) && p1Moves.contains(w[1]) && p1Moves.contains(w[2])) {
                showWin(player1Name + " Wins!");
                return;
            }
            if (p2Moves.contains(w[0]) && p2Moves.contains(w[1]) && p2Moves.contains(w[2])) {
                showWin(player2Name + " Wins!");
                return;
            }
        }

        if (p1Moves.size() + p2Moves.size() == 9) {
            showWin("It's a Draw!");
        }
    }

