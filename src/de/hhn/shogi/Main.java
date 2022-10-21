package de.hhn.shogi;

import de.hhn.shogi.frontend.Window;
import de.hhn.shogi.gamelogic.Game;
import de.hhn.shogi.gamelogic.RuleLogic;
import de.hhn.shogi.gamelogic.util.BoardSide;
import de.hhn.shogi.gamelogic.util.PieceType;
import de.hhn.shogi.gamelogic.util.Vec2;

import static de.hhn.shogi.gamelogic.Game.ACTIVE_GAME;

public class Main {
    public static void main(String[] args) {
        ACTIVE_GAME = new Game(BoardSide.GOTE);
        new Window(ACTIVE_GAME.getBoard(), ACTIVE_GAME.getBottomPlayer()).setVisible(true);
    }
}