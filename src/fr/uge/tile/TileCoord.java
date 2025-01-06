package fr.uge.tile;

/**
 * Represents a tile coordinate, the center of the tile.
 *
 * @param x the x coordinate.
 * @param y the y coordinate.
 */
public record TileCoord(int x, int y) {
  @Override
  public String toString() {
    return '(' + "x=" + x + ", y=" + y + ')';
  }
}
