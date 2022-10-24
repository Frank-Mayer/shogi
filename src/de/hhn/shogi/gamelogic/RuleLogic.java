package de.hhn.shogi.gamelogic;


import de.hhn.shogi.gamelogic.util.PieceType;
import de.hhn.shogi.gamelogic.util.Vec2;

import java.util.ArrayList;

import static de.hhn.shogi.gamelogic.Game.ACTIVE_GAME;

public class RuleLogic {

  public RuleLogic() {
  }

  //is it a valid move?
  public static boolean validMove(Vec2 from, Vec2 to, Piece piece) {
    int xOffset = Vec2.xDiff(from, to);
    int yOffset = Vec2.yDiff(from, to);
    if (piece.getSide() == ACTIVE_GAME.getTopPlayer().getSide()) {
      yOffset *= -1;
    }

    //is it your own piece
    if (ACTIVE_GAME.getBoard().getPiece(to) != null) {
      if (ACTIVE_GAME.getBoard().getPiece(to).getSide() == piece.getSide()) {
        return false;
      }
    }

    //TODO check if other pieces are in the way
    return switch (piece.getType()) {
      case BISHOP ->
        // A bishop (角) moves any number of squares in a diagonal direction.
        RuleLogic.bishop(from, to);
      case GOLD_GENERAL ->
        //  A gold general (金) moves one square orthogonally, or one square diagonally forward, giving it six possible destinations. It cannot move diagonally backwards.
        RuleLogic.goldGeneral(xOffset, yOffset);
      case KING ->
        // A king (玉/王) moves one square in any direction, orthogonal or diagonal.
        RuleLogic.king(xOffset, yOffset);
      case KNIGHT ->
        // A knight (桂) jumps at an angle intermediate to orthogonal and diagonal, amounting to one square straight forward plus one square diagonally forward, in a single move.
        RuleLogic.knight(xOffset, yOffset);
      case LANCE ->
        // A lance (香) moves just like the rook except it cannot move backwards or to the sides.
        RuleLogic.lance(from, to, piece.getSide() == ACTIVE_GAME.getBottomPlayer().getSide());
      case PAWN ->
        // A pawn (歩) moves one square straight forward.
        RuleLogic.pawn(xOffset, yOffset);
      case ROOK ->
        // A rook (飛) moves any number of squares in an orthogonal direction.
        RuleLogic.rook(from, to);
      case SILVER_GENERAL ->
        // A silver general (銀) moves one square diagonally, or one square straight forward, giving it five possible destinations.
        RuleLogic.silverGeneral(xOffset, yOffset);
      case DRAGON ->
        // A dragon (龍王) moves as a rook and as a king
        RuleLogic.rook(from, to) || RuleLogic.king(xOffset, yOffset);
      case HORSE ->
        // A horse (龍馬) moves as a bishop and as a king
        RuleLogic.bishop(from, to) || RuleLogic.king(xOffset, yOffset);
    };
  }

  //returns all possible moves of a piece
  public static ArrayList<Vec2> getAllPossibleMoves(Vec2 pos, Piece piece) {
    ArrayList<Vec2> result = new ArrayList<>();
    for (int x = 0; x < 9; x++) {
      for (int y = 0; y < 9; y++) {
        Vec2 toPos = new Vec2(x, y);
        if (RuleLogic.validMove(pos, toPos, piece)) {
          result.add(toPos);
        }
      }
    }
    return result;
  }

  //returns all possible positions you can drop your piece
  public static ArrayList<Vec2> getAllPossibleDrops(Piece piece) {
    ArrayList<Vec2> result = new ArrayList<>();

    switch (piece.getType()) {
      case PAWN:
        for (int y = 0; y < 9; y++) {
          ArrayList<Vec2> column = new ArrayList<>();
          for (int x = 0; x < 8; x++) {
            Vec2 dropPos = new Vec2(x, y);
            if (!ACTIVE_GAME.getBoard().occupied(dropPos)) {
              column.add(dropPos);
            } else {
              //test if column contains pawn -> if yes: clear column
              Piece occupyingPiece = ACTIVE_GAME.getBoard().getPiece(dropPos);
              if (occupyingPiece.getType() == PieceType.PAWN && !occupyingPiece.isPromoted() && occupyingPiece.getSide() == piece.getSide()) {
                column.clear();
                break;
              }
            }
          }
          result.addAll(column);
        }
        break;
      case LANCE:
        for (int x = 0; x < 9; x++) {
          for (int y = 0; y < 8; y++) {
            Vec2 dropPos = new Vec2(x, y);
            if (!ACTIVE_GAME.getBoard().occupied(dropPos)) {
              result.add(dropPos);
            }
          }
        }
        break;
      case KNIGHT:
        for (int x = 0; x < 9; x++) {
          for (int y = 0; y < 7; y++) {
            Vec2 dropPos = new Vec2(x, y);
            if (!ACTIVE_GAME.getBoard().occupied(dropPos)) {
              result.add(dropPos);
            }
          }
        }
        break;
      default:
        for (int x = 0; x < 9; x++) {
          for (int y = 0; y < 9; y++) {
            Vec2 dropPos = new Vec2(x, y);
            if (!ACTIVE_GAME.getBoard().occupied(dropPos)) {
              result.add(dropPos);
            }
          }
        }
        break;
    }
    return result;
  }

