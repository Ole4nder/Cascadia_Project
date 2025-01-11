package fr.uge.graphic;

import com.github.forax.zen.ApplicationContext;
import fr.uge.stack.StackList;
import fr.uge.tile.Tile;
import fr.uge.tile.TileCoord;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public record GraphicSquareZen(ApplicationContext context, int height, int width)
    implements Graphic {
  private static final int PADDING_SQUARE = 60;
  private static final int PADDING_TEXT_HEIGHT = 5;
  private static final int PADDING_TEXT_WIDTH = 10;

  public GraphicSquareZen {
    Objects.requireNonNull(context);
  }

  public GraphicSquareZen(ApplicationContext context) {
    this(context, context.getScreenInfo().height(), context.getScreenInfo().width());
  }

  private void resteScreen() {
    context.renderFrame(
        graphics2D -> {
          graphics2D.setColor(Color.DARK_GRAY);
          graphics2D.fillRect(0, 0, width, height);
        });
  }

  @Override
  public void drawGameBoard(
      Map<TileCoord, Tile> tilesBoard, int minX, int maxX, int minY, int maxY) {
    resteScreen();
    for (int x = minX; x <= maxX; x++) {
      for (int y = minY; y <= maxY; y++) {
        TileCoord tmpCord = new TileCoord(x, y);

        if (tilesBoard.containsKey(tmpCord)) {
          Tile tile = tilesBoard.get(tmpCord);
          int centerX = width / 2 + x * PADDING_SQUARE;
          int centerY = height / 2 + y * PADDING_SQUARE;
          context.renderFrame(
              graphics2D -> {
                graphics2D.setColor(Color.BLACK);
                drawTileDetails(graphics2D, tile, centerX, centerY);
                drawTileBorder(graphics2D, centerX, centerY);
              });
        }
      }
    }
  }

  private void drawTileDetails(Graphics2D graphics2D, Tile tile, int centerX, int centerY) {
    int textX = centerX + PADDING_TEXT_HEIGHT;
    graphics2D.drawString(tile.landscape().getLandscape(), textX, centerY + PADDING_TEXT_WIDTH);
    graphics2D.drawString(tile.animals1().getName(), textX, centerY + PADDING_TEXT_WIDTH * 2);
    graphics2D.drawString(tile.animals2().getName(), textX, centerY + PADDING_TEXT_WIDTH * 3);
    graphics2D.drawString(tile.animalToken().getName(), textX, centerY + PADDING_TEXT_WIDTH * 4);
  }

  private void drawTileBorder(Graphics2D graphics2D, int centerX, int centerY) {
    graphics2D.drawRect(centerX, centerY, PADDING_SQUARE, PADDING_SQUARE);
  }

  /**
   * Whe don't need to implement this method for the use zen6 so we just return because the player
   * say how to put token with default value on the tile board
   */
  @Override
  public void drawTileToTokenAndTile(List<Tile> tiles) {}

  @Override
  public void drawOptionTileToken(StackList option) {
    var x = 0;
    for (var optionTileToken : option.optionTileTokenList()) {
      int finalX = x;
      context.renderFrame(
          graphics2D -> {
            graphics2D.setColor(Color.CYAN);
            drawTileBorder(graphics2D, PADDING_SQUARE + finalX, PADDING_SQUARE);
            drawTileDetails(
                graphics2D, optionTileToken.tile(), PADDING_SQUARE + finalX, PADDING_SQUARE);
            graphics2D.setColor(Color.ORANGE);
            graphics2D.drawString(
                "Token :", PADDING_SQUARE + finalX, PADDING_SQUARE + PADDING_TEXT_WIDTH * 8);
            graphics2D.drawString(
                optionTileToken.token().getName(),
                PADDING_SQUARE + finalX,
                PADDING_SQUARE + PADDING_TEXT_WIDTH * 9);
            graphics2D.setColor(Color.BLACK);
            graphics2D.drawRect(
                PADDING_SQUARE + finalX,
                PADDING_SQUARE + PADDING_SQUARE,
                PADDING_SQUARE,
                PADDING_SQUARE);
          });
      x += PADDING_SQUARE;
    }
  }

  @Override
  public void drawCoordToPutTile(Set<TileCoord> tiles) {
    for (var tile : tiles) {
      context.renderFrame(
          graphics2D -> {
            graphics2D.setColor(Color.RED);
            graphics2D.drawRect(
                width / 2 + tile.x() * PADDING_SQUARE,
                height / 2 + tile.y() * PADDING_SQUARE,
                PADDING_SQUARE,
                PADDING_SQUARE);
          });
    }
  }
}
