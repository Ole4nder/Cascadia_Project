package fr.uge.game;

import java.util.Objects;

public class Game {
  private final StartGame startGame;
  private final GameScore score; // nécéssaire de l'avoir ici ?
  private int numberTurns = 0;

  public Game(StartGame startGame, GameScore endGame) {
    Objects.requireNonNull(startGame);
    Objects.requireNonNull(endGame);
    this.startGame = startGame;
    this.score = endGame;
  }
  
  
  
  public boolean endTheGame() {
	return startGame.tiles().isEmpty() || numberTurns == 20;
  }
  
  
}
