package fr.uge.tile;

import fr.uge.animal.Animals;
import java.util.List;

public sealed interface Tile permits SquareTile, HexagonTile {
  /**
   * Place an animal token on the tile.
   *
   * @param animal the animal to place on the tile.
   */
  void setAnimalToken(Animals animal);

  /**
   * Return the animal token on the tile.
   *
   * @return the animal token on the tile.
   */
  Animals animalToken();

  /**
   * Return the landscape of the tile.
   *
   * @return the landscape of the tile.
   */
  TileLandscape landscape();

  /**
   * Return the first animal associated with the tile.
   *
   * @return the first animal associated with the tile.
   */
  Animals animals1();

  /**
   * Return the second animal associated with the tile.
   *
   * @return the second animal associated with the tile.
   */
  Animals animals2();

  /**
   * Return List of all position of neighbor (fourth).
   *
   * @param tileCoord the coordinates of the tile.
   * @return the position of the neighbor tile.
   */
  List<TileCoord> neighborPosition(TileCoord tileCoord);

  /**
   * Test if the landscape are the same.
   *
   * @return true if the habitat is perfect (same landscape), false otherwise.
   */
  boolean perfectHabitat();
}
