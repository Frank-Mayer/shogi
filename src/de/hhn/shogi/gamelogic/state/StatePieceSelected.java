package de.hhn.shogi.gamelogic.state;

import de.hhn.shogi.gamelogic.Board;
import de.hhn.shogi.gamelogic.Piece;
import de.hhn.shogi.gamelogic.Player;
import de.hhn.shogi.gamelogic.RuleLogic;
import de.hhn.shogi.gamelogic.util.BoardSide;
import de.hhn.shogi.gamelogic.util.PieceType;
import de.hhn.shogi.gamelogic.util.Vec2;

import static de.hhn.shogi.gamelogic.Game.ACTIVE_GAME;
import static de.hhn.shogi.Main.getMainWindow;


public class StatePieceSelected extends PlayerTurnState {
    private Vec2 selectedPos;

    public StatePieceSelected(BoardSide side, Vec2 pos) {
        super(side);
        selectedPos = pos;
    }

    @Override
    public void fieldClick(Vec2 pos) {
        Board board = ACTIVE_GAME.getBoard();

        if (RuleLogic.validMove(selectedPos, pos, board.getPiece(selectedPos))) {
            Piece beatPiece = board.move(selectedPos, pos);
            if (beatPiece != null) {
                Player currentPlayer = getSide() == board.getBottomSide() ? ACTIVE_GAME.getBottomPlayer() : ACTIVE_GAME.getTopPlayer();
                currentPlayer.getHand().addPiece(beatPiece);
            }
            getMainWindow().removeIcons();
            getMainWindow().setField(pos, board.getPiece(pos));
            getMainWindow().setField(selectedPos, board.getPiece(selectedPos));
            ACTIVE_GAME.getState().changeState(new StateNeutralTurn(getSide() == BoardSide.SENTE ? BoardSide.GOTE : BoardSide.SENTE));
        } else if (board.occupied(pos) && pos != selectedPos) {
            if (board.getPiece(pos).getSide() == getSide()) {
                ACTIVE_GAME.getState().changeState(new StatePieceSelected(getSide(), pos));
                getMainWindow().removeIcons();
                getMainWindow().displayPossibleMoves(RuleLogic.getAllPossibleMoves(pos, board.getPiece(pos)));
            } else {
                getMainWindow().removeIcons();
                ACTIVE_GAME.getState().changeState(new StateNeutralTurn(getSide()));
            }
        } else {
            getMainWindow().removeIcons();
            ACTIVE_GAME.getState().changeState(new StateNeutralTurn(getSide()));
        }
    }

    @Override
    public void handClick(PieceType type) {

    }
}
