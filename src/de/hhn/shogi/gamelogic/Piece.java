package de.hhn.shogi.gamelogic;

import de.hhn.shogi.gamelogic.util.BoardSide;
import de.hhn.shogi.gamelogic.util.PieceType;

public class Piece {
  // Current type important for gameLogic
  private PieceType type;
  // Original type of the Piece. Important for frontend and handLogic
  private final PieceType baseType;
  // Is Piece in promoted state. Important for frontend
  private boolean promoted;
  // Side of Board
  private final BoardSide side;

  public Piece(PieceType type, BoardSide side) {
    this.type = this.baseType = type;
    this.side = side;
  }

  // Returns current Type for Logic
  public PieceType getType() {
    return this.type;
  }

  // Returns baseType used for UI and DemotionLogic
  public PieceType getBaseType() {
    return this.baseType;
  }

  // Returns true when the Piece is Promoted
  public boolean isPromoted() {
    return this.promoted;
  }

  // Returns which Side owns this Piece
  public BoardSide getSide() {
    return this.side;
  }

  //Tries to promote the Piece. Returns whether the promotion was done successfully.
  public boolean promote() {
    if (!this.canPromote()) {
        return false;
    }
      this.type = switch (this.baseType) {
        case ROOK -> PieceType.DRAGON;
      case BISHOP -> PieceType.HORSE;
        default -> PieceType.GOLD_GENERAL;
    };
      this.promoted = true;
    return true;
  }

  // Demotes the Piece. Should not be called typically
  public void demote() {
      this.type = this.baseType;
      this.promoted = false;
  }

  // Returns True when the Piece can Promote
  public boolean canPromote() {
      return !this.promoted && switch (this.type) {
          case KING, GOLD_GENERAL -> false;
          default -> true;
      };
  }

  @Override
  public String toString() {
    return "Piece{" +
      "type=" + this.type +
      ", side=" + this.side +
      '}';
  }
}
