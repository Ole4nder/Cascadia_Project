package fr.uge.graphic;

import fr.uge.game.OptionTileToken;
import fr.uge.tile.Tile;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Represents a graphic terminal that allows the user to interact with the game through the console.
 */
public class GraphicTerminal implements Graphic {

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
      String[] tileLines = tile.toString().split("\n");
      for (int i = 0; i < 3; i++) {
        lines[i] = (lines[i] == null ? "" : lines[i] + " ") + tileLines[i];
      }
    }
    for (String line : lines) {
      sb.append(line).append("\n");
    }
    return sb.toString();
  }

  public static void choosePutPileOnPlayerBoard(Set<Tile> tiles) {
    return;
  }

  public static OptionTileToken ChoicePlayer(List<OptionTileToken> optionTileTokenList) {
    return null;
  }

  public static String choosePutTileToken() {
    System.out.println("Choose with put tile or token first");
    var scanner = new Scanner(System.in);
    var input = scanner.nextLine().trim().toLowerCase();
    if (input.equals("tiles") || input.equals("token")) {
      return input;
    }
    System.out.println("Invalid input ! Please enter a valid choice  : tile or token.");
    return choosePutTileToken();
  }
  //TODO a faire
  public static Tile choosePutToken(List<Tile> tiles) {
    return null;
  }

  // TODO a finir
  public static List<Tile> choosePutTile(List<Tile> tiles) {
    System.out.println("Choose a tile to put on the board");
    var scanner = new Scanner(System.in);
    var input = scanner.nextLine().trim().toLowerCase();
    System.out.println("Invalid input ! Please enter a valid tile.");
    //choosePutTile(tiles);
    return null;
  }

  @Override
  public void drawGameBoard() {

  }
}
