package de.hhn.shogi.gamelogic.state;

import de.hhn.shogi.gamelogic.util.BoardSide;
import de.hhn.shogi.gamelogic.util.PieceType;
import de.hhn.shogi.gamelogic.util.Vec2;

public class StatePieceSelected extends PlayerTurnState {
    private Vec2 selectedPos;

    public StatePieceSelected(BoardSide side, Vec2 pos) {
        super(side);
        selectedPos = pos;
    }

    @Override
    public void fieldClick(Vec2 pos) {

    }

    @Override
    public void handClick(PieceType type) {

    }
}
