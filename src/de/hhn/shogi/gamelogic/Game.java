package de.hhn.shogi.gamelogic;

import de.hhn.shogi.frontend.Window;
import de.hhn.shogi.gamelogic.util.BoardSide;

public class Game {
    public Board board;
    public Player bottomPlayer;
    public Player topPlayer;

    public Game(BoardSide bottomSide, Window window) {
        board = new Board(bottomSide, window);
        bottomPlayer = new Player(bottomSide);
        BoardSide otherSide = bottomSide == BoardSide.SENTE ? BoardSide.GOTE : BoardSide.SENTE;
        topPlayer = new Player(otherSide);
    }
}
