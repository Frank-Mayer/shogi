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

    public FieldButton(Vec2 pos, Piece piece) {
        this.pos = pos;
        this.piece = piece;
    }


    public void draw(Graphics g, int width, int height) {
        // calculate position on the screen from shogi coordinates
        int translatedX = pos.getX() * 75 + (width / 2 - BOARD_SIZE / 2) + 37;
        int translatedY = (8 - pos.getY()) * 75 + (height / 2 - BOARD_SIZE / 2) + 37;

        // if there is a piece on the tile
        if (piece != null) {

            //select image based on PieceType
            String imgName;
            switch (piece.getType()) {
                default -> imgName = "TurmV2.png";
                case BISHOP -> imgName = "bishop.png";
                case KING -> imgName = "king.png";
                case ROOK -> imgName = "rook.png";
            }


            //draw image (rotated if top side)
            ImageIcon ic = new ImageIcon("src/de/hhn/shogi/frontend/images/" + imgName);

            BufferedImage img = new BufferedImage(75, 75, BufferedImage.TYPE_INT_ARGB);
            Graphics2D imgGraphics = img.createGraphics();
            imgGraphics.setColor(new Color(0, 0, 0, 50));
            imgGraphics.fillRect(0, 0, 75, 75);
            imgGraphics.drawImage(ic.getImage(), 0 ,0, ic.getImageObserver());

            BufferedImage rotated = piece.getSide() == BoardSide.SENTE ? rotate(img) : img;
            g.drawImage(rotated, translatedX, translatedY, ic.getImageObserver());
        }
    }

    //rotate a buffered image
    public static BufferedImage rotate(BufferedImage bimg) {
        double sin = Math.abs(Math.sin(Math.PI)),
                cos = Math.abs(Math.cos(Math.PI));
        int w = bimg.getWidth();
        int h = bimg.getHeight();
        int newWidth = (int) Math.floor(w*cos + h*sin),
                newHeight = (int) Math.floor(h*cos + w*sin);
        BufferedImage rotated = new BufferedImage(newWidth, newHeight, bimg.getType());
        Graphics2D graphic = rotated.createGraphics();
        graphic.translate((newWidth-w)/2, (newHeight-h)/2);
        graphic.rotate(Math.PI, w/2, h/2);
        graphic.drawRenderedImage(bimg, null);
        graphic.dispose();
        return rotated;
    }
}
