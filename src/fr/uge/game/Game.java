package fr.uge.game;

import java.util.Objects;

public class Game {
  private final StartGame startGame;
  private final EndGame endGame;
  private int numberTurns = 0;

  public Game(StartGame startGame, EndGame endGame) {
    Objects.requireNonNull(startGame);
    Objects.requireNonNull(endGame);
    this.startGame = startGame;
    this.endGame = endGame;
  }
}
