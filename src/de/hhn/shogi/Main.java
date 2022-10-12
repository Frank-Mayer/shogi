package de.hhn.shogi;

import de.hhn.shogi.basicgamemanager.BasicGameState;
import de.hhn.shogi.basicgamemanager.BasicSerializer;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        boolean test = RuleLogic.validMove(0, 0, 0, 0, Piece.BISHOP);
        System.out.println(test);
        String serialized_test = (BasicSerializer.serialize(new BasicGameState("test","test2")));
        BasicGameState basicGameState = BasicSerializer.deserialize(serialized_test);
    }
}