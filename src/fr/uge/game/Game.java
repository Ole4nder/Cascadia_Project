package fr.uge.game;

import java.util.*;

/**
 * Represents a game with a start configuration.
 * startGame = the start configuration of the game.
 * numberTurns = the number of turns played.
 */
public class Game {
  private final InitGame initGame;
  private int numberTurns = 0;

  public Game(InitGame initGame) {
    Objects.requireNonNull(initGame);
    this.initGame = initGame;
  }

  /**
   * End the game if there are no more tiles or if the number of turns is 20.
   *
   * @return true if the game is over, false otherwise.
   */
//  public boolean endTheGame() {
//    return initGame.tiles().isEmpty() || numberTurns == 20;
//  }

  public InitGame startGame() {
    return initGame;
  }
//
//  /**
//   * Game loop. (Unfinished)
//   */
//  public void play() {
//    while (!endTheGame()) {
//      playerTurns();
//    }
//  }
//
//  /** Players take turns to play. */
//  public void playerTurns() {
//    for (var player : initGame.players()) {
//      chooseTileToken(player);
//    }
//    numberTurns++;
//  }
//
//  /**
//   * Put a token on a tile.
//   *
//   * @param playerBoard
//   * @param tile
//   * @param token
//   */
//  private void putToken(PlayerBoard playerBoard, Tile tile, Animals token) {
//    Objects.requireNonNull(playerBoard);
//    Objects.requireNonNull(tile);
//    Objects.requireNonNull(token);
//    if (checkAddTokenOnTile(playerBoard, token)) {
//    } else {
//      initGame.animalTokens().add(token);
//    }
//  }
//
//  private void putTile(PlayerBoard playerBoard, Tile tile) {}
//
//  /**
//   * Get the tiles where a player can put a tile.
//   *
//   * @param playerBoard
//   * @return Set<Tile>, the tiles where a player can put a tile.
//   */
//  private List<Tile> wherePutTile(PlayerBoard playerBoard) {
//    Objects.requireNonNull(playerBoard);
//    return playerBoard.tileNeighborMap().entrySet().stream()
//        .filter(entry -> entry.getValue().length < initGame.plateauType().getValue())
//        .map(Map.Entry::getKey)
//        .collect(Collectors.toList());
//  }
//
//  /**
//   * Choose a tile and a token for a player.
//   *
//   * @param playerBoard
//   */
//  private void chooseTileToken(PlayerBoard playerBoard) {
//    Objects.requireNonNull(playerBoard);
//    var tileGame = initGame.tiles().subList(0, 3);
//    var tokenGame = initGame.animalTokens().tokenList().subList(0, 3);
//    tokenGame = overpopulation(tokenGame);
//    // TODO change GraphicTerminal to interface of the different graphic for the game
//    var playerChoice = GraphicTerminal.ChoicePlayer(createAllOption(tileGame, tokenGame));
//    var tileOrTokens = GraphicTerminal.choosePutTileToken();
//    if (tileOrTokens.equals("token")) {
//      var tokenPlay = GraphicTerminal.choosePutToken(wherePutToken(playerBoard));
//      var tilePLay = GraphicTerminal.choosePutTile(wherePutToken(playerBoard));
//    } else {
//      var tilePlay = GraphicTerminal.choosePutTile(wherePutToken(playerBoard));
//      var tokenPlay = GraphicTerminal.choosePutToken(wherePutToken(playerBoard));
//    }
//  }
//
//  /**
//   * create board option for player
//   *
//   * @param tiles
//   * @param tokens
//   * @return
//   */
//  private List<OptionTileToken> createAllOption(List<Tile> tiles, List<Animals> tokens) {
//    Objects.requireNonNull(tiles);
//    Objects.requireNonNull(tokens);
//    if (tiles.size() != tokens.size()) {
//      throw new IllegalStateException("size of tiles and tokens are different");
//    }
//    var optionTileTokens = new ArrayList<OptionTileToken>();
//    for (var i = 0; i < tiles.size(); i++) {
//      optionTileTokens.add(new OptionTileToken(tiles.get(i), tokens.get(i)));
//    }
//    return optionTileTokens;
//  }
//
//  /**
//   * Check if a token can be added on a tile.
//   *
//   * @param playerBoard, the player who wants to add a token.
//   * @param token, the token to add.
//   * @return true if the token can be added, false otherwise.
//   */
//  public boolean checkAddTokenOnTile(PlayerBoard playerBoard, Animals token) {
//    return playerBoard.tileNeighborMap().keySet().stream()
//            .filter(tile -> tile.animalToken() == Animals.DEFAULT)
//            .count()
//        > 1;
//  }
//
//  private List<Tile> wherePutToken(PlayerBoard playerBoard) {
//    return playerBoard.tileNeighborMap().keySet().stream()
//        .filter(tile -> tile.animalToken() == Animals.DEFAULT)
//        .toList();
//  }
//
//  /**
//   * Get a given amount of tokens from the bag.
//   *
//   * @param count, amount of tokens to get.
//   * @return List of animals, list of obtained tokens.
//   */
//  public List<Animals> drawTokens(int count) {
//    if (initGame.animalTokens().tokenList().size() < count) {
//      throw new IllegalStateException("Not enough tokens in the bag !");
//    }
//    List<Animals> drawnTokens = new ArrayList<>();
//    for (int i = 0; i < count; i++) {
//      Animals token = initGame.animalTokens().tokenList().get(0);
//      drawnTokens.add(token);
//      initGame.animalTokens().remove(token);
//    }
//    return drawnTokens;
//  }
//
//  /**
//   * Draw new tokens if the same animal appears 4 times.
//   * @param tokens, the tokens to check.
//   *
//   * @return List of animals, new tokens
//   */
//  public List<Animals> overpopulation(List<Animals> tokens) {
//    Map<Animals, Long> animalCounts =
//        tokens.stream().collect(Collectors.groupingBy(animal -> animal, Collectors.counting()));
//    while (animalCounts.values().stream().anyMatch(count -> count >= 3)) {
//      if (animalCounts.values().stream().anyMatch(count -> count == 3)) {
//        tokens = drawTokens(3);
//        break;
//      }
//      tokens = drawTokens(4);
//      animalCounts =
//          tokens.stream().collect(Collectors.groupingBy(animal -> animal, Collectors.counting()));
//    }
//    return tokens;
//  }
}
