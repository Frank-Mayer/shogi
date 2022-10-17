package de.hhn.shogi.frontend;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    JLabel text = new JLabel();
    JLabel boardImg = new JLabel();
    int boardSize = 800;

    public Window() {
        //window settings
        setTitle("Shogi");
        setSize(1000, 1000);
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(null);
        getContentPane().setBackground(new Color(92, 31, 31));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //test text
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Arial Black", Font.PLAIN, 15));
        getContentPane().add(text);

        //adds board
//        boardImg.setIcon(new ImageIcon("src/de/hhn/shogi/frontend/board.png"));
//        add(boardImg);
        getContentPane().add(boardImg);
    }

    public void paint(Graphics g) {
        super.paint(g);
        //updated every time the window is resized

        text.setBounds(10, 0, 500, 50);
        text.setText("width: " + getWidth() + ", height: " + getHeight());
//        boardImg.setBounds(getWidth() / 2 - boardSize / 2, getHeight() / 2 - boardSize / 2, boardSize, boardSize);

        ImageIcon ic = new ImageIcon("src/de/hhn/shogi/frontend/board.png");
        g.drawImage(ic.getImage(),(getWidth()/2)-(400),(getHeight()/2)-(400),ic.getImageObserver());


        g.drawLine(getWidth() / 2 - boardSize / 2, getHeight() / 2 - boardSize / 2, getWidth() / 2 + boardSize / 2, getHeight() / 2 - boardSize / 2);
        g.drawLine(getWidth() / 2 - boardSize / 2, getHeight() / 2 + boardSize / 2, getWidth() / 2 + boardSize / 2, getHeight() / 2 + boardSize / 2);
        g.drawLine(getWidth() / 2 - boardSize / 2, getHeight() / 2 - boardSize / 2, getWidth() / 2 - boardSize / 2, getHeight() / 2 + boardSize / 2);
        g.drawLine(getWidth() / 2 + boardSize / 2, getHeight() / 2 - boardSize / 2, getWidth() / 2 + boardSize / 2, getHeight() / 2 + boardSize / 2);


    }
}
