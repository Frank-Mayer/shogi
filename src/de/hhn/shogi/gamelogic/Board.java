package de.hhn.shogi.gamelogic;

import de.hhn.shogi.gamelogic.util.BoardSide;
import de.hhn.shogi.gamelogic.util.PieceType;
import de.hhn.shogi.gamelogic.util.Vec2;

import java.util.HashMap;

public class Board {
    // What side gets the Handicap
    public static final BoardSide HANDICAPPED_SIDE = BoardSide.GOTE;
    // Container of all Pieces
    HashMap<Vec2, Piece> map;
    // What side is on the Bottom (point of View)
    BoardSide bottomSide;
    // What side is on the Top
    BoardSide otherSide;

    // Creates a new Board and fills it with Pieces
    public Board(BoardSide side) {
        this.init(side);
        this.resetBoard(0);
    }

    // Creates a new Board and fills it with Pieces according to the handicap level
    public Board(BoardSide side, int handicap) {
        if (handicap < 0 || handicap > 7) {
            throw new IllegalArgumentException("Handicap exceeds the limit of 7 or is negative.");
        }
        this.init(side);
        this.resetBoard(handicap);
    }

    public boolean occupied(Vec2 position) {
        return this.map.containsKey(position);
    }

    // Get the Piece of Position and null if empty.
    public Piece getPiece(Vec2 position) {
        return this.map.get(position);
    }

    // Moves Piece from one position to another. Returns a Piece if target was occupied.
    public Piece move(Vec2 from, Vec2 to) {
        Piece returnPiece = null;
        if (!this.occupied(from)) {
            throw new IllegalArgumentException("Moving Piece does not exist");
        }
        Piece movingPiece = this.getPiece(from);
        if (!RuleLogic.validMove(from, to, movingPiece)) {
            throw new IllegalArgumentException("Move is Illegal");
        }
        if (this.occupied(to)) {
            returnPiece = this.getPiece(to);
        }
        this.map.put(to, movingPiece);
        this.map.remove(from);
        return returnPiece;
    }

    @Deprecated
    public HashMap<Vec2, Piece> getMap() {
        return this.map;
    }

    private void init(BoardSide side) {
        this.map = new HashMap<>();
        this.bottomSide = side;
        this.otherSide = this.bottomSide == BoardSide.GOTE ? BoardSide.SENTE : BoardSide.GOTE;
    }

    //Empties Board and places figures. Handicap only gets applied to GOTE
    private void resetBoard(int handicap) {
        this.map.clear();
        this.placeMandatoryPieces();
        this.placeKnights(handicap);
        this.placeLances(handicap);
        this.placeRooks(handicap);
        this.placeBishops(handicap);
    }

    //Places all Pieces that you are guaranteed to start with
    private void placeMandatoryPieces() {
        for (int i = 0; i < 9; i++) {
            this.createPiece(i, 2, PieceType.PAWN, this.bottomSide);
            this.createPiece(i, 6, PieceType.PAWN, this.otherSide);
        }
        this.createPiece(4, 0, PieceType.KING, this.bottomSide);
        this.createPiece(4, 8, PieceType.KING, this.otherSide);
        this.createPiece(3, 0, PieceType.GOLD_GENERAL, this.bottomSide);
        this.createPiece(5, 0, PieceType.GOLD_GENERAL, this.bottomSide);
        this.createPiece(3, 8, PieceType.GOLD_GENERAL, this.otherSide);
        this.createPiece(5, 8, PieceType.GOLD_GENERAL, this.otherSide);
        this.createPiece(2, 0, PieceType.SILVER_GENERAL, this.bottomSide);
        this.createPiece(6, 0, PieceType.SILVER_GENERAL, this.bottomSide);
        this.createPiece(2, 8, PieceType.SILVER_GENERAL, this.otherSide);
        this.createPiece(6, 8, PieceType.SILVER_GENERAL, this.otherSide);
    }

    //Places Knights according to Handicap
    private void placeKnights(int handicap) {
        if (handicap < 7 || this.bottomSide != Board.HANDICAPPED_SIDE) {
            this.createPiece(1, 0, PieceType.KNIGHT, this.bottomSide);
            this.createPiece(7, 0, PieceType.KNIGHT, this.bottomSide);
        }
        if (handicap < 7 || this.otherSide != Board.HANDICAPPED_SIDE) {
            this.createPiece(1, 8, PieceType.KNIGHT, this.otherSide);
            this.createPiece(7, 8, PieceType.KNIGHT, this.otherSide);
        }
    }

    //Places Lances according to Handicap
    private void placeLances(int handicap) {
        if (handicap != 1 && handicap != 4 && handicap < 6 || this.bottomSide != Board.HANDICAPPED_SIDE) {
            this.createPiece(0, 0, PieceType.LANCE, this.bottomSide);
        }
        if (handicap < 6 || this.bottomSide != Board.HANDICAPPED_SIDE) {
            this.createPiece(8, 0, PieceType.LANCE, this.bottomSide);
        }
        if (handicap != 1 && handicap != 4 && handicap < 6 || this.otherSide != Board.HANDICAPPED_SIDE) {
            this.createPiece(8, 8, PieceType.LANCE, this.otherSide);
        }
        if (handicap < 6 || this.otherSide != Board.HANDICAPPED_SIDE) {
            this.createPiece(0, 8, PieceType.LANCE, this.otherSide);
        }
    }

    //Places Rooks according to Handicap
    private void placeRooks(int handicap) {
        if (handicap < 3 || this.bottomSide != Board.HANDICAPPED_SIDE) {
            this.createPiece(7, 1, PieceType.ROOK, this.bottomSide);
        }
        if (handicap < 3 || this.otherSide != Board.HANDICAPPED_SIDE) {
            this.createPiece(1, 7, PieceType.ROOK, this.otherSide);
        }
    }

    //Places Bishops according to Handicap
    private void placeBishops(int handicap) {
        if (handicap != 2 && handicap < 5 || this.bottomSide != Board.HANDICAPPED_SIDE) {
            this.createPiece(1, 1, PieceType.BISHOP, this.bottomSide);
        }
        if (handicap != 2 && handicap < 5 || this.otherSide != Board.HANDICAPPED_SIDE) {
            this.createPiece(7, 7, PieceType.BISHOP, this.otherSide);
        }
    }

    //Creates a piece on the board
    private void createPiece(int x, int y, PieceType type, BoardSide side) {
        this.map.put(new Vec2(x, y), new Piece(type, side));
    }

    public BoardSide getBottomSide() {
        return this.bottomSide;
    }
}
