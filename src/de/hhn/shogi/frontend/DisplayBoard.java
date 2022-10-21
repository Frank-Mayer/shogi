package de.hhn.shogi.frontend;

import javax.swing.*;
import java.awt.*;

import static de.hhn.shogi.frontend.Window.BOARD_SIZE;

public class DisplayBoard extends JPanel {
    Window window;

    public DisplayBoard(Window w) {
        window = w;
        setBounds(window.getWidth() / 2 - BOARD_SIZE / 2, window.getHeight() / 2 - BOARD_SIZE / 2, BOARD_SIZE, BOARD_SIZE);
        window.add(this, 1);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        setBounds(window.getWidth() / 2 - BOARD_SIZE / 2, window.getHeight() / 2 - BOARD_SIZE / 2, BOARD_SIZE, BOARD_SIZE);
        ImageIcon ic = new ImageIcon("src/de/hhn/shogi/frontend/images/board.png");
        g.drawImage(ic.getImage(), 0, 0, null);
    }
}
