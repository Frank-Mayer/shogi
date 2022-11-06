package de.hhn.shogi.gamelogic.state;

import de.hhn.shogi.gamelogic.Board;
import de.hhn.shogi.gamelogic.Piece;
import de.hhn.shogi.gamelogic.util.BoardSide;
import de.hhn.shogi.gamelogic.util.Vec2;

public class StateManager {
    private GameState state;

    public StateManager() {
        this.state = new StateNeutralTurn(Board.HANDICAPPED_SIDE);
    }

    public StateManager(BoardSide startingSide) {
        this.state = new StateNeutralTurn(startingSide);
    }

    public void fieldClick(Vec2 pos) {
        this.state.fieldClick(pos);
    }

    public void handClick(Piece piece) {
        this.state.handClick(piece);
    }

    public void changeState(GameState state) {
        this.state = state;
    }
}
