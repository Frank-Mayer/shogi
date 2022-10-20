package de.hhn.shogi.frontend;

import de.hhn.shogi.gamelogic.Piece;
import de.hhn.shogi.gamelogic.util.BoardSide;
import de.hhn.shogi.gamelogic.util.PieceType;
import de.hhn.shogi.gamelogic.util.Vec2;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static de.hhn.shogi.frontend.Window.BOARD_SIZE;

public class FieldButton extends JButton {
    private final Vec2 pos;
    private Piece piece;
    Window window;

    public FieldButton(Vec2 pos, Piece piece, Window w) {
        this.pos = pos;
        this.piece = piece;
        window = w;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // calculate position on the screen from shogi coordinates
        int translatedX = pos.getX() * 75 + (window.getWidth() / 2 - BOARD_SIZE / 2) + 37;
        int translatedY = (8 - pos.getY()) * 75 + (window.getHeight() / 2 - BOARD_SIZE / 2) + 37;

        // if there is a piece on the tile
        if (piece != null) {

            //select image based on PieceType
            String imgName;
            switch (piece.getBaseType()) {
                default -> imgName = "TurmV2.png";
                case BISHOP, HORSE -> imgName = "bishop.png";
                case KING -> imgName = "king.png";
                case ROOK, DRAGON -> imgName = "rook.png";
            }


            //draw image (rotated if top side)
            ImageIcon pieceImg = new ImageIcon("src/de/hhn/shogi/frontend/images/" + imgName);
            ImageIcon plusImg = new ImageIcon("src/de/hhn/shogi/frontend/images/plus.png");


            BufferedImage img = new BufferedImage(75, 75, BufferedImage.TYPE_INT_ARGB);
            Graphics2D imgGraphics = img.createGraphics();
            imgGraphics.setColor(new Color(0, 0, 0, 50));
            imgGraphics.fillRect(0, 0, 75, 75);
            imgGraphics.drawImage(pieceImg.getImage(), 0, 0, pieceImg.getImageObserver());
            if (piece.isPromoted()) {
                imgGraphics.drawImage(plusImg.getImage(), 0, 0, plusImg.getImageObserver());
            }

            BufferedImage rotated = piece.getSide() == BoardSide.SENTE ? rotate(img) : img;
            g.drawImage(rotated, translatedX, translatedY, null);
        }
    }

    //rotate a buffered image 90°
    public static BufferedImage rotate(BufferedImage img) {
        BufferedImage rotated = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        Graphics2D graphic = rotated.createGraphics();
        graphic.rotate(Math.PI, img.getWidth() / 2, img.getHeight() / 2);
        graphic.drawRenderedImage(img, null);
        graphic.dispose();
        return rotated;
    }
}
