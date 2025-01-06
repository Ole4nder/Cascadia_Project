package fr.uge.interaction;

import fr.uge.graphic.Graphic;
import fr.uge.stack.StackList;
import fr.uge.stack.StackTileToken;
import fr.uge.tile.Tile;
import fr.uge.tile.TileCoord;

import java.util.List;
import java.util.Set;

public class ZenInteraction implements Interaction {

  @Override
  public StackTileToken choiceOption(StackList stackList, Graphic graphic) {
    return null;
  }

  @Override
  public String choosePutTileToken() {
    return "";
  }

  @Override
  public TileCoord choosePutTile(Set<TileCoord> tileCoords, Graphic graphic) {
    return null;
  }

  @Override
  public Tile choosePutToken(List<Tile> tiles, Graphic graphic) {
    return null;
  }

  @Override
  public boolean wantChangeOption() {
    return false;
  }
}
