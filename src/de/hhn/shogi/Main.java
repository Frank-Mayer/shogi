package de.hhn.shogi;

import de.hhn.shogi.basicgamemanager.BasicGameState;
import de.hhn.shogi.basicgamemanager.BasicSerializer;
import de.hhn.shogi.gamelogic.PieceType;
import de.hhn.shogi.gamelogic.RuleLogic;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        boolean test = RuleLogic.validMove(0, 0, 0, 0, PieceType.BISHOP);
        System.out.println(test);
        BasicGameState basicGameState = new BasicGameState();
        basicGameState.getPieces().put("piece",new ArrayList<>(List.of(new String[]{"warum auch nicht"})));
        String serialized_test = (BasicSerializer.serialize(basicGameState));
        basicGameState = BasicSerializer.deserialize(serialized_test);
    }
}