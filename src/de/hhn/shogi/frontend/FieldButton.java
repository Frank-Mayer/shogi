package de.hhn.shogi.frontend;

import de.hhn.shogi.gamelogic.Piece;
import de.hhn.shogi.gamelogic.state.StateManager;
import de.hhn.shogi.gamelogic.util.Vec2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static de.hhn.shogi.frontend.Window.BOARD_SIZE;

public class FieldButton extends JButton {
    private final Vec2 pos;
    private Piece piece;
    private int translatedX, translatedY;
    private boolean hovering = false;
    Window window;

    public FieldButton(Vec2 pos, Piece piece, Window w) {
        this.pos = pos;
        this.piece = piece;
        window = w;

        //init button
        translatedX = limit(pos.getX() * 75 + (window.getWidth() / 2 - BOARD_SIZE / 2) + 37, 0, window.getWidth());
        translatedY = limit((8 - pos.getY()) * 75 + (window.getHeight() / 2 - BOARD_SIZE / 2) + 37, 0, window.getHeight());
        setContentAreaFilled(false);
        setBounds(translatedX, translatedY, 75, 75);
        setBorderPainted(false);
        window.add(this, 0);

        //paint border if hovered over
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hovering = true;
            }
            @Override
            public void mouseExited(MouseEvent e) {
                hovering = false;
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if(hovering) {
                    System.out.println(pos);
                    StateManager.fieldClick(pos);
                }
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // calculate position on the screen from shogi coordinates
        translatedX = limit(pos.getX() * 75 + (window.getWidth() / 2 - BOARD_SIZE / 2) + 37, 0, window.getWidth());
        translatedY = limit((8 - pos.getY()) * 75 + (window.getHeight() / 2 - BOARD_SIZE / 2) + 37, 0, window.getHeight());
        this.setBounds(translatedX, translatedY, 75, 75);

        //paint piece if Piece is not null
        if (piece != null) {
            setIcon(getImg(getPathName(), hovering));
        } else {
           // setIcon(getHoverEffect());
        }
    }

    private ImageIcon getHoverEffect() {
        if (hovering) {
            BufferedImage img = new BufferedImage(75, 75, BufferedImage.TYPE_INT_ARGB);
            Graphics2D imgGraphics = img.createGraphics();
            imgGraphics.setColor(new Color(0, 0, 0, 50));
            imgGraphics.fillRect(0, 0, 75, 75);
            return new ImageIcon(img);
        } else {
            return null;
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

    //returns formatted image from path name
    private ImageIcon getImg(String name, boolean hover) {
        ImageIcon pieceImg = new ImageIcon("src/de/hhn/shogi/frontend/images/" + name);
        ImageIcon plusImg = new ImageIcon("src/de/hhn/shogi/frontend/images/plus.png");
        BufferedImage img = new BufferedImage(75, 75, BufferedImage.TYPE_INT_ARGB);
        Graphics2D imgGraphics = img.createGraphics();
        if (hover) {
            imgGraphics.setColor(new Color(0, 0, 0, 50));
            imgGraphics.fillRect(0, 0, 75, 75);
        }
        imgGraphics.drawImage(pieceImg.getImage(), 0, 0, pieceImg.getImageObserver());
        if (piece.isPromoted()) {
            imgGraphics.drawImage(plusImg.getImage(), 0, 0, plusImg.getImageObserver());
        }
        BufferedImage rotated = piece.getSide() == window.bottom ? img : rotate(img);
        return new ImageIcon(rotated);
    }

    //select image based on PieceType
    public String getPathName() {
        return switch (piece.getBaseType()) {
            default -> "TurmV2.png";
            case BISHOP, HORSE -> "bishop.png";
            case KING -> "king.png";
            case ROOK, DRAGON -> "rook.png";
        };
    }

    private int limit(int value, int min, int max) {
        return Math.min(Math.max(value, min), max);
    }
}
