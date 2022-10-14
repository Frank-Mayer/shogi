package de.hhn.shogi.gamelogic;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Piece> pieces = new ArrayList<Piece>();

    public void addPiece(Piece piece) {
        pieces.add(piece);
    }

    
}
