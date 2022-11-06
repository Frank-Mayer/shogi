package de.hhn.shogi.frontend;

import de.hhn.shogi.gamelogic.Hand;
import de.hhn.shogi.gamelogic.Player;

import javax.swing.*;
import java.awt.*;

import static de.hhn.shogi.frontend.Window.BOARD_SIZE;
import static de.hhn.shogi.gamelogic.Game.ACTIVE_GAME;

public class DisplayHand extends JPanel {
    private final int margin = 20;
    private final int w = 4 * 75 + 2 * this.margin;
    private final int h = 5 * 75 + 2 * this.margin;
    Window window;
    Hand hand;
    boolean isBottomSide;
    private int xPos;
    private int yPos;
    private final FieldButton[] buttons = new FieldButton[20];

    public DisplayHand(Window window, Player player) {
        this.window = window;
        this.hand = player.getHand();
        this.isBottomSide = player == ACTIVE_GAME.getBottomPlayer();
        this.xPos = 0;
        this.yPos = 0;
        this.setBounds(this.xPos, this.yPos, this.w, this.h);
        this.window.getContentPane().add(this, 2);
        for (int i = 0; i < this.buttons.length; i++) {
            this.buttons[i] = new FieldButton(this.xPos, this.yPos, null, window);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (this.isBottomSide) {
            this.xPos = this.window.getWidth() / 2 + BOARD_SIZE / 2 + this.margin;
            this.yPos = this.window.getHeight() / 2 + BOARD_SIZE / 2 - this.h;
        } else {
            this.xPos = this.window.getWidth() / 2 - BOARD_SIZE / 2 - this.margin - this.w;
            this.yPos = this.window.getHeight() / 2 - BOARD_SIZE / 2;
        }
        this.setBounds(this.xPos, this.yPos, this.w, this.h);
        g.setColor(new Color(60, 50, 60));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        if (this.isBottomSide) {
            for (int i = 0; i < this.buttons.length; i++) {
                int x = this.xPos + this.margin + i % 4 * 75;
                int y = this.yPos + this.margin + i / 4 * 75;
                this.buttons[i].setTranslated(x, y);
            }
        } else {
            for (int i = 0; i < this.buttons.length; i++) {
                int x = this.xPos + this.margin + (this.buttons.length - 1 - i) % 4 * 75;
                int y = this.yPos + this.margin + (this.buttons.length - 1 - i) / 4 * 75;
                this.buttons[i].setTranslated(x, y);
            }
        }
    }

    public void update() {
        for (int i = 0; i < this.hand.getHandSize(); i++) {
            this.buttons[i].setPiece(this.hand.getPieces().get(i));
        }
    }
}
