package fr.uge.graphic;

import fr.uge.stack.StackList;
import fr.uge.tile.Tile;
import fr.uge.tile.TileCoord;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Graphic {
  /**
   * Draw the game board.
   *
   * @param tilesBoard the tiles to include in the board.
   * @param minX the minimum x-coordinate of the board.
   * @param maxX the maximum x-coordinate of the board.
   * @param minY the minimum y-coordinate of the board.
   * @param maxY the maximum y-coordinate of the board.
   */
  void drawGameBoard(Map<TileCoord, Tile> tilesBoard, int minX, int maxX, int minY, int maxY);

  /**
   * Draw the tile to the token and the tile.
   *
   * @param tiles the tiles to include in the board.
   */
  void drawTileToTokenAndTile(List<Tile> tiles);

  /**
   * Draw the option tile token.
   *
   * @param option the option to include in the board.
   */
  void drawOptionTileToken(StackList option);

  /**
   * Draw the coordinate to put the tile.
   *
   * @param tiles the tiles to include in the board.
   */
  void drawCoordToPutTile(Set<TileCoord> tiles);
}
