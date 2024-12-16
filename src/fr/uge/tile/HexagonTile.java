package fr.uge.tile;

import fr.uge.animal.Animals;
import java.util.Objects;

public class HexagonTile implements Tile {
  private final TileLandscape landscape;
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
      TileLandscape landscape,
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
   * Place an animal token on the tile.
   */
  public void setAnimalToken(Animals animal) {
    Objects.requireNonNull(animal);
    this.animalToken = animal;
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
  public TileLandscape landscape() {
    return landscape;
  }

  /**
   * Return the first animal on the tile.
   *
   * @return the first animal on the tile.
   */
  @Override
  public Animals animals1() {
    return animal1;
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

  // TODO faire la méthode
  @Override
  public int neighborPosition(Tile tile) {
    return 0;
  }

  @Override
  public String toString() {
    return "SquareTile{"
        + "landscape='"
        + landscape
        + '\''
        + ", animal1="
        + animal1
        + ", animal2="
        + animal2
        + ", animalToken="
        + animalToken
        + '}';
  }

  @Override
  public int hashCode() {
    return Objects.hash(landscape, animal1, animal2, tileCoord, animalToken);
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof HexagonTile that
        && landscape.equals(that.landscape)
        && animal1 == that.animal1
        && animal2 == that.animal2
        && tileCoord.equals(that.tileCoord)
        && animalToken == that.animalToken;
  }
}
