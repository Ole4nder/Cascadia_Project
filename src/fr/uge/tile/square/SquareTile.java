package fr.uge.tile.square;

import fr.uge.animal.Animals;
import fr.uge.tile.Tile;
import java.util.Objects;

public class SquareTile implements Tile {
  private final String landscape;
  private final Animals animal1;
  private final CordSquareTile cord;
  private Animals animal2;
  private Animals animalToken;

  /**
   * Create a new SquareTile with landscape, animals and coordinates.
   *
   * @param landscape, the type of landscape on the tile.
   * @param animal1, the first animal associated with the tile.
   * @param animal2, the second animal associated with the tile.
   * @param cord, the coordinates of the tile on the board.
   */
  public SquareTile(
      String landscape,
      Animals animal1,
      Animals animal2,
      CordSquareTile cord,
      Animals animalToken) {
    Objects.requireNonNull(landscape);
    Objects.requireNonNull(animal1);
    Objects.requireNonNull(animal2);
    Objects.requireNonNull(cord);
    this.landscape = landscape;
    this.animal1 = animal1;
    this.animal2 = animal2;
    this.cord = cord;
    this.animalToken = animalToken;
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

  @Override
  public Animals animalToken() {
    return animalToken;
  }

  @Override
  public int numberNeighbors() {
    return 4;
  }

  @Override
  public String toString() {
    var sb = new StringBuilder();
    sb.repeat("*", landscape.length() + 2)
        .append("\n")
        .append("*")
        .append(landscape)
        .append("*\n")
        .repeat("*", landscape.length() + 2)
        .append("\n");
    return sb.toString();
  }

  @Override
  public CordSquareTile cord() {
    return cord;
  }

  @Override
  public int hashCode() {
    return Objects.hash(cord, landscape, animal1, animal2, animalToken);
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof SquareTile tile
        && tile.cord.equals(cord)
        && tile.landscape.equals(landscape)
        && tile.animal1.equals(animal1)
        && tile.animal2.equals(animal2)
        && tile.animalToken.equals(animalToken);
  }
}
