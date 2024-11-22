package fr.uge.tile.square;

import fr.uge.animal.Animals;
import fr.uge.tile.Tile;
import java.util.Objects;

public record SquareTile(String landscape, Animals animal1, Animals animal2, CordSquareTile cord, Animals animalToken) implements Tile {

  /**
   * Create a new SquareTile with landscape, animals and coordinates.
   *
   * @param landscape, the type of landscape on the tile.
   * @param animal1, the first animal associated with the tile.
   * @param animal2, the second animal associated with the tile.
   * @param cord, the coordinates of the tile on the board.
   */
  public SquareTile {
    Objects.requireNonNull(landscape);
    Objects.requireNonNull(animal1);
    Objects.requireNonNull(animal2);
    Objects.requireNonNull(cord);
  }

  /**
   * Place an animal token on the tile if no token is already placed.
   *
   * @param animal, the animal token to place on the tile.
   */
  public SquareTile setAnimalToken(Animals animal) {
    Objects.requireNonNull(animal);
    if (animalToken ==  Animals.DEFAULT) {
      return new SquareTile(landscape, animal1, animal2, cord, animal);
    }
	return this;
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
