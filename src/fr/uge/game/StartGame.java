package fr.uge.game;

import fr.uge.animal.token.AnimalToken;
import fr.uge.player.Player;
import fr.uge.tile.Tile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class StartGame {
  private final int playersNumbers;
  private final GameType gameType;
  private final List<Player> players = new ArrayList<>();
  private final AnimalToken animalToken = new AnimalToken();
  private final List<Tile> tiles = new ArrayList<>();

  
  /**
   * Create a new game with players and variant type.
   *
   * @param playersNumbers, the number of players
   * @param gameType,       the variant (family or intermediate)
   */
  public StartGame(int playersNumbers, GameType gameType) {
    this.playersNumbers = playersNumbers;
    this.gameType = gameType;
  }

  /**
   * Add a player to the game.
   *
   * @param player, the player to add
   */
  public void addPlayer(Player player) {
    Objects.requireNonNull(player);
    players.add(player);
  }

  /**
   * Calculate the total of tiles required for the game.
   *
   * @return number of tiles
   */
  private int tileGame(){
    return playersNumbers * 20 + 3;
  }

  /**
   * Add a tile to the game.
   *
   * @param tile, the tile to add
   */
  public void addTile(Tile tile){
    Objects.requireNonNull(tile);
    tiles.add(tile);
  }

  /**
   * Remove a tile from the game.
   *
   * @param tile, the tile to remove
   */
  public void removeTile(Tile tile){
    Objects.requireNonNull(tile);
    tiles.remove(tile);
  }

  //TODO faire la méthode + voir comment ajouter des tuiles de base pour le jeu
  public void addAllTIle(){
  }

  /**
   * Shuffles the tiles in a random order.
   */
  private void shuffleTile(){
    Collections.shuffle(tiles);
  }

}
