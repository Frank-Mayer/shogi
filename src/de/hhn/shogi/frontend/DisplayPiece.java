package de.hhn.shogi.frontend;

import javax.swing.*;
import java.awt.*;

import static de.hhn.shogi.frontend.Window.BOARD_SIZE;

public class DisplayPiece extends JPanel {
    private int x;
    private int y;

    public DisplayPiece(int x, int y){
        this.x = x;
        this.y = y;
    }


    public void draw(Graphics g, int width, int height) {
        g.setColor(Color.BLACK);
        g.fillRect(x * 75 + (width / 2 - BOARD_SIZE / 2) + 37,y * 75 + (height / 2 - BOARD_SIZE / 2) + 37,75,75);
    }
}
