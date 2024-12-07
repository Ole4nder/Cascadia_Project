package fr.uge.graphic;

import fr.uge.tile.Tile;
import java.util.Set;

/** Represents a graphic terminal to display the game board. */
public class GraphicTerminal implements Graphic {

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
   * @param tiles the tiles to include in the row.
   * @return a string representing the tiles aligned in a row.
   */
  // TODO : fais gaffe, tu as oublié de positionner les tiles correctement (position x, y)
  public static String drawAllTiles(Set<Tile> tiles) {
    // Construire les lignes pour chaque partie de la tuile
    StringBuilder topBorder = new StringBuilder();
    StringBuilder line1 = new StringBuilder();
    StringBuilder line2 = new StringBuilder();
    StringBuilder line3 = new StringBuilder();
    StringBuilder bottomBorder = new StringBuilder();

    for (Tile tile : tiles) {
      // Dessiner une tuile et la diviser en lignes
      String[] lines = drawOneTile(tile).split("\n");

      topBorder.append(lines[0]); // +---+
      line1.append(lines[1]); // | %s |
      line2.append(lines[2]); // | %s |
      line3.append(lines[3]); // | %s |
      bottomBorder.append(lines[4]); // +---+
    }

    // Construire l'affichage final avec toutes les lignes
    return """
        %s
        %s
        %s
        %s
        %s
        """
        .formatted(
            topBorder.toString().trim(),
            line1.toString().trim(),
            line2.toString().trim(),
            line3.toString().trim(),
            bottomBorder.toString().trim());
  }

  @Override
  public void drawGameBoard() {}
}
