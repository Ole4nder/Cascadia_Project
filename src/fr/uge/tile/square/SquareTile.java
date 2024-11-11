package fr.uge.tile.square;

import fr.uge.animal.Animals;
import fr.uge.tile.Tile;
import java.util.Objects;

public class SquareTile implements Tile {
  private final String landscape;
  private final Animals animal1;
  private final Animals animal2;
  private final CordSquareTile cord;
  private Animals animaltoken;

  public SquareTile(String landscape, Animals animal1, Animals animal2, CordSquareTile cord) {
    Objects.requireNonNull(landscape);
    Objects.requireNonNull(animal1);
    Objects.requireNonNull(animal2);
    Objects.requireNonNull(cord);
    this.landscape = landscape;
    this.animal1 = animal1;
    this.animal2 = animal2;
    this.cord = cord;
    this.animaltoken = null;
  }

  public Animals getAnimalToken() {
    return animaltoken;
  }

  public void setAnimalToken(Animals animal) {
    Objects.requireNonNull(animal);
    if (animaltoken == null) {
      this.animaltoken = animal;
    }
  }

  public CordSquareTile getCord() {
    return cord;
  }

  public String getLandscape() {
    return landscape;
  }

  public Animals getAnimal1() {
    return animal1;
  }

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
