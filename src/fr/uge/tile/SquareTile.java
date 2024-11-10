package fr.uge.tile;

import fr.uge.animal.Animals;
import java.util.Objects;

public record SquareTile(String landscape, Animals animal1, Animals animal2, int sizeTile) {
  public SquareTile {
    Objects.requireNonNull(landscape);
    Objects.requireNonNull(animal1);
    Objects.requireNonNull(animal2);
  }

  @Override
  public String toString() {
    var sb = new StringBuilder();
    sb.repeat("*", sizeTile)
        .append("\n")
        .append("*")
        .append(landscape)
        .append("*\n")
        .repeat("*", sizeTile);
    return sb.toString();
  }
}
