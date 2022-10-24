package de.hhn.shogi;

import de.hhn.shogi.frontend.Window;
import de.hhn.shogi.gamelogic.Game;
import de.hhn.shogi.gamelogic.util.BoardSide;

import static de.hhn.shogi.gamelogic.Game.ACTIVE_GAME;

public class Main {
    static private Window MAIN_WINDOW;

    public static void main(String[] args) {
        ACTIVE_GAME = new Game(BoardSide.GOTE, BoardSide.GOTE);
        MAIN_WINDOW = new Window(ACTIVE_GAME.getBoard(), ACTIVE_GAME.getBottomPlayer());
        MAIN_WINDOW.setVisible(true);
    }

    public static Window getMainWindow() {
        return MAIN_WINDOW;
    }
}