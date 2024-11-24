package fr.uge.game;

import fr.uge.animal.Animals;
import fr.uge.player.Player;
import fr.uge.tile.Tile;

import java.util.*;
import java.util.stream.Collectors;

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
      chooseTileToken(player);
    }
    numberTurns++;
  }

  private void putToken(Player player, Tile tile, Animals token) {
    if(checkAddTokenOnTile(player, token)) {
      player.addTokenOnTile(tile, token);
    }
    else {
      startGame.animalTokens().add(token);
    }
  }

  private void putTile(Player player, Tile tile) {

  }

  private Set<Tile> wherePutTile(Player player) {
    return player.tileNeighborMap().entrySet().stream()
            .filter(entry -> entry.getValue().size() < entry.getKey().numberNeighbors())
            .map(Map.Entry::getKey)
            .collect(Collectors.toSet());
  }

  private void chooseTileToken(Player player) {
    var tileGame = startGame.tiles().subList(0, 3);
    var tokenGame = startGame.animalTokens().tokenList().subList(0, 3);
    tokenGame = overpopulation(tokenGame);
  }

  public boolean checkAddTokenOnTile(Player player, Animals token) {
    return player.tileNeighborMap().keySet().stream()
            .filter(tile -> tile.animalToken() == Animals.DEFAULT)
            .count()
        > 1;
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
   * Draw new tokens if the same animal appears 4 times.
   *
   * @return List<Animals>, new tokens
   */
  public List<Animals> overpopulation(List<Animals> tokens) {
    Map<Animals, Long> animalCounts =
        tokens.stream().collect(Collectors.groupingBy(animal -> animal, Collectors.counting()));
    while (animalCounts.values().stream().anyMatch(count -> count >= 3)) {
      if (animalCounts.values().stream().anyMatch(count -> count == 3)) {
        tokens = drawTokens(3);
        break;
      }
      tokens = drawTokens(4);
      animalCounts =
          tokens.stream().collect(Collectors.groupingBy(animal -> animal, Collectors.counting()));
    }
    return tokens;
  }
}
