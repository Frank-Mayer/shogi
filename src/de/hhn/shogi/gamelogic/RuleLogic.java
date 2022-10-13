package de.hhn.shogi.gamelogic;

public class RuleLogic {

    public RuleLogic() {
    }

    public static boolean validMove(int x1, int y1, int x2, int y2, PieceType pieceType){
        if(x1 == x2 && y1 == y2){
            return false;
        }
        return true;
    }
}
