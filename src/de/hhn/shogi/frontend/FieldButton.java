package de.hhn.shogi.frontend;

import de.hhn.shogi.gamelogic.Piece;
import de.hhn.shogi.gamelogic.state.StateManager;
import de.hhn.shogi.gamelogic.util.Vec2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static de.hhn.shogi.frontend.Window.BOARD_SIZE;

public class FieldButton extends JButton {
    private final Vec2 pos;
    private Piece piece;
    private int translatedX, translatedY;
    private boolean hovering = false, mousePressed = false;
    private boolean legalMoveIcon = false;
    private final Color legalMoveColor = new Color(145, 100, 145);
    private final int legalMoveBorderWidth = 5;
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
                mousePressed = false;
            }

            @Override
            public void mousePressed(MouseEvent e) {
                mousePressed = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (mousePressed) {
                    System.out.println(pos);
                    // new StateManager().fieldClick(pos);
                }
                mousePressed = false;
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
            setIcon(getImg(getPathName()));
        } else {
            setIcon(getEmptyEffect());
        }
    }

    //returns the background of an empty field
    private ImageIcon getEmptyEffect() {
        BufferedImage img = new BufferedImage(75, 75, BufferedImage.TYPE_INT_ARGB);
        Graphics2D imgGraphics = img.createGraphics();
        if (mousePressed) {
            imgGraphics.setColor(new Color(0, 0, 0, 60));
            imgGraphics.fillRect(0, 0, 75, 75);
        } else if (hovering) {
            imgGraphics.setColor(new Color(0, 0, 0, 30));
            imgGraphics.fillRect(0, 0, 75, 75);
        }
        if (legalMoveIcon) {
            imgGraphics.setColor(legalMoveColor);
            imgGraphics.fillOval(25, 25, 25, 25);
        }
        return new ImageIcon(img);
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
    private ImageIcon getImg(String name) {
        ImageIcon pieceImg = new ImageIcon("src/de/hhn/shogi/frontend/images/" + name);
        ImageIcon plusImg = new ImageIcon("src/de/hhn/shogi/frontend/images/plus.png");
        BufferedImage img = new BufferedImage(75, 75, BufferedImage.TYPE_INT_ARGB);
        //create graphics
        Graphics2D imgGraphics = img.createGraphics();
        //draw hover/click effect
        imgGraphics.drawImage(getEmptyEffect().getImage(), 0, 0, null);
        //draw piece
        imgGraphics.drawImage(pieceImg.getImage(), 0, 0, null);
        //add plus icon
        if (piece.isPromoted()) {
            imgGraphics.drawImage(plusImg.getImage(), 0, 0, null);
        }
        //rotate
        BufferedImage rotated = piece.getSide() == window.bottom ? img : rotate(img);
        //add legalMoveIcon
        if (legalMoveIcon) {
            Graphics2D rotatedGraphics = rotated.createGraphics();
            rotatedGraphics.setColor(legalMoveColor);
            rotatedGraphics.fillRect(2, 2, 72, legalMoveBorderWidth);
            rotatedGraphics.fillRect(2, 2, legalMoveBorderWidth, 72);
            rotatedGraphics.fillRect(74 - legalMoveBorderWidth, 2, legalMoveBorderWidth, 72);
            rotatedGraphics.fillRect(2, 74 - legalMoveBorderWidth, 72, legalMoveBorderWidth);
        }
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

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void legalMove(boolean addOrRemove) {
        legalMoveIcon = addOrRemove;
    }
}
