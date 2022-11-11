package de.hhn.shogi.frontend;

import de.hhn.shogi.frontend.images.ImagePath;
import de.hhn.shogi.gamelogic.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConfirmationWindow extends JFrame {
    Window window;
    public ConfirmationWindow(Window w, Piece piece) {
        this.window = w;

        //window settings
        this.setTitle("Confirmation");
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(60, 50, 60));
        this.setSize(400, 200);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocation(window.getX() + window.getWidth() / 2 - this.getWidth() / 2, window.getY() + window.getHeight() / 2 - this.getHeight() / 2);
        this.setAlwaysOnTop(true);
        this.setIconImage(new ImageIcon(ImagePath.getPath(ImagePath.getPieceImage(piece.getType()))).getImage());
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                no();
            }
        });

        //text
        JLabel text = new JLabel("Do you want to promote this " + piece.getType().toString() + "?");
        text.setBounds(10, 15, 360, 50);
        text.setFont(new Font("Arial Black", Font.PLAIN, 14));
        text.setForeground(Color.WHITE);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(text);

        //buttons
        JButton yesButton = new JButton("Yes");
        JButton noButton = new JButton("No");
        makeButton(yesButton, e -> {
            yes();
        }, 50);
        makeButton(noButton, e -> {
            no();
        }, 240);
    }

    public void makeButton(JButton button, ActionListener actionListener, int xPos) {
        button.setBounds(xPos, 80, 100, 50);
        button.setBackground(new Color(50, 40, 50));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial Black", Font.PLAIN, 20));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(new Color(40, 30, 40));
            }
            public void mouseExited(MouseEvent evt) {
                button.setBackground(new Color(50, 40, 50));
            }
        });
        button.addActionListener(actionListener);
        this.add(button);
    }

    public void yes() {
        this.dispose();
        window.setEnabled(true);
        window.requestFocus();
        System.out.println("yes");
    }

    public void no() {
        this.dispose();
        window.setEnabled(true);
        window.requestFocus();
        System.out.println("no");
    }
}