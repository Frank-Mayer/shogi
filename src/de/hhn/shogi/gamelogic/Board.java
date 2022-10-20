package de.hhn.shogi.gamelogic;

import de.hhn.shogi.frontend.Window;
import de.hhn.shogi.gamelogic.util.BoardSide;
import de.hhn.shogi.gamelogic.util.PieceType;
import de.hhn.shogi.gamelogic.util.Vec2;

import java.util.HashMap;

public class Board {
    // Container of all Pieces
    HashMap<Vec2, Piece> map;
    // What side is on the Bottom (point of View)
    BoardSide bottomSide;
    // What side is on the Top
    BoardSide otherSide;
    // What side gets the Handicap
    public static final BoardSide HANDICAPPED_SIDE = BoardSide.GOTE;

    // Creates a new Board and fills it with Pieces
    public Board(BoardSide side) {
        init(side);
        resetBoard(0);
     }

    // Creates a new Board and fills it with Pieces according to the handicap level
    public Board(BoardSide side, int handicap) {
        if (handicap < 0 || handicap > 7)
            throw new IllegalArgumentException("Handicap exceeds the limit of 7 or is negative.");
        init(side);
        resetBoard(handicap);
    }

    public boolean occupied(Vec2 position) {
        return map.containsKey(position);
    }

    // Get the Piece of Position and null if empty.
    public Piece getPiece(Vec2 position) {
        return map.get(position);
    }

    // Moves Piece from one position to another. Returns a Piece if target was occupied.
    public Piece move(Vec2 from, Vec2 to) {
        Piece returnPiece = null;
        if (!occupied(from))
            throw new IllegalArgumentException("Moving Piece does not exist");
        Piece movingPiece = getPiece(from);
        if (!RuleLogic.validMove(from, to, movingPiece.getType(), movingPiece.getSide() == bottomSide))
            throw new IllegalArgumentException("Move is Illegal");
        if (occupied(to))
            returnPiece = getPiece(to);
        map.put(to, movingPiece);
        map.remove(from);
        return returnPiece;
    }

    @Deprecated
    public HashMap<Vec2, Piece> getMap(){
        return map;
    }

    private void init(BoardSide side) {
        map = new HashMap<>();
        bottomSide = side;
        otherSide = bottomSide == BoardSide.GOTE ? BoardSide.SENTE : BoardSide.GOTE;
    }

    //Empties Board and places figures. Handicap only gets applied to GOTE
    private void resetBoard(int handicap) {
        map.clear();
        placeMandatoryPieces();
        placeKnights(handicap);
        placeLances(handicap);
        placeRooks(handicap);
        placeBishops(handicap);
    }

    //Places all Pieces that you are guaranteed to start with
    private void placeMandatoryPieces() {
        for (int i = 0; i < 9; i++) {
            createPiece(i, 2, PieceType.PAWN, bottomSide);
            createPiece(i, 6, PieceType.PAWN, otherSide);
        }
        createPiece(4, 0, PieceType.KING, bottomSide);
        createPiece(4, 8, PieceType.KING, otherSide);
        createPiece(3, 0, PieceType.GOLD_GENERAL, bottomSide);
        createPiece(5, 0, PieceType.GOLD_GENERAL, bottomSide);
        createPiece(3, 8, PieceType.GOLD_GENERAL, otherSide);
        createPiece(5, 8, PieceType.GOLD_GENERAL, otherSide);
        createPiece(2, 0, PieceType.SILVER_GENERAL, bottomSide);
        createPiece(6, 0, PieceType.SILVER_GENERAL, bottomSide);
        createPiece(2, 8, PieceType.SILVER_GENERAL, otherSide);
        createPiece(6, 8, PieceType.SILVER_GENERAL, otherSide);
    }

    //Places Knights according to Handicap
    private void placeKnights(int handicap) {
        if (handicap < 7 || bottomSide != HANDICAPPED_SIDE) {
            createPiece(1, 0, PieceType.KNIGHT, bottomSide);
            createPiece(7, 0, PieceType.KNIGHT, bottomSide);
        }
        if (handicap < 7 || otherSide != HANDICAPPED_SIDE) {
            createPiece(1, 8, PieceType.KNIGHT, otherSide);
            createPiece(7, 8, PieceType.KNIGHT, otherSide);
        }
    }

    //Places Lances according to Handicap
    private void placeLances(int handicap) {
        if (handicap != 1 && handicap != 4 && handicap < 6 || bottomSide != HANDICAPPED_SIDE) {
            createPiece(0, 0, PieceType.LANCE, bottomSide);
        }
        if (handicap < 6 || bottomSide != HANDICAPPED_SIDE) {
            createPiece(8, 0, PieceType.LANCE, bottomSide);
        }
        if (handicap != 1 && handicap != 4 && handicap < 6 || otherSide != HANDICAPPED_SIDE) {
            createPiece(8, 8, PieceType.LANCE, bottomSide);
        }
        if (handicap < 6 || otherSide != HANDICAPPED_SIDE) {
            createPiece(0, 8, PieceType.LANCE, bottomSide);
        }
    }

    //Places Rooks according to Handicap
    private void placeRooks(int handicap) {
        if (handicap < 3 || bottomSide != HANDICAPPED_SIDE) {
            createPiece(7, 1, PieceType.ROOK, bottomSide);
        }
        if (handicap < 3 || otherSide != HANDICAPPED_SIDE) {
            createPiece(1, 7, PieceType.ROOK, otherSide);
        }
    }

    //Places Bishops according to Handicap
    private void placeBishops(int handicap) {
        if (handicap != 2 && handicap < 5 || bottomSide != HANDICAPPED_SIDE) {
            createPiece( 1, 1, PieceType.BISHOP, bottomSide);
        }
        if (handicap != 2 && handicap < 5 || otherSide != HANDICAPPED_SIDE) {
            createPiece( 7, 7, PieceType.BISHOP, otherSide);
        }
    }

    //Creates a piece on the board
    private void createPiece(int x, int y, PieceType type, BoardSide side) {
        map.put(new Vec2(x, y), new Piece(type, side));
    }
}
