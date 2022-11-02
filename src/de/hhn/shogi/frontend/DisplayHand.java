package de.hhn.shogi.frontend;

import de.hhn.shogi.gamelogic.Hand;

import javax.swing.*;
import java.awt.*;

import static de.hhn.shogi.frontend.Window.BOARD_SIZE;

public class DisplayHand extends JPanel {
    Window window;
    Hand hand;
    private final int margin = 20;
    private final int w = 4 * 75 + 2 * margin;
    private final int h = 5 * 75 + 2 * margin;
    private int xPos;
    private int yPos;
    private FieldButton[] buttons = new FieldButton[20];

    public DisplayHand(Window window, Hand hand) {
        this.window = window;
        this.hand = hand;
        setBounds(0, 0, w, h);
        this.window.getContentPane().add(this, 2);
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new FieldButton(0, 0, null, window);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        xPos = window.getWidth() / 2 + BOARD_SIZE / 2 + 25;
        yPos = window.getHeight() / 2 + BOARD_SIZE / 2 - h;
        setBounds(xPos, yPos, w, h);
        g.setColor(new Color(60, 50, 60));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        for (int i = 0; i < buttons.length; i++) {
            int x = xPos + margin + (i % 4) * 75;
            int y = yPos + margin + (i / 4) * 75;
            buttons[i].setTranslated(x, y);
        }
    }

    public void update() {
        for (int i = 0; i < hand.getHandSize(); i++) {
            buttons[i].setPiece(hand.getPieces().get(i));
        }
    }
}
