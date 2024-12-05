package fr.uge.game;

import fr.uge.animal.Animals;
import fr.uge.animal.card.AnimalCards;
import fr.uge.bag.AnimalsTokenBag;
import fr.uge.bag.DepartTileBag;
import fr.uge.bag.TileBag;
import fr.uge.board.BoardType;
import fr.uge.board.players.PlayerBoard;
import fr.uge.board.players.PlayersBoards;
import fr.uge.tile.DepartTile;
import fr.uge.tile.SquareTile;
import fr.uge.tile.TileCoord;
import fr.uge.tile.TileLandscape;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Scanner;

/** Start the game with the number of players and the variant chosen by the player. */
// TODO a voir si on le change ou non, (mettre directement les champs dans game et les méthodes en
// static)
public record InitGame(
        int playersNumbers,
        GameType gameType,
    BoardType boardType,
    TileBag tileBag,
    AnimalCards animalCards,
    DepartTileBag departTileBag,
    PlayersBoards playersBoards,
    AnimalsTokenBag animalTokenBag) {

  public InitGame {
    Objects.requireNonNull(gameType);
    Objects.requireNonNull(playersNumbers);
    Objects.requireNonNull(boardType);
    Objects.requireNonNull(tileBag);
    Objects.requireNonNull(animalCards);
    Objects.requireNonNull(departTileBag);
    Objects.requireNonNull(playersBoards);
    Objects.requireNonNull(animalTokenBag);
  }

  /**
   * Create a new game with players and variant type.
   *
   * @param playersNumbers, the number of players
   * @param gameType, the variant (family or intermediate)
   */
  public InitGame(int playersNumbers, GameType gameType, BoardType boardType) {
    this(playersNumbers, gameType, boardType, new TileBag(), new AnimalCards(), new DepartTileBag(), new PlayersBoards(), new AnimalsTokenBag());
  }

  public static GameType chooseGameVariant() {
    System.out.println(
        "Choose the game variant:\n - Type '1', 'family', or 'f' for Family\n - Type '2', 'intermediate', 'i', or 'intermediate' for Intermediate:");
    var scanner = new Scanner(System.in);
    var input = scanner.nextLine().trim().toLowerCase();

    switch (input) {
      case "family", "f", "1" -> {
        return GameType.FAMILY;
      }
      case "intermediate", "i", "2" -> {
        return GameType.INTERMEDIATE;
      }
      default -> {
        System.out.println("Invalid input! Please enter a valid variant.");
        return chooseGameVariant();
      }
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

  // TODO modifier la méthode pour implémenter les hexagones.
  /** Add all players to the game. */
  public void addAllPlayerAndDepartTile() {
    for (int i = 0; i < playersNumbers; i++) {
      var newPlayer = new PlayerBoard();
      newPlayer.addDepartTile(departTileBag.departTileBag().get(i), boardType);
      departTileBag.remove(departTileBag.departTileBag().get(i));
      playersBoards.add(newPlayer);
    }
  }

  /**
   * Add all tiles from the game.
   *
   * @throws IOException if the files are not found
   */
  private void addAllTileToBag() throws IOException {
    try (var reader =
        Files.newBufferedReader(Path.of("src/resources/tileDescription/tilesDescription.txt"))) {
      String line;
      while ((line = reader.readLine()) != null) {
        var parts = line.split(" : ");
        // Partie à changer pour les hexagones
        // TODO a améliorer pour prendre en compte le nombre de joueurs
        for (int i = 0;
            i < 17;
            i++) { // On a besoin de 85 tuiles en tout, avec 5 habitats différents, donc 17 tuiles
          // de chaque habitat.
          tileBag.add(
              (new SquareTile(
                      TileLandscape.landscapeNameToEnums(parts[0]),
                  Animals.animalNameToEnums(parts[1]),
                  Animals.animalNameToEnums(parts[2]),
                  new TileCoord(-1, -1),
                  Animals.DEFAULT)));
        }
      }
    }
    tileBag.shuffle();
  }

  /**
   * Add all depart tiles from the game.
   *
   * @throws IOException if the files are not found
   */
  private void addAllDepartTileToBag() throws IOException {
    try (var reader =
        Files.newBufferedReader(
            Path.of("src/resources/tileDescription/departTilesDescription.txt"))) {
      String line;
      while ((line = reader.readLine()) != null) {
        var parts = line.split(" : ");
        departTileBag.add(
            (new DepartTile(
                new SquareTile(
                    TileLandscape.landscapeNameToEnums(parts[1]),
                    Animals.animalNameToEnums(parts[2]),
                    Animals.animalNameToEnums(parts[3]),
                    new TileCoord(-1, -1),
                    Animals.DEFAULT),
                new SquareTile(
                    TileLandscape.landscapeNameToEnums(parts[4]),
                    Animals.animalNameToEnums(parts[5]),
                    Animals.animalNameToEnums(parts[6]),
                    new TileCoord(-1, -1),
                    Animals.DEFAULT),
                new SquareTile(
                    TileLandscape.landscapeNameToEnums(parts[7]),
                    Animals.animalNameToEnums(parts[8]),
                    Animals.animalNameToEnums(parts[9]),
                    new TileCoord(-1, -1),
                    Animals.DEFAULT))));
      }
    }
    departTileBag.shuffle();
  }

  /**
   * Add all items needed for the game.
   *
   * @throws IOException if the files are not found
   */
  public void initGame() throws IOException {
    addAllDepartTileToBag();
    addAllTileToBag();
    // TODO a revoir
    animalTokenBag.addAllToken();
    addAllPlayerAndDepartTile();
    animalCards.addAllAnimalCard();
  }

  /**
   * Getter for the players.
   *
   * @return PlayersBoards
   */
  public GameType gameType() {
    return gameType;
  }

  /**
   * Getter for the type of plateau.
   *
   * @return PlateauType
   */
  public BoardType boardType() {
    return boardType;
  }

  /**
   * Getter for the players.
   *
   * @return PlayersBoards
   */
  public PlayersBoards playersBoards() {
    return playersBoards;
  }

  /**
   * Getter for the tile bag.
   *
   * @return TileBag
   */
  public TileBag tileBag() {
    return tileBag;
  }

  /**
   * Getter for the animal cards.
   *
   * @return AnimalCards
   */
  public AnimalCards animalCards() {
    return animalCards;
  }

  /**
   * Getter for the animal token bag.
   *
   * @return AnimalsTokenBag
   */
  public AnimalsTokenBag animalTokenBag() {
    return animalTokenBag;
  }

  /**
   * Getter for the departure tile bag.
   *
   * @return DepartTileBag
   */
  public DepartTileBag departTileBag() {
    return departTileBag;
  }
}
