package de.hhn.shogi.frontend;

import de.hhn.shogi.gamelogic.Piece;
import de.hhn.shogi.gamelogic.util.BoardSide;
import de.hhn.shogi.gamelogic.util.PieceType;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static de.hhn.shogi.frontend.Window.BOARD_SIZE;

public class DisplayPiece extends JPanel {
    private int x;
    private int y;
    private PieceType type;
    private BoardSide side;

    public DisplayPiece(int x, int y, Piece piece) {
        this.x = x;
        this.y = y;
        type = piece.getType();
        side = piece.getSide();
    }


    public void draw(Graphics g, int width, int height) {
        g.setColor(new Color(0, 0, 0, 50));
        g.fillRect(x * 75 + (width / 2 - BOARD_SIZE / 2) + 37,(8 - y) * 75 + (height / 2 - BOARD_SIZE / 2) + 37,75,75);

        String imgName;

        //select image based on PieceType
        switch (type) {
            default -> imgName = "TurmV2.png";
            case BISHOP -> imgName = "bishop.png";
            case KING -> imgName = "king.png";
            case ROOK -> imgName = "rook.png";
        }


        //draw image (rotated if top side)
      Graphics2D g2d = (Graphics2D)g;
        if (side == BoardSide.SENTE) {
            ImageIcon ic = new ImageIcon("src/de/hhn/shogi/frontend/images/" + imgName);
            g2d.rotate(Math.PI);
            g2d.drawImage(ic.getImage(), x * 75 + (width / 2 - BOARD_SIZE / 2) + 37, (8 - y) * 75 + (height / 2 - BOARD_SIZE / 2) + 37 , ic.getImageObserver());
        }else{
            ImageIcon ic = new ImageIcon("src/de/hhn/shogi/frontend/images/" + imgName);
            g.drawImage(ic.getImage(), x * 75 + (width / 2 - BOARD_SIZE / 2) + 37, (8 - y) * 75 + (height / 2 - BOARD_SIZE / 2) + 37, ic.getImageObserver());
        }
    }
}
