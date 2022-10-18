package de.hhn.shogi.frontend;

import de.hhn.shogi.gamelogic.Piece;
import de.hhn.shogi.gamelogic.util.Vec2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Window extends JFrame {
    JLabel title = new JLabel();
    JLabel hand = new JLabel();
    public static final int BOARD_SIZE = 750;
    List<DisplayPiece> displayPieces = new ArrayList<>();

    public Window() {
        //window settings
        setTitle("Shogi");
        setSize(1000, 1000);
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(null);
        getContentPane().setBackground(new Color(50, 40, 50));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //title text
        title.setText("Shogi");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial Black", Font.PLAIN, 50));
        title.setHorizontalAlignment(JLabel.CENTER);
        getContentPane().add(title);

        //hand rect
        hand.setBackground(new Color(55, 45, 55));
        hand.setBounds(50, 50, BOARD_SIZE / 2, BOARD_SIZE);

        getContentPane().add(hand);
    }

    public void paint(Graphics g) {
        super.paint(g);
        //updated every time the window is resized
        title.setBounds(0, 0, getWidth(), 80);

        ImageIcon ic = new ImageIcon("src/de/hhn/shogi/frontend/images/board.png");
        g.drawImage(ic.getImage(), (getWidth() / 2) - (BOARD_SIZE / 2), (getHeight() / 2) - (BOARD_SIZE / 2), ic.getImageObserver());

        for (DisplayPiece d : displayPieces) {
            d.draw(g, getWidth(), getHeight());
        }
    }

    public void drawPieces(HashMap<Vec2, Piece> pieces) {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                Piece piece = pieces.get(new Vec2(x, y));
                if (piece != null) {
                    DisplayPiece d = new DisplayPiece(x, y);
                    displayPieces.add(d);
                    getContentPane().add(d);
                }
            }
        }
    }
}
