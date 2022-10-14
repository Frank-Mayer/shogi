package de.hhn.shogi.gamelogic;

public class Piece {
    private PieceType type;
    private PieceType baseType;
    private boolean promoted;

    //Tries to promote the Piece. Returns whether the promotion was done successfully.
    public boolean promote() {
        if (!canPromote())
            return false;
        type = switch (baseType) {
            default -> PieceType.GOLD_GENERAL;
            case ROOK -> PieceType.DRAGON;
            case BISHOP -> PieceType.HORSE;
        };
        return true;
    }

    public boolean canPromote() {
        if (promoted) {
            return false;
        }
        return switch (type) {
            default -> true;
            case KING, GOLD_GENERAL -> false;
        };
    }
}
