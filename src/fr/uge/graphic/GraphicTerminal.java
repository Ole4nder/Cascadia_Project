package fr.uge.graphic;

import fr.uge.animal.Animals;
import fr.uge.game.OptionTileToken;
import fr.uge.tile.Tile;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Represents a graphic terminal that allows the user to interact with the game through the console.
 */
public class GraphicTerminal implements Graphic {

  private static String drawOneTile(Tile tile) {
    var sb = new StringBuilder();
    String information;
    if (tile.animalToken() == Animals.DEFAULT) {
      information = tile.animals1() + "-" + tile.animals2();
    } else {
      information = tile.animalToken().toString();
    }
    sb.repeat("*", tile.landscape().length() + information.length() + 3)
            .append("\n")
            .append("*")
            .append(tile.landscape())
            .append(" ")
            .append(information)
            .append("*\n")
            .repeat("*", tile.landscape().length() + information.length() + 3)
            .append("\n");
    return sb.toString();
}

  /**
   * Generates a single-row string representation of multiple tiles placed side by side.
   *
   * @param tiles the tiles to include in the row.
   * @return a string representing the tiles aligned in a row.
   */
  // TODO : fais gaffe, tu as oublié de positionner les tiles correctement (position x, y)
  public static String drawTile(Set<Tile> tiles) {
    var sb = new StringBuilder();
    String[] lines = new String[3];

    // Recuperate all tile line and add side by side
    for (Tile tile : tiles) {
      String[] tileLines = drawOneTile(tile).split("\n");
      for (int i = 0; i < 3; i++) {
        lines[i] = (lines[i] == null ? "" : lines[i] + " ") + tileLines[i];
      }
    }
    for (String line : lines) {
      sb.append(line).append("\n");
    }
    return sb.toString();
  }

  @Override
  public void drawGameBoard() {

  }
}
