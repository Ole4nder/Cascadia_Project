package fr.uge.game;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

import fr.uge.animal.Animals;
import fr.uge.animal.card.AnimalCardImpl;
import fr.uge.animal.token.AnimalToken;
import fr.uge.player.Player;
import fr.uge.tile.DepartTile;
import fr.uge.tile.Tile;
import fr.uge.tile.square.CordSquareTile;
import fr.uge.tile.square.SquareTile;

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
   * Return copy list of 
	 *
   * @return List<Player>
   */
  public List<Player> players(){
	  return List.copyOf(players);
  }
  
  /**
   * Return copy list of tile 
   * 
   * @return List<Tile>
   */
  public List<Tile> tiles(){
	  return List.copyOf(tiles);
  }
  
  
  /**
   * Return copy list of departTile
   * 
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
   * Getter for the type of game
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
  
  /**
   * Removing not used tiles from the list in order to play with the right amount.
   */
  private void amountTileTypeGame() {
	  for (int i = tileGame() - 1; i >= 0; i--) {
		  tiles.remove(i);
	  }
  }
  
  /**
   * Ask the player which variant he wants to play.
   * 
   * @return GameType, variant chosen by the player.
   */
  public static GameType chooseGameVariant() {
    Scanner scanner = new Scanner(System.in);	// j'ai un warning sur scanner, je comprends pas pourquoi ?
    System.out.println("Choose the game variant.\nType 1 for Family or 2 for Intermediate :");
    
    switch (scanner.nextInt()) {
      case 1:
        return GameType.FAMILY;
      case 2:
        return GameType.INTERMEDIATE;
      default:
        System.out.println("Invalid choice! Please enter 1 for Family or 2 for Intermediate.");
        return chooseGameVariant();
    }
  }
  
  /**
   * Ask the player how many players want to play the game.
   * 
   * @return int, number of players chosen by the player.
   */
  public static int chooseNumberOfPlayers() {
      Scanner scanner = new Scanner(System.in);
      System.out.println("Enter the number of players (1-4):");
      int numberOfPlayers = scanner.nextInt();
      
      if (numberOfPlayers >= 1 && numberOfPlayers <= 4) {
          return numberOfPlayers;
      } else {
          System.out.println("Invalid number of players! Please enter a number between 1 and 4.");
          return chooseNumberOfPlayers();
      }
  }
  
  /**
   * Get a given amount of tokens from the bag.
   * 
   * @param count, amount of tokens to get.
   * @return List<Animals>, list of obtained tokens. 
   */
  public List<Animals> drawTokens(int count) {
		Objects.requireNonNull(animalTokens);
		if (animalTokens.tokenList().size() < count) {
		  throw new IllegalStateException("Not enough tokens in the bag !");
		}
		List<Animals> drawnTokens = new ArrayList<>();
		for (int i = 0; i < count; i++) {
	    Animals token = animalTokens.tokenList().get(0);
	    drawnTokens.add(token);
	    animalTokens.remove(token);
		}
		return drawnTokens;
  }
  
  /*TODO: a adapter surpopulation de 3. Désolé tout ce que j'ai testé n'a pas marché
   * j'ai viré mes fonctions parce que vraiment je commençais à écrire de la merde.
   */
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
    //while (distinctTokens(tokens) == 1) {  	// TODO: a voir pour la surpopulation de 3
    while (tokens.stream().distinct().count() == 1) {
      tokens = drawTokens(4);
    }
    return tokens;
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
