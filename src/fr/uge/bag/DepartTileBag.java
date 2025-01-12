package fr.uge.bag;

import fr.uge.tile.DepartTile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DepartTileBag {
  private final List<DepartTile> departTileBag = new ArrayList<>();

  /**
   * Add a new departure tile.
   *
   * @param departTile the departure tile to add.
   */
  public void add(DepartTile departTile) {
    Objects.requireNonNull(departTile);
    departTileBag.add(departTile);
  }

  /**
   * Remove a depart tile.
   *
   * @param departTile the departure tile to remove.
   */
  public void remove(DepartTile departTile) {
    Objects.requireNonNull(departTile);
    departTileBag.remove(departTile);
  }

  /**
   * Return a copy of the current list.
   *
   * @return List of depart tiles
   */
  public List<DepartTile> departTileBag() {
    return List.copyOf(departTileBag);
  }

  /** Shuffle the departure tiles bag. */
  public void shuffle() {
    Collections.shuffle(departTileBag);
  }
}
