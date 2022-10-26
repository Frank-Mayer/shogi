package de.hhn.shogi.basicgamemanager;

import java.util.ArrayList;
import java.util.HashMap;

public class BasicGameState {
    final private HashMap<String, ArrayList<String>> piece = new HashMap<>();
    final private String test = "test4";


    public BasicGameState() {

    }

    public HashMap<String, ArrayList<String>> getPieces() {
        return this.piece;
    }
}
