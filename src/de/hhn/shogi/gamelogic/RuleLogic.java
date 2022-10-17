package de.hhn.shogi.gamelogic;


import de.hhn.shogi.gamelogic.util.BoardSide;
import de.hhn.shogi.gamelogic.util.PieceType;
import de.hhn.shogi.gamelogic.util.Vec2;

public class RuleLogic {

    public RuleLogic() {
    }

    public static boolean validMove(Vec2 from, Vec2 to, PieceType pieceType, boolean facingUp) {
        int xOffset = Vec2.xDiff(from, to);
        int yOffset = Vec2.yDiff(from, to);
        if(!facingUp) yOffset *= -1;
        if (xOffset == 0 && yOffset == 0) {
            return false;
        }
        //TODO check if other pieces are in the way
        return switch (pieceType) {
            case BISHOP ->
                    // A bishop (角) moves any number of squares in a diagonal direction.
                    bishop(xOffset, yOffset);
            case GOLD_GENERAL ->
                    //  A gold general (金) moves one square orthogonally, or one square diagonally forward, giving it six possible destinations. It cannot move diagonally backwards.
                    goldGeneral(xOffset, yOffset);
            case KING ->
                    // A king (玉/王) moves one square in any direction, orthogonal or diagonal.
                    king(xOffset, yOffset);
            case KNIGHT ->
                    // A knight (桂) jumps at an angle intermediate to orthogonal and diagonal, amounting to one square straight forward plus one square diagonally forward, in a single move.
                    knight(xOffset, yOffset);
            case LANCE ->
                    // A lance (香) moves just like the rook except it cannot move backwards or to the sides.
                    lance(xOffset, yOffset);
            case PAWN ->
                    // A pawn (歩) moves one square straight forward.
                    pawn(xOffset, yOffset);
            case ROOK ->
                    // A rook (飛) moves any number of squares in an orthogonal direction.
                    rook(xOffset, yOffset);
            case SILVER_GENERAL ->
                    // A silver general (銀) moves one square diagonally, or one square straight forward, giving it five possible destinations.
                    silverGeneral(xOffset, yOffset);
            case DRAGON ->
                    // A dragon (龍王) moves as a rook and as a king
                    rook(xOffset, yOffset) || king(xOffset, yOffset);
            case HORSE ->
                    // A horse (龍馬) moves as a bishop and as a king
                    bishop(xOffset, yOffset) || king(xOffset, yOffset);
        };
    }

    private static boolean silverGeneral(int xOff, int yOff) {
        return (Math.abs(yOff) == 1 && 1 == Math.abs(xOff)) || (yOff == 1 && xOff == 0);
    }

    private static boolean knight(int xOff, int yOff) {
        return yOff == 2 && Math.abs(xOff) == 1;
    }

    private static boolean goldGeneral(int xOff, int yOff) {
        return ((yOff == 0 || yOff == 1) && Math.abs(xOff) <= 1) || (yOff == -1 && xOff == 0);
    }

    private static boolean bishop(int xOff, int yOff) {
        return Math.abs(yOff) == Math.abs(xOff);
    }

    private static boolean king(int xOff, int yOff) {
        return Math.abs(yOff) <= 1 && Math.abs(xOff) <= 1;
    }

    private static boolean rook(int xOff, int yOff) {
        return yOff == 0 || xOff == 0;
    }

    private static boolean pawn(int xOff, int yOff) {
        return yOff == 1 && xOff == 0;
    }

    private static boolean lance(int xOff, int yOff) {
        return yOff > 0 && xOff == 0;
    }

}
