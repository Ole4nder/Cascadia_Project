package fr.uge.graphic;

import fr.uge.stack.StackList;
import fr.uge.tile.Tile;
import fr.uge.tile.TileCoord;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class GraphicSquareZen implements Graphic {
  // TODO : avoir un application context pour pouvoir dessiner avec zen
  @Override
  public void drawGameBoard(
      Map<TileCoord, Tile> tilesBoard, int minX, int maxX, int minY, int maxY) {
  }

  @Override
  public void drawTileToTokenAndTile(List<Tile> tiles) {}

  @Override
  public void drawOptionTileToken(StackList option) {}

  @Override
  public void drawCoordToPutTile(Set<TileCoord> tiles) {}
}
