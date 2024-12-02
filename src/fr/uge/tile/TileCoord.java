package fr.uge.tile;

import java.util.Comparator;
import java.util.Objects;

/**
 * Class to represent Coord into two ints
 */
public class TileCoord {
  public static final Comparator<TileCoord> COMPARATOR =
      Comparator.comparingInt(TileCoord::x).thenComparingInt(TileCoord::y);
  private int x;
  private int y;

  public TileCoord(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int x() {
    return x;
  }

  public int y() {
    return y;  }

  public void changeCord(int newX, int newY) {
    this.x = newX;
    this.y = newY;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof TileCoord that)) return false;
    return x == that.x && y == that.y;
  }

  @Override
  public String toString() {
    return '(' + "x=" + x + ", y=" + y + ')';
  }
}
