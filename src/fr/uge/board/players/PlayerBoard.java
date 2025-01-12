package fr.uge.board.players;

import fr.uge.tile.*;
import java.util.*;
import java.util.stream.Collectors;

/** Represents a player in the game. */
public class PlayerBoard {
  private final Map<TileCoord, Tile> board =
      new TreeMap<>(Comparator.comparing(TileCoord::x).thenComparing(TileCoord::y));
  private int score = 0;

  /**
   * Update the score of the player with a score.
   *
   * @param score the score to add
   */
  public void updateScore(int score) {
    this.score += score;
  }

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
   * Get the minimum x value of the board.
   *
   * @return the minimum x value of the board.
   */
  public int getMinX() {
    return board.keySet().stream().mapToInt(TileCoord::x).min().orElse(0);
  }

  /**
   * Get the maximum x value of the board.
   *
   * @return the maximum x value of the board.
   */
  public int getMaxX() {
    return board.keySet().stream().mapToInt(TileCoord::x).max().orElse(0);
  }

  /**
   * Get the minimum y value of the board.
   *
   * @return the minimum y value of the board.
   */
  public int getMinY() {
    return board.keySet().stream().mapToInt(TileCoord::y).min().orElse(0);
  }

  /**
   * Get the maximum y value of the board.
   *
   * @return the maximum y value of the board.
   */
  public int getMaxY() {
    return board.keySet().stream().mapToInt(TileCoord::y).max().orElse(0);
  }

  /**
   * Returns the map of tiles and their neighbors.
   *
   * @return Map the map of tiles and their neighbors
   */
  public Map<TileCoord, Tile> board() {
    return Map.copyOf(board);
  }

  /**
   * Returns the score of the player.
   *
   * @return int the score of the player
   */
  public int score() {
    return score;
  }
}
