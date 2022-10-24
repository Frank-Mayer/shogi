package de.hhn.shogi.gamelogic.state;

import de.hhn.shogi.gamelogic.Board;
import de.hhn.shogi.gamelogic.Piece;
import de.hhn.shogi.gamelogic.Player;
import de.hhn.shogi.gamelogic.RuleLogic;
import de.hhn.shogi.gamelogic.util.BoardSide;
import de.hhn.shogi.gamelogic.util.PieceType;
import de.hhn.shogi.gamelogic.util.Vec2;

import static de.hhn.shogi.Main.getMainWindow;
import static de.hhn.shogi.gamelogic.Game.ACTIVE_GAME;


public class StatePieceSelected extends PlayerTurnState {
  private final Vec2 selectedPos;

  public StatePieceSelected(BoardSide side, Vec2 pos) {
    super(side);
    this.selectedPos = pos;
  }

  @Override
  public void fieldClick(Vec2 pos) {
    Board board = ACTIVE_GAME.getBoard();

    if (RuleLogic.validMove(this.selectedPos, pos, board.getPiece(this.selectedPos))) {
      Piece beatPiece = board.move(this.selectedPos, pos);
      if (beatPiece != null) {
        Player currentPlayer = this.getSide() == board.getBottomSide() ? ACTIVE_GAME.getBottomPlayer() : ACTIVE_GAME.getTopPlayer();
        currentPlayer.getHand().addPiece(beatPiece);
      }
      getMainWindow().removeIcons();
      getMainWindow().setField(pos, board.getPiece(pos));
      getMainWindow().setField(this.selectedPos, board.getPiece(this.selectedPos));
      ACTIVE_GAME.getState().changeState(new StateNeutralTurn(this.getSide() == BoardSide.SENTE ? BoardSide.GOTE : BoardSide.SENTE));
    } else if (board.occupied(pos) && pos != this.selectedPos) {
      if (board.getPiece(pos).getSide() == this.getSide()) {
        ACTIVE_GAME.getState().changeState(new StatePieceSelected(this.getSide(), pos));
        getMainWindow().removeIcons();
        getMainWindow().displayPossibleMoves(RuleLogic.getAllPossibleMoves(pos, board.getPiece(pos)));
      } else {
        getMainWindow().removeIcons();
        ACTIVE_GAME.getState().changeState(new StateNeutralTurn(this.getSide()));
      }
    } else {
      getMainWindow().removeIcons();
      ACTIVE_GAME.getState().changeState(new StateNeutralTurn(this.getSide()));
    }
  }

  @Override
  public void handClick(PieceType type) {

  }
}
