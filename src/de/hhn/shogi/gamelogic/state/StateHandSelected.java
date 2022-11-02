package de.hhn.shogi.gamelogic.state;

import de.hhn.shogi.gamelogic.Piece;
import de.hhn.shogi.gamelogic.util.BoardSide;
import de.hhn.shogi.gamelogic.util.PieceType;
import de.hhn.shogi.gamelogic.util.Vec2;

public class StateHandSelected extends PlayerTurnState {
    private final PieceType type;

    public StateHandSelected(BoardSide side, PieceType type) {
        super(side);
        this.type = type;
    }

    @Override
    public void fieldClick(Vec2 pos) {

    }

    @Override
    public void handClick(Piece type) {

    }
}
