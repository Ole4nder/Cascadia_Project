package fr.uge.game;

import fr.uge.animal.Animals;
import fr.uge.animal.card.AnimalCardImpl;
import fr.uge.animal.token.AnimalToken;
import fr.uge.player.Player;
import fr.uge.tile.DepartTile;
import fr.uge.tile.Tile;
import fr.uge.tile.square.CordSquareTile;
import fr.uge.tile.square.SquareTile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class StartGame {
  private final int playersNumbers;
  private final GameType gameType;
  private final AnimalCardImpl animalCards = new AnimalCardImpl();
  private final AnimalToken animalTokens = new AnimalToken();
  //changer les listes en liste chainées ? pour éviter de décalage mémoire avec l'ajout et suppression récurrente
  private final List<Player> players = new ArrayList<>(); 
  private final List<Tile> tiles = new ArrayList<>();
  private final List<DepartTile> departTiles = new ArrayList<>();
  private final static int MAX_TILE = 85;
  
  /**
   * Return copy list of player
   * @return List<Player>
   */
  public List<Player> players(){
	  return List.copyOf(players);
  }
  
  /**
   * Return copy list of tile 
   * @return List<Tile>
   */
  public List<Tile> tiles(){
	  return List.copyOf(tiles);
  }
  
  
  /**
   * Return copy list of departTile
   * @return List<DepartTile>
   */
  public List<DepartTile> departTiles(){
	  return List.copyOf(departTiles);
  }

  /**
   * Create a new game with players and variant type.
   *
   * @param playersNumbers, the number of players
   * @param gameType, the variant (family or intermediate)
   */
  public StartGame(int playersNumbers, GameType gameType) {
    this.playersNumbers = playersNumbers;
    this.gameType = gameType;
  }

  /**
   * getter for the type of game
   *
   * @return GameType
   */
  public GameType gameType() {
    return gameType;
  }

  /**
   * Add a player to the game.
   *
   * @param player, the player to add
   */
  private void addPlayer(Player player) {
    Objects.requireNonNull(player);
    players.add(player);
  }

  /** Add all players to the game. */
  public void addAllPlayer() {
    var random = new Random();
    for (int i = 0; i < playersNumbers; i++) {
      var randomInt = random.nextInt(departTiles.size());
      addPlayer(new Player(departTiles.get(randomInt)));
      departTiles.remove(departTiles.get(randomInt));
    }
  }

  /**
   * Calculate the total of tiles required for the game.
   *
   * @return number of tiles
   */
  public int tileGame() {
    return MAX_TILE - (playersNumbers * 20 + 3);
  }

  /**
   * Add a tile to the game.
   *
   * @param tile, the tile to add
   */
  public void addTile(Tile tile) {
    Objects.requireNonNull(tile);
    tiles.add(tile);
  }

  /**
   * Remove a tile from the game.
   *
   * @param tile, the tile to remove
   */
  public void removeTile(Tile tile) {
    Objects.requireNonNull(tile);
    tiles.remove(tile);
  }
  
  // TODO modifier la méthode pour implémenter les hexagones.
  /**
   * Add all tiles from the game.
   * 
   * @throws IOException
   */
  private void addAllTile() throws IOException {
    try(var reader = Files.newBufferedReader(Path.of("ressources/tileDescription/tilesDescription.txt"))) {
    	String line;
    	while ((line = reader.readLine()) != null) {
    		var parts = line.split(" : ");
    		// Partie à changer pour les hexagones
            //TODO a améliorer pour prendre en compte le nombre de joueurs
    		for(int i = 0; i < 17; i++) { // On a besoin de 85 tuiles en tout, avec 5 habitats différents, donc 17 tuiles de chaque habitat.
    			addTile(new SquareTile(parts[0], Animals.animalNameToEnums(parts[1]), Animals.animalNameToEnums(parts[2]), new CordSquareTile(-1, -1), Animals.DEFAULT));
    		}
    	}
    }
    Collections.shuffle(tiles);
  }

  /**
   * Add a depart tile.
   * 
   * @param departTile
   */
  private void addDepartTile(DepartTile departTile) {
    Objects.requireNonNull(departTile);
    departTiles.add(departTile);
  }

  /**
   * Add all depart tiles from the game.
   * 
   * @throws IOException
   */
  private void addAllDepartTile() throws IOException {
    try(var reader = Files.newBufferedReader(Path.of("ressources/tileDescription/departTilesDescription.txt"))) {
    	String line;
    	while ((line = reader.readLine()) != null) {
    		var parts = line.split(" : ");
    		addDepartTile(new DepartTile(
    				new SquareTile(parts[1], Animals.animalNameToEnums(parts[2]), Animals.animalNameToEnums(parts[3]), new CordSquareTile(-1, -1), Animals.DEFAULT),
    				new SquareTile(parts[4], Animals.animalNameToEnums(parts[5]), Animals.animalNameToEnums(parts[6]), new CordSquareTile(-1, -1), Animals.DEFAULT),
    				new SquareTile(parts[7], Animals.animalNameToEnums(parts[8]), Animals.animalNameToEnums(parts[9]), new CordSquareTile(-1, -1), Animals.DEFAULT)
    		));
    	}
    }
    Collections.shuffle(departTiles);
  }
  
  private void amountTileTypeGame() {
	  for (int i = 0; i < tileGame(); i++) {
		  tiles.remove(i);
	}
  }
  
  /**
   * Add all items needed for the game.
   * 
   * @throws IOException
   */
  public void initGame() throws IOException {
  	addAllDepartTile();
  	addAllPlayer();
  	addAllTile();
  	animalTokens.addAllToken();
  	animalCards.addAllAnimalCard();
  	amountTileTypeGame();
  }
}
