package fr.uge.graphic;

import fr.uge.option.OptionList;

import fr.uge.tile.Tile;

import java.util.List;
import java.util.Set;

public interface Graphic {
    void drawGameBoard(Set<Tile> tiles);

    void drawTileToTokenAndTile(List<Tile> tiles);

    void drawOptionTileToken(OptionList option);
}
