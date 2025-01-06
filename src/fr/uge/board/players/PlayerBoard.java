package fr.uge.board.players;

import fr.uge.tile.*;
import java.util.*;
import java.util.stream.Collectors;

/** Represents a player in the game. */
public class PlayerBoard {
  private final Map<TileCoord, Tile> board =
      new TreeMap<>(Comparator.comparing(TileCoord::x).thenComparing(TileCoord::y));

  /**
   * Adds a neighboring relationship between two tiles.
   *
   * @param tile the main tile
   * @param tileCoord the coordinates of the tile
   */
  public void add(TileCoord tileCoord, Tile tile) {
    Objects.requireNonNull(tile);
    Objects.requireNonNull(tileCoord);
    board.put(tileCoord, tile);
  }

  /**
   * Get all neighbors cord of Tile.
   *
   * @return Set of the cord tile neighbors
   */
  public Set<TileCoord> getAllNeighborCord() {
    return board.entrySet().stream()
        .map(t -> t.getValue().neighborPosition(t.getKey()))
        .flatMap(List::stream)
        .filter(t -> !board.containsKey(t))
        .collect(Collectors.toSet());
  }

  /**
   * Adds a departing tile to the player.
   *
   * @param departTile the departing tile
   */
  public void addDepartTile(DepartTile departTile) {
    Objects.requireNonNull(departTile);
    add(new TileCoord(0, 0), departTile.tile1());
    add(new TileCoord(0, 1), departTile.tile2());
    add(new TileCoord(0, 2), departTile.tile3());
  }

  /**
   * Returns the map of tiles and their neighbors.
   *
   * @return Map the map of tiles and their neighbors
   */
  public Map<TileCoord, Tile> board() {
    return Map.copyOf(board);
  }
}
