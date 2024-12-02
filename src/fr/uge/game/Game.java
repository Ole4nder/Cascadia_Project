package fr.uge.game;

import fr.uge.animal.Animals;
import fr.uge.board.players.PlayerBoard;
import fr.uge.graphic.Graphic;
import fr.uge.interaction.Interaction;
import fr.uge.tile.Tile;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents a game with a start configuration. startGame = the start configuration of the game.
 * numberTurns = the number of turns played. graphic = the graphic interface of the game.
 * interaction = the interaction interface of the game.
 */
public class Game {
  private final InitGame initGame;
  private final Graphic graphic;
  private final Interaction interaction;
  private int numberTurns = 0;

  public Game(InitGame initGame, Graphic graphic, Interaction interaction) {
    Objects.requireNonNull(initGame);
    this.initGame = initGame;
    this.graphic = graphic;
    this.interaction = interaction;
  }

  /**
   * End the game if there are no more tiles or if the number of turns is 20.
   *
   * @return true if the game is over, false otherwise.
   */
  public boolean endTheGame() {
    return initGame.tileBag().tileBag().isEmpty() || numberTurns == 20;
  }

  public InitGame startGame() {
    return initGame;
  }

  /** Game loop. (Unfinished) */
  public void play() {
    while (!endTheGame()) {
      playerTurns();
    }
  }

  /** Players take turns to play. */
  public void playerTurns() {
    for (var player : initGame.playersBoards().playerBoardsList()) {
      chooseTileToken(player);
    }
    numberTurns++;
  }

  /**
   * Put a token on a tile.
   *
   * @param playerBoard
   * @param tile
   * @param token
   */
  private void putToken(PlayerBoard playerBoard, Tile tile, Animals token) {
    Objects.requireNonNull(playerBoard);
    Objects.requireNonNull(tile);
    Objects.requireNonNull(token);
    if (checkAddTokenOnTile(playerBoard, token)) {
    } else {
      initGame.animalTokenBag().add(token);
    }
  }

  private void putTile(PlayerBoard playerBoard, Tile tile) {}

  /**
   * Get the tiles where a player can put a tile.
   *
   * @param playerBoard
   * @return Set<Tile>, the tiles where a player can put a tile.
   */
  private Set<Tile> wherePutTile(PlayerBoard playerBoard) {
    Objects.requireNonNull(playerBoard);
    return playerBoard.tileNeighborMap().entrySet().stream()
        .filter(entry -> entry.getValue().length < initGame.boardType().getValue())
        .map(Map.Entry::getKey)
        .collect(Collectors.toSet());
  }

  /**
   * Choose a tile and a token for a player.
   *
   * @param playerBoard
   */
  private void chooseTileToken(PlayerBoard playerBoard) {
    Objects.requireNonNull(playerBoard);
    // TODO: implement
  }

  /**
   * create board option for player
   *
   * @param tiles
   * @param tokens
   * @return
   */
  private List<OptionTileToken> createAllOption(List<Tile> tiles, List<Animals> tokens) {
    Objects.requireNonNull(tiles);
    Objects.requireNonNull(tokens);
    if (tiles.size() != tokens.size()) {
      throw new IllegalStateException("size of tiles and tokens are different");
    }
    var optionTileTokens = new ArrayList<OptionTileToken>();
    for (var i = 0; i < tiles.size(); i++) {
      optionTileTokens.add(new OptionTileToken(tiles.get(i), tokens.get(i)));
    }
    return optionTileTokens;
  }

  /**
   * Check if a token can be added on a tile.
   *
   * @param playerBoard, the player who wants to add a token.
   * @param token, the token to add.
   * @return true if the token can be added, false otherwise.
   */
  public boolean checkAddTokenOnTile(PlayerBoard playerBoard, Animals token) {
    return playerBoard.tileNeighborMap().keySet().stream()
            .filter(tile -> tile.animalToken() == Animals.DEFAULT)
            .count()
        > 1;
  }

  private List<Tile> wherePutToken(PlayerBoard playerBoard) {
    return playerBoard.tileNeighborMap().keySet().stream()
        .filter(tile -> tile.animalToken() == Animals.DEFAULT)
        .toList();
  }

  /**
   * Get a given amount of tokens from the bag.
   *
   * @param count, amount of tokens to get.
   * @return List of animals, list of obtained tokens.
   */
  public List<Animals> drawTokens(int count) {
    if (initGame.animalTokenBag().tokenBag().size() < count) {
      throw new IllegalStateException("Not enough tokens in the bag !");
    }
    List<Animals> drawnTokens = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      Animals token = initGame.animalTokenBag().tokenBag().get(0);
      drawnTokens.add(token);
      initGame.animalTokenBag().tokenBag().remove(token);
    }
    return drawnTokens;
  }

  /**
   * Draw new tokens if the same animal appears 4 times.
   *
   * @param tokens, the tokens to check.
   * @return List of animals, new tokens
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
