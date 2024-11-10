package fr.uge.tile;

import java.util.Objects;

public record DepartTile(Tile tile1, Tile tile2, Tile tile3) {
  public DepartTile {
    Objects.requireNonNull(tile1);
    Objects.requireNonNull(tile2);
    Objects.requireNonNull(tile3);
  }

  @Override
  public final String toString() {
    return tile1.toString() + tile2.toString() + tile3.toString();
  }
}
