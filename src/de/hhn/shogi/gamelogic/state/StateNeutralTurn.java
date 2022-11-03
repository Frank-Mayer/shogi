package de.hhn.shogi.gamelogic.state;

import de.hhn.shogi.gamelogic.Board;
import de.hhn.shogi.gamelogic.Piece;
import de.hhn.shogi.gamelogic.RuleLogic;
import de.hhn.shogi.gamelogic.util.BoardSide;
import de.hhn.shogi.gamelogic.util.Vec2;

import static de.hhn.shogi.Main.getMainWindow;
import static de.hhn.shogi.gamelogic.Game.ACTIVE_GAME;

public class StateNeutralTurn extends PlayerTurnState {

    public StateNeutralTurn(BoardSide side) {
        super(side);
    }

    @Override
    public void fieldClick(Vec2 pos) {
        Board board = ACTIVE_GAME.getBoard();
        if (board.occupied(pos)) {
            if (board.getPiece(pos).getSide() == this.getSide()) {
                ACTIVE_GAME.getState().changeState(new StatePieceSelected(this.getSide(), pos));
                getMainWindow().displayPossibleMoves(RuleLogic.getAllPossibleMoves(pos, board.getPiece(pos)));
            }
        }
    }

    @Override
    public void handClick(Piece piece) {
        if(piece != null){
            if (piece.getSide() == this.getSide()) {
                ACTIVE_GAME.getState().changeState(new StateHandSelected(this.getSide(), piece));
                getMainWindow().displayPossibleMoves(RuleLogic.getAllPossibleDrops(piece));
            }
        }
    }
}
