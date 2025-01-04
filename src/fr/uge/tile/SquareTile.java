package fr.uge.tile;

import fr.uge.animal.Animals;
import java.util.List;
import java.util.Objects;

public final class SquareTile implements Tile {
  private final TileLandscape landscape;
  private final Animals animal1;
  private final Animals animal2;
  private Animals animalToken;

  /**
   * Create a new SquareTile with landscape, animals and coordinates.
   *
   * @param landscape, the type of landscape on the tile.
   * @param animal1, the first animal associated with the tile.
   * @param animal2, the second animal associated with the tile.
   */
  public SquareTile(
      TileLandscape landscape, Animals animal1, Animals animal2, Animals animalToken) {
    this.landscape = Objects.requireNonNull(landscape);
    this.animal1 = Objects.requireNonNull(animal1);
    this.animal2 = animal2;
    this.animalToken = animalToken;
  }

  /** Place an animal token on the tile. */
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

  @Override
  public List<TileCoord> neighborPosition(TileCoord tileCoord) {
    Objects.requireNonNull(tileCoord);
    return List.of(
        new TileCoord(tileCoord.x() + 1, tileCoord.y()),
        new TileCoord(tileCoord.x() - 1, tileCoord.y()),
        new TileCoord(tileCoord.x(), tileCoord.y() + 1),
        new TileCoord(tileCoord.x(), tileCoord.y() - 1));
  }

  @Override
  public boolean perfectHabitat() {
    // always perfect habitat because he as only one landscape
    return true;
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
    return Objects.hash(landscape, animal1, animal2, animalToken);
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof SquareTile tile
        && tile.landscape.equals(landscape)
        && tile.animal1.equals(animal1)
        && tile.animal2.equals(animal2)
        && tile.animalToken.equals(animalToken);
  }
}
