package fr.uge.graphic;

import com.github.forax.zen.ApplicationContext;
import fr.uge.stack.StackList;
import fr.uge.tile.Tile;
import fr.uge.tile.TileCoord;
import java.awt.*;
import java.awt.geom.Rectangle2D;
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

  @Override
  public void drawGameBoard(
      Map<TileCoord, Tile> tilesBoard, int minX, int maxX, int minY, int maxY) {

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
    graphics2D.draw(new Rectangle2D.Double(centerX, centerY, PADDING_SQUARE, PADDING_SQUARE));
  }

  @Override
  public void drawTileToTokenAndTile(List<Tile> tiles) {}

  @Override
  public void drawOptionTileToken(StackList option) {
    for (var optionTileToken : option.optionTileTokenList()) {
      context.renderFrame(
          graphics2D -> {
            graphics2D.setColor(Color.BLUE);
            graphics2D.drawString(
                optionTileToken.tile().toString(), width / 2, height / 2 + PADDING_TEXT_WIDTH * 2);
            graphics2D.drawString(
                optionTileToken.token().getName(), width / 2, height / 2 + PADDING_TEXT_WIDTH * 3);
          });
    }
  }

  @Override
  public void drawCoordToPutTile(Set<TileCoord> tiles) {
    for (var tile : tiles) {
      context.renderFrame(
          graphics2D -> {
            graphics2D.setColor(Color.RED);
            // Cast to double to avoid integer division
            graphics2D.draw(
                new Rectangle2D.Double(
                    (double) width / 2 + tile.x() * PADDING_SQUARE,
                    (double) height / 2 + tile.y() * PADDING_SQUARE,
                    PADDING_SQUARE,
                    PADDING_SQUARE));
          });
    }
  }
}
