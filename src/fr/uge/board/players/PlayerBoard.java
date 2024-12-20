package fr.uge.board.players;

import fr.uge.board.BoardType;
import fr.uge.tile.DepartTile;
import fr.uge.tile.Tile;
import fr.uge.tile.TileCoord;
import java.util.*;
import java.util.stream.Collectors;

/** Represents a player in the game. */
public class PlayerBoard {
  // TODO à changer après retour
  private final Map<Tile, Tile[]> tileNeighborMap =
      new TreeMap<>(Comparator.comparing(Tile::coord, TileCoord.COMPARATOR));

  /**
   * Adds a neighboring relationship between two tiles.
   *
   * @param tile the main tile
   * @param neighbor the neighboring tile
   * @param boardType the type of plateau
   */
  public void add(Tile tile, Tile neighbor, BoardType boardType) {
    Objects.requireNonNull(tile);
    Objects.requireNonNull(neighbor);
    var position = tile.neighborPosition(neighbor);
    tileNeighborMap.computeIfAbsent(neighbor, _ -> new Tile[boardType.getValue()])[position] = tile;
  }

  /**
   * Adds a departing tile to the player.
   *
   * @param departTile the departing tile
   * @param boardType the type of plateau
   */
  // TODO change method ?
  public void addDepartTile(DepartTile departTile, BoardType boardType) {
    Objects.requireNonNull(departTile);
    Objects.requireNonNull(boardType);
    departTile.tile1().coord().changeCord(0, 0);
    departTile.tile2().coord().changeCord(0, 1);
    departTile.tile3().coord().changeCord(0, 2);
    add(departTile.tile1(), departTile.tile2(), boardType);
    add(departTile.tile2(), departTile.tile3(), boardType);
    add(departTile.tile3(), departTile.tile2(), boardType);
    add(departTile.tile2(), departTile.tile1(), boardType);
  }

  /**
   * Check if the tile have all neighbors.
   *
   * @param tile the tile to get the neighbors
   * @return boolean to check if the tile have all neighbors
   */
  public boolean allHisNeighborsFull(Tile tile) {
    return Arrays.stream(tileNeighborMap.get(tile)).anyMatch(Objects::nonNull);
  }

  /**
   * Get the tiles that have not all neighbors.
   * @return Set of tiles
   */
  public Set<Tile> getTilesAsNoNeighborFull() {
    return tileNeighborMap.keySet().stream()
        .filter(tile -> !allHisNeighborsFull(tile))
        .collect(Collectors.toSet());
  }

  /**
   * Get the neighbors of a tile.
   *
   * @param tile the tile to get the neighbors
   * @return List of neighbors
   */
  public List<Tile> getNeighbor(Tile tile) {
    return Arrays.stream(tileNeighborMap.get(tile))
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
  }

  /**
   * Returns the map of tiles and their neighbors.
   *
   * @return Map the map of tiles and their neighbors
   */
  public Map<Tile, Tile[]> tileNeighborMap() {
    return Map.copyOf(tileNeighborMap);
  }
}
