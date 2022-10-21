package de.hhn.shogi;

import de.hhn.shogi.frontend.Window;
import de.hhn.shogi.gamelogic.Game;
import de.hhn.shogi.gamelogic.RuleLogic;
import de.hhn.shogi.gamelogic.util.BoardSide;
import de.hhn.shogi.gamelogic.util.PieceType;
import de.hhn.shogi.gamelogic.util.Vec2;

public class Main {
    public static void main(String[] args) {
        Game.ACTIVE_GAME = new Game(BoardSide.GOTE);

    }
}