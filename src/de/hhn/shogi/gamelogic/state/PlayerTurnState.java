package de.hhn.shogi.gamelogic.state;

import de.hhn.shogi.gamelogic.util.BoardSide;

public abstract class PlayerTurnState extends GameState {
  private final BoardSide side;

  public PlayerTurnState(BoardSide side) {
    super();
    this.side = side;
  }

  public BoardSide getSide() {
    return this.side;
  }
}
