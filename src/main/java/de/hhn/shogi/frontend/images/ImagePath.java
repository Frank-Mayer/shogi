package de.hhn.shogi.frontend.images;

import de.hhn.shogi.gamelogic.util.PieceType;

public final class ImagePath {
    public static String getPath(String file) {
        return "src/main/java/de/hhn/shogi/frontend/images/" + file;
    }

    public static String getPieceImage(PieceType pieceType) {
        return switch (pieceType) {
            case LANCE -> "lance.png";
            case KNIGHT -> "knight.png";
            case BISHOP, HORSE -> "bishop.png";
            case KING -> "king.png";
            case ROOK, DRAGON -> "rook.png";
            case PAWN -> "pawn.png";
            case GOLD_GENERAL -> "gold_general.png";
            case SILVER_GENERAL -> "silver_general.png";
            default -> "blank.png";
        };
    }
}
