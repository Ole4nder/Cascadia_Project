package fr.uge.game;

import fr.uge.animal.Animals;
import fr.uge.board.players.PlayerBoard;
import fr.uge.graphic.Graphic;
import fr.uge.interaction.Interaction;
import fr.uge.option.OptionList;
import fr.uge.option.OptionOverpopulation;
import fr.uge.option.OptionTileToken;
import fr.uge.tile.Tile;
import fr.uge.tile.TileCoord;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents a game with a start configuration. startGame = the start configuration of the game.
 * numberTurns = the number of turns played. graphic = the graphic interface of the game.
 * interaction = the interaction interface of the game.
 */
public class Game {
  private static final int MAX_TURN = 20;
  private final InitGame initGame;
  private final Graphic graphic;
  private final Interaction interaction;
  private final OptionList optionList = new OptionList();
  private final OptionOverpopulation optionOverpopulation = new OptionOverpopulation();
  private int numberTurns = 0;

  public Game(InitGame initGame, Graphic graphic, Interaction interaction) {
    Objects.requireNonNull(initGame);
    this.initGame = initGame;
    this.graphic = graphic;
    this.interaction = interaction;
  }

  public InitGame startGame() {
    return initGame;
  }

  /**
   * End the game if there are no more tiles or if the number of turns is 20.
   *
   * @return true if the game is over, false otherwise.
   */
  public boolean endTheGame() {
    return initGame.tileBag().tileBag().isEmpty() || numberTurns == MAX_TURN;
  }

  /** Game loop. */
  public void play() {
    createAllOption(initGame.tileBag().drawTile(4), initGame.animalTokenBag().drawAnimal(4));
    while (!endTheGame()) {
      var valueOverpopulation = optionOverpopulation.asOverpopulation();
      if (valueOverpopulation == 4
          || (valueOverpopulation == 3 && interaction.wantChangeOption())) {
        overpopulation();
      }
      playerTurns();
    }
  }

  /** Players take turns to play. */
  public void playerTurns() {
    for (var player : initGame.playersBoards().playerBoardsList()) {
      graphic.drawGameBoard(new HashSet<>(player.board().values())); // TODO : change this method bad draw
      var option = asOptionTileTokenPlayer();
      updateOption(option);
      // TODO: add tile to player
      asTileTokenPlayer(player, option);
    }
    numberTurns++;
  }

  /**
   * create board option for player
   *
   * @param tiles the tiles to choose from
   * @param tokens the tokens to choose from
   */
  private void createAllOption(List<Tile> tiles, List<Animals> tokens) {
    if (tiles.size() != tokens.size()) {
      throw new IllegalStateException("size of tiles and tokens are different");
    }
    tokens.forEach(optionOverpopulation::add);
    IntStream.range(0, tiles.size())
        .mapToObj(i -> new OptionTileToken(tiles.get(i), tokens.get(i)))
        .forEach(optionList::add);
  }

  private OptionTileToken asOptionTileTokenPlayer() {
    return interaction.choiceOption(optionList, graphic);
  }

  private void updateOption(OptionTileToken option) {
    optionList.remove(option);
    optionOverpopulation.remove(option.token());
    var token = initGame.animalTokenBag().remove(0);
    optionOverpopulation.add(token);
    optionList.add(new OptionTileToken(initGame.tileBag().remove(0), token));
  }

  /**
   * Choose a tile and a token for a player.
   *
   * @param playerBoard the player who wants to choose a tile and a token.
   */
  // TODO : correct this method
  private void asTileTokenPlayer(PlayerBoard playerBoard, OptionTileToken option) {
    TileCoord tileCoord;
    Tile tileToken;
    switch (interaction.choosePutTileToken()) {
      case "tile" -> {
        // TODO : add tile to player
        //        graphic.drawTileToTokenAndTile(wherePutToken(playerBoard));
        //        tileToken = interaction.choosePutToken(wherePutToken(playerBoard), graphic);
        //        putToken(playerBoard, tileToken, option.token());
      }
      case "token" -> {
        graphic.drawTileToTokenAndTile(wherePutToken(playerBoard));
        tileToken = interaction.choosePutToken(wherePutToken(playerBoard), graphic);
        putToken(tileToken, option.token());
        // TODO : add tile to player
      }
    }
  }

  /**
   * Put a token on a tile.
   *
   * @param tile the tile where the token will be put.
   * @param token the token to put.
   */
  private void putToken(Tile tile, Animals token) {
    System.out.println(checkAddTokenOnTile(tile, token));
    if (checkAddTokenOnTile(tile, token)) {
      tile.setAnimalToken(token);
    } else {
      initGame.animalTokenBag().add(token);
    }
  }

  /**
   * Check if a token can be added on a tile.
   *
   * @return true if the token can be added, false otherwise.
   */
  public boolean checkAddTokenOnTile(Tile tile, Animals tokens) {
    return tile.animalToken() == Animals.DEFAULT
        && (tokens == tile.animals1() || tokens == tile.animals2());
  }

  /**
   * Get the tiles where a player can put a token.
   *
   * @param playerBoard, the player who wants to put a token.
   * @return List<Tile>, the tiles where a player can put a token.
   */
  public List<Tile> wherePutToken(PlayerBoard playerBoard) {
    return playerBoard.board().values().stream()
        .filter(tile -> tile.animalToken() == Animals.DEFAULT)
        .collect(Collectors.toList());
  }

  /**
   * Get a given amount of tokens from the bag.
   *
   * @param count, amount of tokens to get.
   */
  private void drawTokens(int count) {
    if (initGame.animalTokenBag().tokenBag().size() < count) {
      // TODO : maybe change the throw with end of the game ?
      throw new IllegalStateException("Not enough tokens in the bag !");
    }
    var animalOverpopulation = optionOverpopulation.getAnimalOverpopulation(count);
    optionOverpopulation.reset();
    for (int i = 0; i < count; i++) {
      var newToken = initGame.animalTokenBag().remove(0);
      optionOverpopulation.add(newToken);
      if (animalOverpopulation == optionList.optionTileTokenList().get(i).token()) {
        optionList.optionTileTokenList().get(i).changeToken(newToken);
      }
    }
  }

  /** Draw new tokens if the same animal appears 4 or 3 times. */
  private void overpopulation() {
    while (true) {
      var overpopulationStatus = optionOverpopulation.asOverpopulation();
      if (overpopulationStatus == 3) {
        drawTokens(3); // Gérer la surpopulation pour 3 tokens identiques
        break; // Traiter la surpopulation pour 3 uniquement une fois
      } else if (overpopulationStatus == 4) {
        drawTokens(4); // Gérer la surpopulation pour 4 tokens identiques
      } else {
        break; // Pas de surpopulation
      }
    }
  }
}
