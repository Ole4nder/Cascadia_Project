package fr.uge.interaction;

import fr.uge.graphic.Graphic;
import fr.uge.option.OptionList;
import fr.uge.option.OptionTileToken;
import fr.uge.tile.Tile;
import fr.uge.tile.TileCoord;
import java.util.List;
import java.util.Scanner;

public class TerminalInteraction implements Interaction {

  public OptionTileToken choiceOption(OptionList optionList, Graphic graphic) {
    graphic.drawOptionTileToken(optionList);
    System.out.println(
        "Choose a option number between 1 and " + optionList.optionTileTokenList().size());
    var scanner = new Scanner(System.in);
    var input = scanner.nextLine().trim().toLowerCase();
    var value = Integer.parseInt(input);
    if (value >= 1 && value <= optionList.optionTileTokenList().size()) {
      return optionList.optionTileTokenList().get(value - 1);
    }
    System.out.println("Invalid input ! Please enter a valid number.");
    return choiceOption(optionList, graphic);
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
  public TileCoord choosePutTile(List<TileCoord> tileCoords, Graphic graphic) {
    System.out.println(
        "Choose a number for the tile to put on the board between 1 and " + tileCoords.size());
    var scanner = new Scanner(System.in);
    var input = scanner.nextLine().trim().toLowerCase();
    graphic.drawCoordToPutTile(tileCoords);
    var value = Integer.parseInt(input);
    if (value >= 1 && value <= tileCoords.size()) {
      return tileCoords.get(value - 1);
    }
    System.out.println("Invalid input ! Please enter a valid number.");
    return choosePutTile(tileCoords, graphic);
  }
}
