package de.hhn.shogi.gamelogic.state;

import de.hhn.shogi.gamelogic.util.Vec2;

public class StateManager {
    private static GameState state;

    public static void fieldClick(Vec2 pos) {
        state.fieldClick(pos);
    }

    public void changeState(GameState state) {

    }
}
