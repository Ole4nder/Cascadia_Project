package fr.uge.bag;

import fr.uge.tile.Tile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TileBag {
  private static final int MAX_TILE_DRAWN = 4;
  private static final int MAX_TILE = 85;
  private final List<Tile> tileBag = new ArrayList<>();

  /**
   * Add a new tile.
   *
   * @param tile, the tile to add.
   */
  public void add(Tile tile) {
    Objects.requireNonNull(tile);
    if (tileBag.size() == MAX_TILE) {
      throw new IllegalStateException("The bag is full.");
    }
    tileBag.add(tile);
  }

  /**
   * Remove a tile.
   *
   * @param tile, the tile to remove.
   */
  public void remove(Tile tile) {
    Objects.requireNonNull(tile);
    tileBag.remove(tile);
  }

  public void shuffle() {
    Collections.shuffle(tileBag);
  }

  /**
   * Draw tiles from the bag.
   *
   * @return List of tiles to draw
   */
  public List<Tile> drawTile() {
    return tileBag.subList(0, MAX_TILE_DRAWN);
  }

  /**
   * Calculate the total of tiles required for the game.
   *
   * @return number of tiles
   */
  public int tileGame(int playersNumbers) {
    return MAX_TILE - (playersNumbers * 20 + 3);
  }

  /** Removing not used tiles from the list in order to play with the right amount. */
  private void amountTileTypeGame(int playersNumbers) {
    tileBag.subList(0, tileGame(playersNumbers)).clear();
  }

  /**
   * Return a copy of the current list.
   *
   * @return List of tiles
   */
  public List<Tile> tileBag() {
    return List.copyOf(tileBag);
  }
}