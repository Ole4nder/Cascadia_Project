package fr.uge.tile;

import fr.uge.animal.Animals;
import java.util.Objects;

public class HexagonTile implements Tile {
  private final String landscape;
  private final Animals animal1;
  private final Animals animal2;
  private final TileCoord tileCoord;
  private Animals animalToken;

  /**
   * Create a new SquareTile with landscape, animals and coordinates.
   *
   * @param landscape, the type of landscape on the tile.
   * @param animal1, the first animal associated with the tile.
   * @param animal2, the second animal associated with the tile.
   * @param tileCoord, the coordinates of the tile on the board.
   */
  public HexagonTile(
      String landscape,
      Animals animal1,
      Animals animal2,
      TileCoord tileCoord,
      Animals animalToken) {
    Objects.requireNonNull(landscape);
    Objects.requireNonNull(animal1);
    Objects.requireNonNull(animal2);
    Objects.requireNonNull(tileCoord);
    this.landscape = landscape;
    this.animal1 = animal1;
    this.animal2 = animal2;
    this.tileCoord = tileCoord;
    this.animalToken = animalToken;
  }

  @Override
  public TileCoord coord() {
    return tileCoord;
  }

  /**
   * Place an animal token on the tile if no token is already placed and the animal correspond to
   * animals in tile.
   *
   * @param animal, the animal token to place on the tile.
   * @return true if the token was placed, false otherwise.
   */
  public boolean setAnimalToken(Animals animal) {
    Objects.requireNonNull(animal);
    if (animalToken == Animals.DEFAULT && (animal == animal1 || animal == animal2)) {
      this.animalToken = animal;
      return true;
    }
    return false;
  }

  /**
   * Return the animal token on the tile.
   *
   * @return the animal token on the tile.
   */
  @Override
  public Animals animalToken() {
    return animalToken;
  }

  /**
   * Return the landscape of the tile.
   *
   * @return the landscape of the tile.
   */
  @Override
  public String landscape() {
    return landscape;
  }

  /**
   * Return the first animal on the tile.
   *
   * @return the first animal on the tile.
   */
  @Override
  public Animals animals1() {
    return animal2;
  }

  /**
   * Return the second animal on the tile.
   *
   * @return the second animal on the tile.
   */
  @Override
  public Animals animals2() {
    return animal2;
  }

  //TODO faire la méthode
  @Override
  public int neighborPosition(Tile tile) {
    return 0;
  }
}