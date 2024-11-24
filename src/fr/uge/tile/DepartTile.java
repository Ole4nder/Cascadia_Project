package fr.uge.tile;

import java.util.Objects;

/**
 * Represents a starting tile configuration for a player.
 *
 * @param tile1 if the play at square is the first tile is left but if they play at the hexagon tile
 *     represent the down left
 * @param tile2 if the play at square is the second tile is middle but if they play at the hexagon
 *     tile represent the up middle
 * @param tile3 if the play at square is the third tile is right but if they play at the hexagon
 *     tile represent the down right
 */
public record DepartTile(Tile tile1, Tile tile2, Tile tile3) {
  public DepartTile {
    Objects.requireNonNull(tile1);
    Objects.requireNonNull(tile2);
    Objects.requireNonNull(tile3);
  }
}
