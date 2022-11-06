package de.hhn.shogi.gamelogic;

import static de.hhn.shogi.gamelogic.Game.ACTIVE_GAME;
import de.hhn.shogi.gamelogic.util.BoardSide;
import de.hhn.shogi.gamelogic.util.Vec2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RuleLogicTest {

    @BeforeEach
    void setUp() {
        ACTIVE_GAME = new Game(BoardSide.GOTE, BoardSide.GOTE);
    }

    @Test
    void validMove() {
        var board = ACTIVE_GAME.getBoard();
        var source = new Vec2(0, 2);
        var destination1 = new Vec2(1, 3);
        var destination2 = new Vec2(0, 3);
        var destination3 = new Vec2(0, 4);
        var piece = board.getPiece(source);

        if (piece == null) {
            fail("Piece is null");
        }

        assertFalse(RuleLogic.validMove(source, destination1, piece));
        assertTrue(RuleLogic.validMove(source, destination2, piece));
        assertFalse(RuleLogic.validMove(source, destination3, piece));
    }
}
