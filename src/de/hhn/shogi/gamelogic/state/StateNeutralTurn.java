package de.hhn.shogi.gamelogic.state;

import de.hhn.shogi.gamelogic.util.BoardSide;
import de.hhn.shogi.gamelogic.util.PieceType;
import de.hhn.shogi.gamelogic.util.Vec2;

public class StateNeutralTurn extends PlayerTurnState {

    public StateNeutralTurn(BoardSide side) {
        super(side);
    }

    @Override
    public void fieldClick(Vec2 pos) {

    }

    @Override
    public void handClick(PieceType type) {

    }
}
