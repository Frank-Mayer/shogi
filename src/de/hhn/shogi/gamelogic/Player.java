package de.hhn.shogi.gamelogic;

import de.hhn.shogi.gamelogic.util.BoardSide;

public class Player {
    private Hand hand = new Hand();
    private BoardSide side;

    public Player(BoardSide side) {
        this.side = side;
    }

    public Hand getHand() {
        return hand;
    }

    public BoardSide getSide() {
        return side;
    }
}
