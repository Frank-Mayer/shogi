package de.hhn.shogi;

import de.hhn.shogi.frontend.Window;
import de.hhn.shogi.gamelogic.Game;
import de.hhn.shogi.gamelogic.RuleLogic;
import de.hhn.shogi.gamelogic.util.BoardSide;
import de.hhn.shogi.gamelogic.util.PieceType;
import de.hhn.shogi.gamelogic.util.Vec2;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Game game = new Game(BoardSide.GOTE);


        boolean test = RuleLogic.validMove(new Vec2("A1"), new Vec2("B2"), PieceType.BISHOP, true);
        System.out.println(test);
//        BasicGameState basicGameState = new BasicGameState();
//        basicGameState.getPieces().put("piece",new ArrayList<>(List.of(new String[]{"warum auch nicht"})));
//        String serialized_test = (BasicSerializer.serialize(basicGameState));
//        basicGameState = BasicSerializer.deserialize(basicGameState.getClass(),serialized_test);
    }
}