package de.hhn.shogi.gamelogic.state;

import de.hhn.shogi.gamelogic.util.PieceType;
import de.hhn.shogi.gamelogic.util.Vec2;

public abstract class GameState {

  public abstract void fieldClick(Vec2 pos);

  public abstract void handClick(PieceType type);
}
