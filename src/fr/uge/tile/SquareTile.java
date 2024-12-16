package fr.uge.tile;

import fr.uge.animal.Animals;
import java.util.Objects;

public class SquareTile implements Tile {
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
  public SquareTile(
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

  /**
   * Place an animal token on the tile.
   */
  public void setAnimalToken(Animals animal) {
    Objects.requireNonNull(animal);
    System.out.println(animal.getName());
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

  /**
   * Return the position of the neighbor tile.
   *
   * @param tile the tile to check.
   * @return the position of the neighbor tile.
   */
  @Override
  public int neighborPosition(Tile tile) {
    if (tileCoord.equals(tile.coord())) {
      throw new AssertionError("The tiles are the same");
    }
    if (tile.coord().x() == tileCoord.x() ^ tile.coord().y() == tileCoord.y()) {
      if (tileCoord.x() + 1 == tile.coord().x()) {
        return 1;
      } else if (tileCoord.x() - 1 == tile.coord().x()) {
        return 3;
      } else if (tileCoord.y() + 1 == tile.coord().y()) {
        return 2;
      } else if (tileCoord.y() - 1 == tile.coord().y()) {
        return 0;
      }
    }
    return -1;
  }

  /**
   * Return the coordinates of the tile.
   *
   * @return the coordinates of the tile.
   */
  @Override
  public TileCoord coord() {
    return tileCoord;
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
    return Objects.hash(tileCoord, landscape, animal1, animal2, animalToken);
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof SquareTile tile
        && tile.tileCoord.equals(tileCoord)
        && tile.landscape.equals(landscape)
        && tile.animal1.equals(animal1)
        && tile.animal2.equals(animal2)
        && tile.animalToken.equals(animalToken);
  }
}
