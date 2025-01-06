package fr.uge.graphic;

import fr.uge.option.StackList;
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
    // Construire les lignes pour chaque partie de la tuile
    StringBuilder result = new StringBuilder();

    // Parcourir les lignes (y) de haut en bas
    for (int y = minY; y <= maxY; y++) {
      // Initialiser les StringBuilder pour chaque ligne de tuiles
      StringBuilder topBorder = new StringBuilder();
      StringBuilder line1 = new StringBuilder();
      StringBuilder line2 = new StringBuilder();
      StringBuilder line3 = new StringBuilder();
      StringBuilder bottomBorder = new StringBuilder();

      // Parcourir les colonnes (x) de gauche à droite
      for (int x = minX; x <= maxX; x++) {
        TileCoord coord = new TileCoord(x, y);
        Tile tile = tilesBoard.get(coord);

        // Dessiner la tuile ou une tuile vide si absente
        String[] lines = (tile != null ? drawOneTile(tile) : EMPTY_TILE).split("\n");

        // Ajouter chaque ligne de la tuile
        topBorder.append(lines[0]); // +---+
        line1.append(lines[1]); // | %s |
        line2.append(lines[2]); // | %s |
        line3.append(lines[3]); // | %s |
        bottomBorder.append(lines[4]); // +---+
        }
      // Ajouter les lignes au résultat final
      result.append(topBorder).append("\n");
      result.append(line1).append("\n");
      result.append(line2).append("\n");
      result.append(line3).append("\n");
      result.append(bottomBorder).append("\n");
    }
    return result.toString().trim();
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
