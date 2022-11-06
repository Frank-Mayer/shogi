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

import static de.hhn.shogi.gamelogic.Game.ACTIVE_GAME;

public class Window extends JFrame {
    public static final int BOARD_SIZE = 750;
    JLabel title = new JLabel();
    Map<Vec2, FieldButton> fieldButtons = new HashMap<>();
    Board board;
    de.hhn.shogi.frontend.DisplayHand hand1;
    de.hhn.shogi.frontend.DisplayHand hand2;
    BoardSide bottom;

    public Window(Board board, Player player) {
        this.board = board;
        this.bottom = player.getSide();
        //window settings
        this.setTitle("Shogi");
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setSize(1600, 1000);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(50, 40, 50));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //title text
        this.title.setText("Shogi");
        this.title.setForeground(Color.WHITE);
        this.title.setFont(new Font("Arial Black", Font.PLAIN, 50));
        this.title.setHorizontalAlignment(JLabel.CENTER);
        this.getContentPane().add(this.title);

        //board
        new de.hhn.shogi.frontend.DisplayBoard(this);

        //hand
        this.hand2 = new DisplayHand(this, ACTIVE_GAME.getTopPlayer());
        this.hand1 = new DisplayHand(this, ACTIVE_GAME.getBottomPlayer());

        //makes a FieldButton for every position
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                Vec2 pos = new Vec2(x, y);
                FieldButton button = new FieldButton(pos, board.getPiece(pos), this);
                this.fieldButtons.put(pos, button);
            }
        }
    }

    //updated every time the window is resized
    public void paint(Graphics g) {
        super.paint(g);
        this.title.setBounds(0, 0, this.getWidth(), 80);
    }

    //change the displayed piece on a field
    public void setField(Vec2 vector, Piece piece) {
        this.fieldButtons.get(vector).setPiece(piece);
    }

    //adds "legal move" icons on all positions in the List
    public void displayPossibleMoves(ArrayList<Vec2> positions) {
        for (Vec2 pos : positions) {
            de.hhn.shogi.frontend.FieldButton b = this.fieldButtons.get(pos);
            b.legalMove(true);
        }
    }

    //removes all "legal move" icons
    public void removeIcons() {
        for (de.hhn.shogi.frontend.FieldButton b : this.fieldButtons.values()) {
            b.legalMove(false);
        }
    }

    public void updateHand(Player p) {
        (p.equals(ACTIVE_GAME.getBottomPlayer()) ? this.hand1 : this.hand2).update();
    }
}
