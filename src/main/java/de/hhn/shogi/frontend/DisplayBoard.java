package de.hhn.shogi.frontend;

import de.hhn.shogi.frontend.images.ImagePath;

import javax.swing.*;
import java.awt.Window;
import java.awt.*;

import static de.hhn.shogi.frontend.Window.BOARD_SIZE;

public class DisplayBoard extends JPanel {
    Window window;

    public DisplayBoard(Window w) {
        this.window = w;
        this.setBounds(this.window.getWidth() / 2 - BOARD_SIZE / 2, this.window.getHeight() / 2 - BOARD_SIZE / 2, BOARD_SIZE, BOARD_SIZE);
        this.window.add(this, 1);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.setBounds(this.window.getWidth() / 2 - BOARD_SIZE / 2, this.window.getHeight() / 2 - BOARD_SIZE / 2, BOARD_SIZE, BOARD_SIZE);
        ImageIcon ic = new ImageIcon(ImagePath.getPath("board.png"));
        g.drawImage(ic.getImage(), 0, 0, null);
    }
}
