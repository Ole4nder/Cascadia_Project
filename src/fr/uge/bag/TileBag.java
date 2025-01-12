package fr.uge.bag;

import fr.uge.tile.Tile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TileBag {
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
   * @param index, the position of the tile in list.
   */
  public Tile remove(int index) {
    if (index < 0 || index >= tileBag.size()) {
      throw new IllegalArgumentException("Index out of bound.");
    }
    return tileBag.remove(index);
  }

  public void shuffle() {
    Collections.shuffle(tileBag);
  }

  /**
   * Draw tiles from the bag.
   *
   * @return List of tiles to draw
   */
  public List<Tile> drawTile(int maxTileDrawn) {
    List<Tile> tiles = new ArrayList<>(tileBag.subList(0, maxTileDrawn));
    tileBag.subList(0, maxTileDrawn).clear();
    return tiles;
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
