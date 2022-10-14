package de.hhn.shogi.gamelogic;

public class Piece {
    // Current type important for gameLogic
    private PieceType type;
    // Original type of the Piece. Important for frontend and handLogic
    private PieceType baseType;
    // Is Piece in promoted state. Important for frontend
    private boolean promoted;

    public Piece(PieceType type) {
        this.type = this.baseType = type;
    }

    // Returns current Type for Logic
    public PieceType getType() {
        return type;
    }

    // Returns baseType used for UI and DemotionLogic
    public PieceType getBaseType() {
        return baseType;
    }

    // Returns true when the Piece is Promoted
    public boolean isPromoted() {
        return promoted;
    }

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

    // Demotes the Piece. Should not be called typically
    public void demote() {
        type = baseType;
        promoted = false;
    }

    // Returns True when the Piece can Promote
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
