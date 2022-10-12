package de.hhn.shogi;

import de.hhn.shogi.BasicGameManager.BasicGameState;
import de.hhn.shogi.BasicGameManager.BasicNetworkHelper;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        boolean test = RuleLogic.validMove(0, 0, 0, 0, Piece.BISHOP);
        System.out.println(test);
        System.out.println(BasicNetworkHelper.serialize(new BasicGameState("test","test2")));
    }
}