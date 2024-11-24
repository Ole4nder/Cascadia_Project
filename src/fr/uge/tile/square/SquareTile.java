package fr.uge.tile.square;

import fr.uge.animal.Animals;
import fr.uge.tile.Tile;
import fr.uge.tile.coord.Coord;
import java.util.Objects;

public class SquareTile implements Tile {
  private final String landscape;
  private final Animals animal1;
  private final Animals animal2;
  private final Coord coord;
  private Animals animalToken;

  /**
   * Create a new SquareTile with landscape, animals and coordinates.
   *
   * @param landscape, the type of landscape on the tile.
   * @param animal1, the first animal associated with the tile.
   * @param animal2, the second animal associated with the tile.
   * @param coord, the coordinates of the tile on the board.
   */
  public SquareTile(
      String landscape, Animals animal1, Animals animal2, Coord coord, Animals animalToken) {
    Objects.requireNonNull(landscape);
    Objects.requireNonNull(animal1);
    Objects.requireNonNull(animal2);
    Objects.requireNonNull(coord);
    this.landscape = landscape;
    this.animal1 = animal1;
    this.animal2 = animal2;
    this.coord = coord;
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

  /**
   * Return the position of the neighbor tile.
   *
   * @param tile
   * @return the position of the neighbor tile.
   */
  @Override
  public int neighborPosition(Tile tile) {
    if (coord.equals(tile.coord())) {
      throw new AssertionError("The tiles are the same");
    }
    if (tile.coord().x() == coord.x() ^ tile.coord().y() == coord.y()) {
      if (coord.x() + 1 == tile.coord().x()) {
        return 1;
      } else if (coord.x() - 1 == tile.coord().x()) {
        return 3;
      } else if (coord.y() + 1 == tile.coord().y()) {
        return 2;
      } else if (coord.y() - 1 == tile.coord().y()) {
        return 0;
      }
    }
    return -1;
  }

  /**
   * Return the coordinates of the tile.
   * @return the coordinates of the tile.
   */
  @Override
  public Coord coord() {
    return coord;
  }

  // TODO : pourquoi c'est l'objet qui fait le taff de se montrer dans le terminal ???
  @Override
  public String toString() {
    var sb = new StringBuilder();
    String information;
    if (animalToken == Animals.DEFAULT) {
      information = animal1.toString() + "-" + animal2.toString();
    } else {
      information = animalToken.toString();
    }
    sb.repeat("*", landscape.length() + information.length() + 3)
        .append("\n")
        .append("*")
        .append(landscape)
        .append(" ")
        .append(information)
        .append("*\n")
        .repeat("*", landscape.length() + information.length() + 3)
        .append("\n");
    return sb.toString();
  }

  @Override
  public int hashCode() {
    return Objects.hash(coord, landscape, animal1, animal2, animalToken);
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof SquareTile tile
        && tile.coord.equals(coord)
        && tile.landscape.equals(landscape)
        && tile.animal1.equals(animal1)
        && tile.animal2.equals(animal2)
        && tile.animalToken.equals(animalToken);
  }
}
