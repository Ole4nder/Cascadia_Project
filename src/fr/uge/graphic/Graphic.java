package fr.uge.graphic;

import fr.uge.option.StackList;
import fr.uge.tile.Tile;
import fr.uge.tile.TileCoord;
import java.util.List;
import java.util.Set;

public interface Graphic {
  void drawGameBoard(Set<Tile> tiles);

  void drawTileToTokenAndTile(List<Tile> tiles);

  void drawOptionTileToken(StackList option);

  void drawCoordToPutTile(Set<TileCoord> tiles);
}
