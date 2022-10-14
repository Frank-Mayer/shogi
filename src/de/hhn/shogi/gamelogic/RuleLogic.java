package de.hhn.shogi.gamelogic;


import de.hhn.shogi.gamelogic.util.PieceType;

public class RuleLogic {

    public RuleLogic() {
    }

    public static boolean validMove(int x1, int y1, int x2, int y2, PieceType pieceType) {
        int xOffset = x2 - x1;
        int yOffset = y2 - y1;
        if (xOffset == 0 && yOffset == 0) {
            return false;
        }
        //TODO check if other pieces are in the way
        switch (pieceType) {
            case BISHOP:
                // A bishop (角) moves any number of squares in a diagonal direction.
                if (Math.abs(yOffset) == Math.abs(xOffset)) {
                    return true;
                }
                break;
            case GOLD_GENERAL:
                //  A gold general (金) moves one square orthogonally, or one square diagonally forward, giving it six possible destinations. It cannot move diagonally backwards.
                if (((yOffset == 0 || yOffset == 1) && Math.abs(xOffset) <= 1) || (yOffset == -1 && xOffset == 0)) {
                    return true;
                }
                break;
            case KING:
                // A king (玉/王) moves one square in any direction, orthogonal or diagonal.
                if (Math.abs(yOffset) <= 1 && Math.abs(xOffset) <= 1) {
                    return true;
                }
                break;
            case KNIGHT:
                // A knight (桂) jumps at an angle intermediate to orthogonal and diagonal, amounting to one square straight forward plus one square diagonally forward, in a single move.
                if (yOffset == 2 && Math.abs(xOffset) == 1) {
                    return true;
                }
                break;
            case LANCE:
                // A lance (香) moves just like the rook except it cannot move backwards or to the sides.
                if (yOffset > 0 && xOffset == 0) {
                    return true;
                }
                break;
            case PAWN:
                // A pawn (歩) moves one square straight forward.
                if (yOffset == 1 && xOffset == 0) {
                    return true;
                }
                break;
            case ROOK:
                // A rook (飛) moves any number of squares in an orthogonal direction.
                if (yOffset == 0 || xOffset == 0) {
                    return true;
                }
                break;
            case SILVER_GENERAL:
                // A silver general (銀) moves one square diagonally, or one square straight forward, giving it five possible destinations.
                if ((Math.abs(yOffset) == 1 && 1 == Math.abs(xOffset)) || (yOffset == 1 && xOffset == 0)) {
                    return true;
                }
                break;
            default:
                throw new IllegalArgumentException();
        }
        return false;
    }


}
