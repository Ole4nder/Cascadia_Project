package fr.uge.board.players;

import fr.uge.tile.*;
import java.util.*;

/** Represents a player in the game. */
public class PlayerBoard {
  // TODO à changer après retour
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

  public Set<TileCoord> getAllCoord(Tile tile) {
    return board.keySet();
  }

  /**
   * Adds a departing tile to the player.
   *
   * @param departTile the departing tile
   */
  // TODO change method ?
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
