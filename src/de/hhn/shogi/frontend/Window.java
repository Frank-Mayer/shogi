package de.hhn.shogi.frontend;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public Window() {
        setTitle("Shogi");
        setSize(1000, 1000);
        setLayout(null);
        getContentPane().setBackground(new Color(92, 31, 31));
        JLabel text = new JLabel("Hello World");
        text.setBounds(20, -25, 760, 200);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Arial Black", Font.PLAIN, 25));
        add(text);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
