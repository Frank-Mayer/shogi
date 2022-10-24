package de.hhn.shogi.frontend;

import de.hhn.shogi.gamelogic.Board;
import de.hhn.shogi.gamelogic.Piece;
import de.hhn.shogi.gamelogic.Player;
import de.hhn.shogi.gamelogic.util.BoardSide;
import de.hhn.shogi.gamelogic.util.Vec2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
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
        setExtendedState(MAXIMIZED_BOTH);
        setSize(1300, 1000);
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

    //updated every time the window is resized
    public void paint(Graphics g) {
        super.paint(g);
        title.setBounds(0, 0, getWidth(), 80);
    }

    //change the displayed piece on a field
    public void setField(Vec2 vector, Piece piece) {
        fieldButtons.get(vector).setPiece(piece);
    }

    //adds or removes "legal move icons" from all positions in the List (add = true, remove = false)
    public void displayPossibleMoves(ArrayList<Vec2> positions) {
        for (Vec2 pos : positions) {
            FieldButton b = fieldButtons.get(pos);
            b.legalMove(true);
        }
    }

    public void removeIcons() {
        for(FieldButton b : fieldButtons.values()){
            b.legalMove(false);
        }
    }
}
