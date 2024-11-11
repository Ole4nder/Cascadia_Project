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

  public StartGame(int playersNumbers, GameType gameType) {
    this.playersNumbers = playersNumbers;
    this.gameType = gameType;
  }

  public void addPlayer(Player player) {
    Objects.requireNonNull(player);
    players.add(player);
  }

  private int tileGame(){
    return playersNumbers * 20 + 3;
  }

  public void addTile(Tile tile){
    Objects.requireNonNull(tile);
    tiles.add(tile);
  }

  public void removeTile(Tile tile){
    Objects.requireNonNull(tile);
    tiles.remove(tile);
  }

  //TODO faire la méthode + voir comment ajouter des tuiles de base pour le jeu
  public void addAllTIle(){
  }

  private void shuffleTile(){
    Collections.shuffle(tiles);
  }

}
