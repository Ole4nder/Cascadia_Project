package fr.uge.tile.square;

import fr.uge.animal.Animals;
import fr.uge.tile.Tile;
import java.util.Objects;

public class SquareTile implements Tile {
  private final String landscape;
  private final Animals animal1;
  private final Animals animal2;
  private final CordSquareTile cord;
  private Animals animalToken;

  /**
   * Create a new SquareTile with landscape, animals and coordinates.
   *
   * @param landscape, the type of landscape on the tile.
   * @param animal1, the first animal associated with the tile.
   * @param animal2, the second animal associated with the tile.
   * @param cord, the coordinates of the tile on the board.
   */
  public SquareTile(String landscape, Animals animal1, Animals animal2, CordSquareTile cord) {
    Objects.requireNonNull(landscape);
    Objects.requireNonNull(animal1);
    Objects.requireNonNull(animal2);
    Objects.requireNonNull(cord);
    this.landscape = landscape;
    this.animal1 = animal1;
    this.animal2 = animal2;
    this.cord = cord;
    this.animalToken = null;
  }

  /**
   * Retrieves the animal token placed on the tile.
   *
   * @return the animal token or null if no token is placed.
   */
  public Animals getAnimalToken() {
    return animalToken;
  }

  /**
   * Place an animal token on the tile if no token is already placed.
   *
   * @param animal, the animal token to place on the tile.
   */
  public void setAnimalToken(Animals animal) {
    Objects.requireNonNull(animal);
    if (animalToken == null) {
      this.animalToken = animal;
    }
  }

  /**
   * Retrieve the coordinates of the tile.
   *
   * @return the coordinates of the tile.
   */
  public CordSquareTile getCord() {
    return cord;
  }

  /**
   * Retrieve the type of landscape on the tile.
   *
   * @return the landscape of the tile.
   */
  public String getLandscape() {
    return landscape;
  }

  /**
   * Retrieve the first animal associated with the tile.
   *
   * @return the first animal.
   */
  public Animals getAnimal1() {
    return animal1;
  }

  /**
   * Retrieve the second animal associated with the tile.
   *
   * @return the second animal.
   */
  public Animals getAnimal2() {
    return animal2;
  }

  // TODO check si on peut enlever la méthode
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
}
