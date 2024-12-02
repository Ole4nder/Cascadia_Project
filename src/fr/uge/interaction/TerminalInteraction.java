package fr.uge.interaction;

import fr.uge.game.OptionTileToken;
import fr.uge.tile.Tile;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class TerminalInteraction implements Interaction {
  public static void choosePutPileOnPlayerBoard(Set<Tile> tiles) {
    return;
  }

  public OptionTileToken ChoicePlayer(List<OptionTileToken> optionTileTokenList) {
    return null;
  }

  public String choosePutTileToken() {
    System.out.println("Choose with put tile or token first");
    var scanner = new Scanner(System.in);
    var input = scanner.nextLine().trim().toLowerCase();
    if (input.equals("tiles") || input.equals("token")) {
      return input;
    }
    System.out.println("Invalid input ! Please enter a valid choice  : tile or token.");
    return choosePutTileToken();
  }

  // TODO a faire
  public Tile choosePutToken(List<Tile> tiles) {
    return null;
  }

  // TODO a finir
  public List<Tile> choosePutTile(List<Tile> tiles) {
    System.out.println("Choose a tile to put on the board");
    var scanner = new Scanner(System.in);
    var input = scanner.nextLine().trim().toLowerCase();
    System.out.println("Invalid input ! Please enter a valid tile.");
    // choosePutTile(tiles);
    return null;
  }
}
