package de.hhn.shogi.gamelogic.state;

import de.hhn.shogi.gamelogic.util.BoardSide;
import de.hhn.shogi.gamelogic.util.Vec2;

public abstract class PlayerTurnState extends GameState {
    private final BoardSide side;

    public PlayerTurnState(BoardSide side) {
        this.side = side;
    }

    public BoardSide getSide() {
        return side;
    }
}
