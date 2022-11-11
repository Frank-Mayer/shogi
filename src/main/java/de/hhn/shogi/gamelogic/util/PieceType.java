package de.hhn.shogi.gamelogic.util;

public enum PieceType {
    BISHOP,
    HORSE,
    GOLD_GENERAL,
    KING,
    KNIGHT,
    LANCE,
    PAWN,
    ROOK,
    DRAGON,
    SILVER_GENERAL;

    @Override
    public String toString() {
        return switch (this) {
            case BISHOP -> "Bishop";
            case HORSE -> "Horse";
            case GOLD_GENERAL -> "Gold General";
            case KING -> "King";
            case KNIGHT -> "Knight";
            case LANCE -> "Lance";
            case PAWN -> "Pawn";
            case ROOK -> "Rook";
            case DRAGON -> "Dragon";
            case SILVER_GENERAL -> "Silver General";
        };
    }
}
