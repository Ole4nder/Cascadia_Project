package fr.uge.game;

import fr.uge.animal.Animals;
import fr.uge.player.Player;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Game {
  private final StartGame startGame;
  private int numberTurns = 0;
  private boolean replaceTokens = false; // ajouté pour la surpopulation de 3 jetons
  private boolean isGraphic = false;

  public Game(StartGame startGame) {
    Objects.requireNonNull(startGame);
    this.startGame = startGame;
  }

  public boolean endTheGame() {
    return startGame.tiles().isEmpty() || numberTurns == 20;
  }

  public void startGame() {
    for (var player : startGame.players()) {
      playerTurn(player);
    }
    numberTurns++;
  }

  public void playerTurn(Player player) {
    chooseTileToken(player);
    putTileToken(player);
  }

  private void putTileToken(Player player) {

  }


  private void chooseTileToken(Player player) {
    var tile1 = startGame.tiles().get(0);
    var tile2 = startGame.tiles().get(1);
    var tile3 = startGame.tiles().get(2);
    var tile4 = startGame.tiles().get(3);
    var token1 = startGame.animalTokens().tokenList().get(0);
    var token2 = startGame.animalTokens().tokenList().get(1);
    var token3 = startGame.animalTokens().tokenList().get(2);
    var token4 = startGame.animalTokens().tokenList().get(3);
  }

  public boolean asOverpopulation(Animals token1, Animals token2, Animals token3, Animals token4) {
    return token1 == token2 && token2 == token3 && token3 == token4;
  }
  public boolean asThreeSameTokens(Animals token1, Animals token2, Animals token3) {
    return token1 == token2 && token2 == token3;
  }

  /**
   * Get a given amount of tokens from the bag.
   *
   * @param count, amount of tokens to get.
   * @return List<Animals>, list of obtained tokens.
   */
  public List<Animals> drawTokens(int count) {
    if (startGame.animalTokens().tokenList().size() < count) {
      throw new IllegalStateException("Not enough tokens in the bag !");
    }
    List<Animals> drawnTokens = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      Animals token = startGame.animalTokens().tokenList().get(0);
      drawnTokens.add(token);
      startGame.animalTokens().remove(token);
    }
    return drawnTokens;
  }

  /**
   * Check the amount of different animals tokens.
   *
   * @param tokens
   * @return
   */
  public int distinctTokens(List<Animals> tokens) {
    return new HashSet<>(tokens).size();
  }

  /**
   * Draw new tokens if the same animal appears 4 times.
   *
   * @return List<Animals>, new tokens
   */
  public List<Animals> overpopulation(List<Animals> tokens) {
    // while (distinctTokens(tokens) == 1) {  	// TODO: a voir pour la surpopulation de 3
    while (tokens.stream().distinct().count() == 1) {
      tokens = drawTokens(4);
    }
    return tokens;
  }
}
