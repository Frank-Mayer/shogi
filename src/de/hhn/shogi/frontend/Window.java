package de.hhn.shogi.frontend;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    JLabel title = new JLabel();
    JLabel hand = new JLabel();
    int boardSize = 750;

    public Window() {
        //window settings
        setTitle("Shogi");
        setSize(1000, 1000);
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(null);
        getContentPane().setBackground(new Color(50, 40, 50));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //title text
        title.setText("Shogi");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial Black", Font.PLAIN, 50));
        title.setHorizontalAlignment(JLabel.CENTER);
        getContentPane().add(title);

        //hand rect
        hand.setBackground(new Color(55, 45, 55));
        hand.setBounds(50 , 50, boardSize/2, boardSize);

        getContentPane().add(hand);
    }

    public void paint(Graphics g) {
        super.paint(g);
        //updated every time the window is resized
        title.setBounds(0, 0, getWidth(), 80);


        ImageIcon ic = new ImageIcon("src/de/hhn/shogi/frontend/images/board.png");
        g.drawImage(ic.getImage(), (getWidth() / 2) - (boardSize / 2), (getHeight() / 2) - (boardSize / 2), ic.getImageObserver());
    }
}
