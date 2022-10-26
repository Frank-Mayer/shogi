package de.hhn.shogi.frontend;

import de.hhn.shogi.gamelogic.Hand;

import javax.swing.*;
import java.awt.*;

import static de.hhn.shogi.frontend.Window.BOARD_SIZE;

public class DisplayHand extends JPanel {
    Window window;

    public DisplayHand(Window w, Hand hand) {
        super();
        this.window = w;
        this.setBounds(25, this.window.getHeight() / 2 - BOARD_SIZE / 2, 4 * 75, 5 * 75);
        this.window.getContentPane().add(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //  setBounds(25, window.getHeight() / 2 - BOARD_SIZE / 2, 4 * 75, 5 * 75);
        g.setColor(new Color(60, 50, 60));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}
