package fr.uge.tile;

/**
 * Represents a tile coordinate.
 * @param x
 * @param y
 */
public record TileCoord(int x, int y) {
  @Override
  public String toString() {
    return '(' + "x=" + x + ", y=" + y + ')';
  }
}
