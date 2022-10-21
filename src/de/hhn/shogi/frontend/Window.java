package de.hhn.shogi.frontend;

import de.hhn.shogi.gamelogic.Board;
import de.hhn.shogi.gamelogic.Hand;
import de.hhn.shogi.gamelogic.Piece;
import de.hhn.shogi.gamelogic.Player;
import de.hhn.shogi.gamelogic.util.BoardSide;
import de.hhn.shogi.gamelogic.util.Vec2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Window extends JFrame {
    JLabel title = new JLabel();
    public static final int BOARD_SIZE = 750;
    Map<Vec2, FieldButton> fieldButtons = new HashMap<>();
    Board board;
    BoardSide bottom;

    public Window(Board board, Player player) {
        this.board = board;
        this.bottom = player.getSide();
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

        //board
        new DisplayBoard(this);

        //hand
        new DisplayHand(this, player.getHand());


        //makes a FieldButton for every position
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                Vec2 pos = new Vec2(x, y);
                FieldButton button;
                if (board.occupied(pos)) {
                    button = new FieldButton(pos, board.getPiece(pos), this);
                } else {
                    button = new FieldButton(pos, null, this);
                }
                fieldButtons.put(pos, button);
            }
        }
    }


    public void paint(Graphics g) {
        super.paint(g);
        //updated every time the window is resized
        title.setBounds(0, 0, getWidth(), 80);
    }
}
