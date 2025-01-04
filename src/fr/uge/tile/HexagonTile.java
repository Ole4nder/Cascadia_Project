package fr.uge.tile;

import fr.uge.animal.Animals;
import java.util.List;
import java.util.Objects;

public final class HexagonTile implements Tile {
  private final TileLandscape landscape1;
  private final TileLandscape landscape2;
  private final Animals animal1;
  private final Animals animal2;
  private Animals animalToken;

  /**
   * Create a new SquareTile with landscape, animals and coordinates.
   *
   * @param landscape1, the type of landscape on the tile.
   * @param landscape2, the type of landscape on the tile.
   * @param animal1, the first animal associated with the tile.
   * @param animal2, the second animal associated with the tile.
   */
  public HexagonTile(
      TileLandscape landscape1,
      TileLandscape landscape2,
      Animals animal1,
      Animals animal2,
      Animals animalToken) {
    this.landscape1 = Objects.requireNonNull(landscape1);
    this.landscape2 = Objects.requireNonNull(landscape2);
    this.animal1 = Objects.requireNonNull(animal1);
    this.animal2 = Objects.requireNonNull(animal2);
    this.animalToken = Objects.requireNonNull(animalToken);
  }

  /** Place an animal token on the tile. */
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
    return landscape1;
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
  public List<TileCoord> neighborPosition(TileCoord tileCoord) {
    return List.of();
  }

  @Override
  public boolean perfectHabitat() {
    return landscape1.equals(landscape2);
  }

  @Override
  public String toString() {
    return "SquareTile{"
        + "landscape1='"
        + landscape1
        + "landscape2='"
        + landscape2
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
    return Objects.hash(landscape1, landscape2, animal1, animal2, animalToken);
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof HexagonTile that
        && landscape1.equals(that.landscape1)
        && landscape2.equals(that.landscape2)
        && animal1 == that.animal1
        && animal2 == that.animal2
        && animalToken == that.animalToken;
  }
}
