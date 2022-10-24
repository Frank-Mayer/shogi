package de.hhn.shogi.gamelogic;

import de.hhn.shogi.gamelogic.util.BoardSide;

public class Player {
  private final Hand hand = new Hand();
  private final BoardSide side;

  public Player(BoardSide side) {
    this.side = side;
  }

  public Hand getHand() {
    return this.hand;
  }

  public BoardSide getSide() {
    return this.side;
  }
}