  private static boolean silverGeneral(int xOff, int yOff) {
    return Math.abs(yOff) == 1 && 1 == Math.abs(xOff) || yOff == 1 && xOff == 0;
  }

  private static boolean knight(int xOff, int yOff) {
    return yOff == 2 && Math.abs(xOff) == 1;
  }

  private static boolean goldGeneral(int xOff, int yOff) {
    return (yOff == 0 || yOff == 1) && Math.abs(xOff) <= 1 || yOff == -1 && xOff == 0;
  }

  private static boolean bishop(Vec2 from, Vec2 to) {
    if (Math.abs(Vec2.xDiff(from, to)) != Math.abs(Vec2.yDiff(from, to))) {
      return false;
    }
    int offset = Math.abs(Vec2.xDiff(from, to));

    //right-up
    if (Vec2.xDiff(from, to) > 0 && Vec2.yDiff(from, to) > 0) {
      for (int i = 1; i < offset; i++) {
        if (ACTIVE_GAME.getBoard().getPiece(new Vec2(from.getX() + i, from.getY() + i)) != null) {
          return false;
        }
      }
      return true;
    }
    //right-down
    if (Vec2.xDiff(from, to) > 0 && Vec2.yDiff(from, to) < 0) {
      for (int i = 1; i < offset; i++) {
        if (ACTIVE_GAME.getBoard().getPiece(new Vec2(from.getX() + i, from.getY() - i)) != null) {
          return false;
        }
      }
      return true;
    }
    //left-up
    if (Vec2.xDiff(from, to) < 0 && Vec2.yDiff(from, to) > 0) {
      for (int i = 1; i < offset; i++) {
        if (ACTIVE_GAME.getBoard().getPiece(new Vec2(from.getX() - i, from.getY() + i)) != null) {
          return false;
        }
      }
      return true;
    }
    //left-down
    if (Vec2.xDiff(from, to) < 0 && Vec2.yDiff(from, to) < 0) {
      for (int i = 1; i < offset; i++) {
        if (ACTIVE_GAME.getBoard().getPiece(new Vec2(from.getX() - i, from.getY() - i)) != null) {
          return false;
        }
      }
      return true;
    }
    return false;
  }

  private static boolean king(int xOff, int yOff) {
    return Math.abs(yOff) <= 1 && Math.abs(xOff) <= 1;
  }

  private static boolean rook(Vec2 from, Vec2 to) {
    if (Vec2.xDiff(from, to) > 0 && Vec2.yDiff(from, to) == 0) {
      for (int i = from.getX() + 1; i < to.getX(); i++) {
        if (ACTIVE_GAME.getBoard().getPiece(new Vec2(i, from.getY())) != null) {
          return false;
        }
      }
      return true;
    }
    if (Vec2.xDiff(from, to) < 0 && Vec2.yDiff(from, to) == 0) {
      for (int i = from.getX() - 1; i > to.getX(); i--) {
        if (ACTIVE_GAME.getBoard().getPiece(new Vec2(i, from.getY())) != null) {
          return false;
        }
      }
      return true;
    }
    return RuleLogic.lance(from, to, true) || RuleLogic.lance(from, to, false);
  }

  private static boolean pawn(int xOff, int yOff) {
    return yOff == 1 && xOff == 0;
  }

  private static boolean lance(Vec2 from, Vec2 to, boolean facingUp) {
    if (facingUp) {
      //check up
      if (Vec2.xDiff(from, to) == 0 && Vec2.yDiff(from, to) > 0) {
        for (int i = from.getY() + 1; i < to.getY(); i++) {
          if (ACTIVE_GAME.getBoard().getPiece(new Vec2(from.getX(), i)) != null) {
            return false;
          }
        }
        return true;
      }
    } else {
      //check down
      if (Vec2.xDiff(from, to) == 0 && Vec2.yDiff(from, to) < 0) {
        for (int i = from.getY() - 1; i > to.getY(); i--) {
          if (ACTIVE_GAME.getBoard().getPiece(new Vec2(from.getX(), i)) != null) {
            return false;
          }
        }
        return true;
      }
    }
    return false;
  }


}
