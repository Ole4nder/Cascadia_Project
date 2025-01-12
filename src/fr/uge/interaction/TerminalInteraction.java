package fr.uge.interaction;

import fr.uge.graphic.Graphic;
import fr.uge.stack.StackList;
import fr.uge.stack.StackTileToken;
import fr.uge.tile.Tile;
import fr.uge.tile.TileCoord;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class TerminalInteraction implements Interaction {

  public StackTileToken choiceOption(StackList stackList, Graphic graphic) {
    graphic.drawOptionTileToken(stackList);
    System.out.println(
        "Choose a option number between 1 and " + stackList.optionTileTokenList().size());
    var scanner = new Scanner(System.in);
    var input = scanner.nextLine().trim().toLowerCase();
    var value = Integer.parseInt(input);
    if (value >= 1 && value <= stackList.optionTileTokenList().size()) {
      return stackList.optionTileTokenList().get(value - 1);
    }
    System.out.println("Invalid input ! Please enter a valid number.");
    return choiceOption(stackList, graphic);
  }

  @Override
  public String choosePutTileToken() {
    System.out.println("Choose with put tile or token first");
    var scanner = new Scanner(System.in);
    var input = scanner.nextLine().trim().toLowerCase();
    if (input.equals("tile") || input.equals("token")) {
      return input;
    }
    System.out.println("Invalid input ! Please enter a valid choice  : tile or token.");
    return choosePutTileToken();
  }

  /* It's normal because Graphic with Zen well have different implementation */

  @Override
  public Tile choosePutToken(List<Tile> tiles, Graphic graphic) {
    System.out.println("Choose a Tile to put the token on between 1 and " + tiles.size());
    var scanner = new Scanner(System.in);
    var input = scanner.nextLine().trim().toLowerCase();
    graphic.drawTileToTokenAndTile(tiles);
    var value = Integer.parseInt(input);
    if (value >= 1 && value <= tiles.size()) {
      return tiles.get(value - 1);
    }
    System.out.println("Invalid input ! Please enter a valid number.");
    return choosePutToken(tiles, graphic);
  }

  @Override
  public boolean wantChangeOption() {
    System.out.println("Overpopulation of 3 do you want to change the option ? (yes or no)");
    var scanner = new Scanner(System.in);
    var input = scanner.nextLine().trim().toLowerCase();
    if (input.equals("yes") || input.equals("no")) {
      return input.equals("yes");
    }
    System.out.println("Invalid input ! Please enter a valid choice : yes or no.");
    return wantChangeOption();
  }

  @Override
  public TileCoord choosePutTile(Set<TileCoord> tileCoords, Graphic graphic) {
    System.out.println(
        "Choose a number for the tile to put on the board between 1 and " + tileCoords.size());
    List<TileCoord> tileCoordsList = new ArrayList<>(tileCoords);
    graphic.drawCoordToPutTile(tileCoords);
    var scanner = new Scanner(System.in);
    var input = scanner.nextLine().trim().toLowerCase();
    var value = Integer.parseInt(input);
    if (value >= 1 && value <= tileCoords.size()) {
      return tileCoordsList.get(value - 1);
    }
    System.out.println("Invalid input ! Please enter a valid number.");
    return choosePutTile(tileCoords, graphic);
  }
}
