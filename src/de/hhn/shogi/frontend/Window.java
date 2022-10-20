package de.hhn.shogi.frontend;

import de.hhn.shogi.gamelogic.Board;
import de.hhn.shogi.gamelogic.Piece;
import de.hhn.shogi.gamelogic.util.Vec2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Window extends JFrame {
    JLabel title = new JLabel();
    //JLabel hand = new JLabel();
    public static final int BOARD_SIZE = 750;
    Map<Vec2, FieldButton> fieldButtons = new HashMap<>();
    Board board;

    public Window(Board board) {
        this.board = board;
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
//        hand.setBackground(new Color(55, 45, 55));
//        hand.setBounds(50, 50, BOARD_SIZE / 2, BOARD_SIZE);
//        getContentPane().add(hand);


        //makes a FieldButton for every position
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                Vec2 pos = new Vec2(x, y);
                FieldButton button;
                if (board.occupied(pos)) {
                    button = new FieldButton(pos, board.getPiece(pos));
                } else {
                    button = new FieldButton(pos, null);
                }
                fieldButtons.put(pos, button);
                getContentPane().add(button);
            }
        }
    }


    public void paint(Graphics g) {
        super.paint(g);
        //updated every time the window is resized
        title.setBounds(0, 0, getWidth(), 80);

        ImageIcon ic = new ImageIcon("src/de/hhn/shogi/frontend/images/board.png");
        g.drawImage(ic.getImage(), (getWidth() / 2) - (BOARD_SIZE / 2), (getHeight() / 2) - (BOARD_SIZE / 2), ic.getImageObserver());

        //display all pieces
        for (FieldButton d : fieldButtons.values()) {
            d.draw(g, getWidth(), getHeight());
        }
    }
}
