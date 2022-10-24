package de.hhn.shogi.gamelogic;

import de.hhn.shogi.gamelogic.state.StateManager;
import de.hhn.shogi.gamelogic.util.BoardSide;

public class Game {
  public static Game ACTIVE_GAME;
  private final Board board;
  private final Player bottomPlayer;
  private final Player topPlayer;
  private final StateManager state;

  public Game(BoardSide bottomSide, BoardSide startingSide) {
      this.board = new Board(bottomSide);
      this.bottomPlayer = new Player(bottomSide);
    BoardSide otherSide = bottomSide == BoardSide.SENTE ? BoardSide.GOTE : BoardSide.SENTE;
      this.topPlayer = new Player(otherSide);
      this.state = new StateManager(startingSide);
  }

  public Game(BoardSide bottomSide, int handicap) {
      this.board = new Board(bottomSide, handicap);
      this.bottomPlayer = new Player(bottomSide);
    BoardSide otherSide = bottomSide == BoardSide.SENTE ? BoardSide.GOTE : BoardSide.SENTE;
      this.topPlayer = new Player(otherSide);
      this.state = new StateManager();
  }

  public Board getBoard() {
    return this.board;
  }

  public Player getBottomPlayer() {
    return this.bottomPlayer;
  }

  public StateManager getState() {
    return this.state;
  }

  public Player getTopPlayer() {
    return this.topPlayer;
  }

  @Override
  public String toString() {
    return "Game{" +
      "board=" + this.board +
      ", bottomPlayer=" + this.bottomPlayer +
      ", topPlayer=" + this.topPlayer +
      '}';
  }
}
