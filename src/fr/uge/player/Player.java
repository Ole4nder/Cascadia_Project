package fr.uge.player;

import fr.uge.animal.Animals;
import fr.uge.tile.DepartTile;
import fr.uge.tile.Tile;
import fr.uge.tile.square.CordSquareTile;
import java.util.*;

public class Player {
  private final DepartTile departTile; // maybe useless ?
  private final Map<Tile, List<Tile>> tileNeighborMap =
      new TreeMap<>(Comparator.comparing(Tile::cord, CordSquareTile.COMPARATOR));

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

  /**
   * Adds a departing tile to the player.
   *
   * @param departTile the departing tile
   */
  public void addDepartTile(DepartTile departTile) {
    Objects.requireNonNull(departTile);
    departTile.tile1().cord().changeCordSquareTile(0, 0);
    departTile.tile2().cord().changeCordSquareTile(0, 1);
    departTile.tile3().cord().changeCordSquareTile(0, 2);
    add(departTile.tile1(), departTile.tile2());
    add(departTile.tile2(), departTile.tile3());
    add(departTile.tile3(), departTile.tile2());
    add(departTile.tile2(), departTile.tile1());
  }

  /**
   * Adds a token on a tile.
   *
   * @param tile the tile to add the token to
   * @param animal the animal token to add
   */
  public void addTokenOnTile(Tile tile, Animals animal) {
    Objects.requireNonNull(tile);
    Objects.requireNonNull(animal);
    tileNeighborMap.keySet().stream()
        .filter(t -> t.equals(tile.cord()))
        .findFirst()
        .ifPresent(t -> t.setAnimalToken(animal));
  }

  /**
   * Returns the map of tiles and their neighbors.
   *
   * @return Map the map of tiles and their neighbors
   */
  public Map<Tile, List<Tile>> tileNeighborMap() {
    return Map.copyOf(tileNeighborMap);
  }
}
