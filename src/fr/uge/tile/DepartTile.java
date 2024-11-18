package fr.uge.tile;

import java.util.Objects;

public record DepartTile(Tile tile1, Tile tile2, Tile tile3) {
  public DepartTile {
    Objects.requireNonNull(tile1);
    Objects.requireNonNull(tile2);
    Objects.requireNonNull(tile3);
  }

  // TODO changer de place la méthode
  /**
   * Generates a single-row string representation of multiple tiles placed side by side.
   *
   * @param tiles the tiles to include in the row.
   * @return a string representing the tiles aligned in a row.
   */
  public static String generateRowOfTiles(Tile... tiles) {
    var sb = new StringBuilder();
    String[] lines = new String[3];

    // Récupère chaque ligne d'une tuile et les ajoute côte à côte
    for (Tile tile : tiles) {
      String[] tileLines = tile.toString().split("\n");
      for (int i = 0; i < 3; i++) {
        lines[i] = (lines[i] == null ? "" : lines[i] + " ") + tileLines[i];
      }
    }

    for (String line : lines) {
      sb.append(line).append("\n");
    }

    return sb.toString();
  }
  //TODO check si on peut enlever la méthode
  @Override
  public final String toString() {
    return tile1.toString() + tile2.toString() + tile3.toString();
  }
}
