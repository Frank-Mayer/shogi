package de.hhn.shogi.frontend;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    JLabel text = new JLabel();
    int boardSize = 500;

    public Window() {
        setTitle("Shogi");
        setSize(1000, 1000);
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(null);
        getContentPane().setBackground(new Color(226, 170, 102));

        text.setForeground(Color.WHITE);
        text.setFont(new Font("Arial Black", Font.PLAIN, 15));
        add(text);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void paint(Graphics g) {
        super.paint(g);
        text.setBounds(10, 0, 500, 50);
        text.setText("width: " + getWidth() + ", height: " + getHeight());


        g.drawLine(getWidth() / 2 - boardSize / 2, getHeight() / 2 - boardSize / 2, getWidth() / 2 + boardSize / 2, getHeight() / 2 - boardSize / 2);
        g.drawLine(getWidth() / 2 - boardSize / 2, getHeight() / 2 + boardSize / 2, getWidth() / 2 + boardSize / 2, getHeight() / 2 + boardSize / 2);
        g.drawLine(getWidth() / 2 - boardSize / 2, getHeight() / 2 - boardSize / 2, getWidth() / 2 - boardSize / 2, getHeight() / 2 + boardSize / 2);
        g.drawLine(getWidth() / 2 + boardSize / 2, getHeight() / 2 - boardSize / 2, getWidth() / 2 + boardSize / 2, getHeight() / 2 + boardSize / 2);


    }
}
