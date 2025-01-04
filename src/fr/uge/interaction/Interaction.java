package fr.uge.interaction;

import fr.uge.graphic.Graphic;
import fr.uge.option.OptionList;
import fr.uge.option.OptionTileToken;
import fr.uge.tile.Tile;
import fr.uge.tile.TileCoord;

import java.util.List;

public interface Interaction {

  /**
   * Choose the option of the player
   *
   * @param optionList list of options
   * @param graphic the graphic
   * @return the player chosen
   */
  OptionTileToken choiceOption(OptionList optionList, Graphic graphic);

  /**
   * Choose the first action to do
   *
   * @return the action chosen
   */
  String choosePutTileToken();

  /**
   * Choose the tile to put the token on
   *
   * @param tileCoords list of tile coordinates to put new tile
   * @param graphic the graphic
   * @return the tile chosen
   */

  TileCoord choosePutTile(List<TileCoord> tileCoords, Graphic graphic);

  /**
   * Choose the tile to put the token on
   *
   * @param tiles list of tiles
   * @param graphic the graphic
   * @return the tile chosen
   */
  Tile choosePutToken(List<Tile> tiles, Graphic graphic);

  /**
   * Ask the player if he wants to change the option
   *
   * @return true if the player wants to change the option
   */
  boolean wantChangeOption();
}
