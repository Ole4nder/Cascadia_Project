package fr.uge.interaction;

import com.github.forax.zen.ApplicationContext;
import com.github.forax.zen.KeyboardEvent;
import com.github.forax.zen.PointerEvent;
import fr.uge.graphic.Graphic;
import fr.uge.stack.StackList;
import fr.uge.stack.StackTileToken;
import fr.uge.tile.Tile;
import fr.uge.tile.TileCoord;
import java.awt.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public record ZenInteractionSquare(ApplicationContext context, int height, int width)
    implements Interaction {

  private static final int TOP_LEFT_X_DRAW = 60; // Corner top left (x)
  private static final int TOP_LEFT_Y_DRAW = 60; // Corner top left (y)
  private static final int BOTTOM_RIGHT_X = 120; // Bottom right corner (x)
  private static final int BOTTOM_RIGHT_Y = 180; // Bottom right corner (y)
  private static final int PADDING_SQUARE = 60; // Bottom right corner (x)
  private static final KeyboardEvent.Key QUIT_KEY = KeyboardEvent.Key.Q;

  public ZenInteractionSquare {
    Objects.requireNonNull(context);
  }

  public ZenInteractionSquare(ApplicationContext context) {
    this(context, context.getScreenInfo().height(), context.getScreenInfo().width());
  }

  private int positionOption(PointerEvent pe, int numberOption) {
    if (pe.action() != PointerEvent.Action.POINTER_DOWN) {
      return -1;
    }
    var squareWidth = BOTTOM_RIGHT_X - TOP_LEFT_X_DRAW; // Largest of a square
    var location = pe.location();

    for (int i = 0; i < numberOption; i++) {
      var currentTopLeftX = TOP_LEFT_X_DRAW + i * squareWidth; // shift horizontal
      var currentBottomRightX = currentTopLeftX + squareWidth; // limit right of square
      if (location.x() >= currentTopLeftX
          && location.x() <= currentBottomRightX
          && location.y() >= TOP_LEFT_Y_DRAW
          && location.y() <= BOTTOM_RIGHT_Y) {
        return i;
      }
    }
    return -1;
  }

  @Override
  public StackTileToken choiceOption(StackList stackList, Graphic graphic) {
    graphic.drawOptionTileToken(stackList);
    context.renderFrame(
        graphics2D -> {
          graphics2D.setColor(Color.GREEN);
          graphics2D.drawString("Choose o option of up left screen", width / 5, 100);
        });
    var event = context.pollOrWaitEvent(10);
    int hasChosenOption = -1;
    while (hasChosenOption == -1) {
      for (; event == null; event = context.pollOrWaitEvent(10))
        ;
      hasChosenOption =
          switch (event) {
            case KeyboardEvent _ -> -1;
            case PointerEvent pe -> positionOption(pe, stackList.optionTileTokenList().size());
          };
      event = context.pollOrWaitEvent(10);
    }
    return stackList.optionTileTokenList().get(hasChosenOption);
  }

  private void drawInfoTOChosePutTileOrToken() {
    context.renderFrame(
        graphics2D -> {
          graphics2D.setColor(Color.GREEN);
          graphics2D.drawString("Choose with put tile or token first", width - 400, height / 15);
          graphics2D.drawString("Press T for tile, K for token or Q to quit the program.", width - 400, height / 10);
        });
  }

  @Override
  public String choosePutTileToken() {
    drawInfoTOChosePutTileOrToken();
    var event = context.pollOrWaitEvent(10);
    while (true) {
      for (; event == null; event = context.pollOrWaitEvent(10))
        ;
      switch (event) {
        case KeyboardEvent ke -> {
          checkQuitProgram(ke);
          switch (ke.key()) {
            case KeyboardEvent.Key.T -> {
              return "tile";
            }
            case KeyboardEvent.Key.K -> {
              return "token";
            }
          }
        }
        case PointerEvent _ -> {}
      }
      event = context.pollOrWaitEvent(10);
    }
  }

  @Override
  public TileCoord choosePutTile(Set<TileCoord> tileCoords, Graphic graphic) {
    graphic.drawCoordToPutTile(tileCoords);
    var event = context.pollOrWaitEvent(10);
    while (true) {
      for (; event == null; event = context.pollOrWaitEvent(10))
        ;
      switch (event) {
        case KeyboardEvent ke -> {
          checkQuitProgram(ke);
        }
        case PointerEvent pe -> {
          return positionTileToPut(pe, tileCoords);
        }
      }
      event = context.pollOrWaitEvent(10);
    }
  }

  private TileCoord positionTileToPut(PointerEvent pe, Set<TileCoord> tileCoords) {
    if (pe.action() != PointerEvent.Action.POINTER_DOWN) {
      return null;
    }
    return tileCoords.stream()
        .filter(
            tileCoord -> {
              var x = tileCoord.x() * PADDING_SQUARE + width / 2;
              var y = tileCoord.y() * PADDING_SQUARE + height / 2;
              return pe.location().x() >= x
                  && pe.location().x() <= x + PADDING_SQUARE
                  && pe.location().y() >= y
                  && pe.location().y() <= y + PADDING_SQUARE;
            })
        .findFirst()
        .orElse(null);
  }

  private void drawInfoTileToPutToken(List<Tile> tiles) {
    context.renderFrame(
        graphics2D -> {
          graphics2D.drawString(
              "Choose a Tile to put the token on between 1 and " + tiles.size(),
              width - 400,
              height / 15);
          var letter = 'A';
          var i = 0;
          for (var tile : tiles) {
            graphics2D.drawString(
                letter + " : " + tile.animals1().getName() + " " + tile.animals2().getName(),
                width - 400,
                height / 10 + 30 + i * 30);
            letter++;
            i++;
          }
        });
  }

  // Terrible function, but it's the only way to do it
  private Tile positionTileToToken(KeyboardEvent ke, List<Tile> tiles) {
    return switch (ke.key()) {
      case KeyboardEvent.Key.A -> tiles.get(0);
      case KeyboardEvent.Key.B -> tiles.get(1);
      case KeyboardEvent.Key.C -> tiles.get(2);
      case KeyboardEvent.Key.D -> tiles.get(3);
      case KeyboardEvent.Key.E -> tiles.get(4);
      case KeyboardEvent.Key.F -> tiles.get(5);
      case KeyboardEvent.Key.G -> tiles.get(6);
      case KeyboardEvent.Key.H -> tiles.get(7);
      case KeyboardEvent.Key.I -> tiles.get(8);
      case KeyboardEvent.Key.J -> tiles.get(9);
      default -> null;
    };
  }

  @Override
  public Tile choosePutToken(List<Tile> tiles, Graphic graphic) {
    graphic.drawTileToTokenAndTile(tiles);
    drawInfoTileToPutToken(tiles);
    var event = context().pollOrWaitEvent(100000);
    Tile tile = null;
    while (tile == null) {
      for (; event == null; event = context.pollOrWaitEvent(10))
        ;
      switch (event) {
        case KeyboardEvent ke -> {
          checkQuitProgram(ke);
          tile = positionTileToToken(ke, tiles);
        }
        case PointerEvent _ -> {}
      }
      event = context.pollOrWaitEvent(10);
    }
    return tile;
  }

  @Override
  public boolean wantChangeOption() {
    drawInfoChangeOption(); // Draw the information
    var event = context.pollOrWaitEvent(10);
    while (true) {
      for (; event == null; event = context.pollOrWaitEvent(10))
        ;
      switch (event) {
        case KeyboardEvent ke -> {
          return checkIfWantChangeOption(ke);
        }
        case PointerEvent _ -> {}
      }
      event = context.pollOrWaitEvent(10);
    }
  }

  private void drawInfoChangeOption() {
    context.renderFrame(
        graphics2D -> {
          graphics2D.setColor(Color.GREEN);
          graphics2D.drawString(
              "Do you want to change the option ? (yes or no)", width - 400, height / 15);
          graphics2D.setColor(Color.BLACK);
          graphics2D.drawString("yes", width - 395, height / 10 + 30);
          graphics2D.drawString("no", width - 345, height / 10 + 30);
        });
  }

  private boolean checkIfWantChangeOption(KeyboardEvent ke) {
    return ke.key() == KeyboardEvent.Key.Y || ke.key() == KeyboardEvent.Key.N;
  }

  private void checkQuitProgram(KeyboardEvent ke) {
    if (ke.key() == QUIT_KEY) {
      System.exit(0);
    }
  }
}
