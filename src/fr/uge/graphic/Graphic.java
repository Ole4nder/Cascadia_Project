package fr.uge.graphic;

import fr.uge.option.StackList;
import fr.uge.tile.Tile;
import fr.uge.tile.TileCoord;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Graphic {
  void drawGameBoard(Map<TileCoord, Tile> tilesBoard, int minX, int maxX, int minY, int maxY);

  void drawTileToTokenAndTile(List<Tile> tiles);

  void drawOptionTileToken(StackList option);

  void drawCoordToPutTile(Set<TileCoord> tiles);
}
