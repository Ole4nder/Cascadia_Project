package fr.uge.player;

import fr.uge.tile.DepartTile;
import fr.uge.tile.Tile;
import java.util.*;

public class Player {
  private final DepartTile departTile;
  private final Map<Tile, List<Tile>> tileNeighborMap = new HashMap<>();

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
   */
  public void add(Tile tile, Tile neighbor) {
    Objects.requireNonNull(tile);
    Objects.requireNonNull(neighbor);
    tileNeighborMap.computeIfAbsent(tile, _ -> new ArrayList<>()).add(neighbor);
  }

  public Map<Tile, List<Tile>> tileNeighborMap() {
    return Map.copyOf(tileNeighborMap);
  }
}
