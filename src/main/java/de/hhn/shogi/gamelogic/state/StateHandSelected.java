package de.hhn.shogi.gamelogic.state;

import de.hhn.shogi.gamelogic.Piece;
import de.hhn.shogi.gamelogic.util.BoardSide;
import de.hhn.shogi.gamelogic.util.Vec2;

public class StateHandSelected extends PlayerTurnState {
    private final Piece piece;

    public StateHandSelected(BoardSide side, Piece piece) {
        super(side);
        this.piece = piece;
    }

    @Override
    public void fieldClick(Vec2 pos) {

    }

    @Override
    public void handClick(Piece type) {

    }
}
