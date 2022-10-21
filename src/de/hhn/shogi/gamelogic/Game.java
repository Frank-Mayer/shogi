package de.hhn.shogi.gamelogic;

import de.hhn.shogi.frontend.Window;
import de.hhn.shogi.gamelogic.state.StateManager;
import de.hhn.shogi.gamelogic.util.BoardSide;

public class Game {
    private Board board;
    private Player bottomPlayer;
    private Player topPlayer;
    private StateManager state;

    public static Game ACTIVE_GAME;

    public Game(BoardSide bottomSide) {
        board = new Board(bottomSide);
        bottomPlayer = new Player(bottomSide);
        BoardSide otherSide = bottomSide == BoardSide.SENTE ? BoardSide.GOTE : BoardSide.SENTE;
        topPlayer = new Player(otherSide);
        state = new StateManager();
    }

    public Board getBoard() {
        return board;
    }

    public Player getBottomPlayer() {
        return bottomPlayer;
    }

    public StateManager getState() {
        return state;
    }

    public Player getTopPlayer() {
        return topPlayer;
    }
}
