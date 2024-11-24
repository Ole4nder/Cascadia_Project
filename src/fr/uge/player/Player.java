package fr.uge.player;

import fr.uge.game.PlateauType;
import fr.uge.tile.DepartTile;
import fr.uge.tile.Tile;
import fr.uge.tile.coord.Coord;
import java.util.*;

/**
 * Represents a player in the game.
 */
public class Player {
  private final DepartTile departTile; // maybe useless ?
  private final Map<Tile, Tile[]> tileNeighborMap =
      new TreeMap<>(Comparator.comparing(Tile::coord, Coord.COMPARATOR));

  /**
   * Create a player with a specified starting tile.
   *
   * @param departTile, starting tile configuration
   */
  public Player(DepartTile departTile) {
    Objects.requireNonNull(departTile);
    this.departTile = departTile;
  }

  /**
   * Adds a neighboring relationship between two tiles.
   *
   * @param tile the main tile
   * @param neighbor the neighboring tile
   * @param plateauType the type of plateau
   */
  public void add(Tile tile, Tile neighbor, PlateauType plateauType) {
    Objects.requireNonNull(tile);
    Objects.requireNonNull(neighbor);
    var position = tile.neighborPosition(neighbor);
    tileNeighborMap.computeIfAbsent(neighbor, _ -> new Tile[plateauType.getValue()])[position] =
        tile;
  }

  /**
   * Adds a departing tile to the player.
   *
   * @param departTile the departing tile
   * @param plateauType the type of plateau
   */
  public void addDepartTile(DepartTile departTile, PlateauType plateauType) {
    Objects.requireNonNull(departTile);
    departTile.tile1().coord().changeCord(0, 0);
    departTile.tile2().coord().changeCord(0, 1);
    departTile.tile3().coord().changeCord(0, 2);
    add(departTile.tile1(), departTile.tile2(), plateauType);
    add(departTile.tile2(), departTile.tile3(), plateauType);
    add(departTile.tile3(), departTile.tile2(), plateauType);
    add(departTile.tile2(), departTile.tile1(), plateauType);
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
