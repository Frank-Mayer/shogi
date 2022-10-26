package de.hhn.shogi.gamelogic;

import de.hhn.shogi.gamelogic.util.PieceType;

import java.util.ArrayList;

public class Hand {
    // Represents all the Pieces this Hand holds
    private final ArrayList<Piece> pieces = new ArrayList<Piece>();

    // Add a piece to the Hand (and demote it)
    public void addPiece(Piece piece) {
        piece.demote();
        this.pieces.add(piece);
    }


    public Piece removePiece(PieceType type) {
        for (Piece piece : this.pieces) {
            if (piece.getBaseType() == type) {
                this.pieces.remove(piece);
                return piece;
            }
        }
        throw new IllegalArgumentException("Hand does not contain a piece of type: " + type);
    }

    public PieceType[] getAllPieces() {
        if (this.pieces.size() == 0) {
            return null;
        }
        PieceType[] returnValue = new PieceType[this.pieces.size()];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = this.pieces.get(i).getBaseType();
        }
        return returnValue;
    }

    public int getHandSize() {
        return this.pieces.size();
    }
}
