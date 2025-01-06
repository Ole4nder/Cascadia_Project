package fr.uge.graphic;

import fr.uge.stack.StackList;
import fr.uge.tile.Tile;
import fr.uge.tile.TileCoord;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** Represents a graphic terminal to display the game board. */
public class GraphicTerminal implements Graphic {
  private static final String EMPTY_TILE =
      """
          +-----+
          |     |
          |     |
          |     |
          +-----+
          """;

  public static String drawOneTile(Tile tile) {
    return """
        +-----+
        |  %s  |
        |  %s |
        |  %s  |
        +-----+
        """
        .formatted(
            tile.landscape().name().charAt(0),
            "" + tile.animals1().getName().charAt(0) + tile.animals2().getName().charAt(0),
            tile.animalToken().name().charAt(0));
  }

  /**
   * Generates a single-row string representation of multiple tiles placed side by side.
   *
   * @param tilesBoard the tiles to include in the row.
   * @return a string representing the tiles aligned in a row.
   */
  // TODO : fais gaffe, tu as oublié de positionner les tiles correctement (position x, y)
  private String drawAllTiles(
      Map<TileCoord, Tile> tilesBoard, int minX, int maxX, int minY, int maxY) {
    StringBuilder result = new StringBuilder();
    for (int y = minY; y <= maxY; y++) {
      result.append(drawRow(tilesBoard, y, minX, maxX));
    }
    return result.toString().trim();
  }

  /**
   * Generates a single-row string representation of multiple tiles placed side by side.
   *
   * @param tilesBoard the tiles to include in the row.
   * @param y the y-coordinate of the row.
   * @param minX the minimum x-coordinate of the row.
   * @param maxX the maximum x-coordinate of the row.
   * @return a string representing the tiles aligned in a row.
   */
  private String drawRow(Map<TileCoord, Tile> tilesBoard, int y, int minX, int maxX) {
    StringBuilder topBorder = new StringBuilder();
    StringBuilder line1 = new StringBuilder();
    StringBuilder line2 = new StringBuilder();
    StringBuilder line3 = new StringBuilder();
    StringBuilder bottomBorder = new StringBuilder();

    for (int x = minX; x <= maxX; x++) {
      appendTileLines(
          tilesBoard, new TileCoord(x, y), topBorder, line1, line2, line3, bottomBorder);
    }

    return assembleRow(topBorder, line1, line2, line3, bottomBorder);
  }

  /**
   * Appends the lines of a tile to the corresponding string builders.
   *
   * @param tilesBoard the tiles to include in the row.
   * @param coord the coordinates of the tile to draw.
   * @param topBorder the string builder for the top border of the tile.
   * @param line1 the string builder for the first line of the tile.
   * @param line2 the string builder for the second line of the tile.
   * @param line3 the string builder for the third line of the tile.
   * @param bottomBorder the string builder for the bottom border of the tile.
   */
  private void appendTileLines(
      Map<TileCoord, Tile> tilesBoard,
      TileCoord coord,
      StringBuilder topBorder,
      StringBuilder line1,
      StringBuilder line2,
      StringBuilder line3,
      StringBuilder bottomBorder) {

    String[] lines =
        (tilesBoard.get(coord) != null ? drawOneTile(tilesBoard.get(coord)) : EMPTY_TILE)
            .split("\n");

    topBorder.append(lines[0]); // +---+
    line1.append(lines[1]); // | %s |
    line2.append(lines[2]); // | %s |
    line3.append(lines[3]); // | %s |
    bottomBorder.append(lines[4]); // +---+
  }

  /**
   * Assembles the string representation of a row of tiles.
   * @param topBorder the top of tile
   * @param line1 the string builder for the first line of the tile.
   * @param line2 the string builder for the second line of the tile.
   * @param line3 the string builder for the third line of the tile.
   * @param bottomBorder the bottom of tile
   * @return the string representation of the row of tiles.
   */
  private String assembleRow(
      StringBuilder topBorder,
      StringBuilder line1,
      StringBuilder line2,
      StringBuilder line3,
      StringBuilder bottomBorder) {

    return topBorder
        .append("\n")
        .append(line1)
        .append("\n")
        .append(line2)
        .append("\n")
        .append(line3)
        .append("\n")
        .append(bottomBorder)
        .append("\n")
        .toString();
  }

  @Override
  public void drawGameBoard(
      Map<TileCoord, Tile> tilesBoard, int minX, int maxX, int minY, int maxY) {
    System.out.println(drawAllTiles(tilesBoard, minX, maxX, minY, maxY));
  }

  @Override
  public void drawTileToTokenAndTile(List<Tile> tiles) {
    var sb = new StringBuilder();
    for (var tile : tiles) {
      sb.append(tile.toString()).append("\n");
    }
    System.out.println(sb);
  }

  @Override
  public void drawOptionTileToken(StackList optionTileTokenList) {
    var sb = new StringBuilder();
    for (var optionTileToken : optionTileTokenList.optionTileTokenList()) {
      sb.append(optionTileToken.toString()).append("\n");
    }
    System.out.println(sb);
  }

  @Override
  public void drawCoordToPutTile(Set<TileCoord> tiles) {
    var sb = new StringBuilder();
    for (var tile : tiles) {
      sb.append(tile.toString()).append("\n");
    }
    System.out.println(sb);
  }
}
