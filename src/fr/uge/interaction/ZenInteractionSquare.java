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

  public ZenInteractionSquare {
    Objects.requireNonNull(context);
  }

  public ZenInteractionSquare(ApplicationContext context) {
    this(context, context.getScreenInfo().height(), context.getScreenInfo().width());
  }

  private static int positionOption(PointerEvent pe, int numberOption) {
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

  @Override
  public String choosePutTileToken() {

    return "";
  }

  @Override
  public TileCoord choosePutTile(Set<TileCoord> tileCoords, Graphic graphic) {
    return null;
  }

  @Override
  public Tile choosePutToken(List<Tile> tiles, Graphic graphic) {
    return null;
  }

  @Override
  public boolean wantChangeOption() {
    context.renderFrame(
        graphics2D -> {
          graphics2D.setColor(Color.GREEN);
          graphics2D.drawString(
              "Do you want to change the option ? (yes or no)", width - 400, height / 15);
          graphics2D.setColor(Color.BLACK);
          graphics2D.drawRect(width - 400, height / 10, 50, 50);
          graphics2D.drawString("yes", width - 395, height / 10 + 30);
          graphics2D.drawRect(width - 350, height / 10, 50, 50);
          graphics2D.drawString("no", width - 345, height / 10 + 30);
        });
    var event = context.pollOrWaitEvent(10);
    while (true) {
      for (; event == null; event = context.pollOrWaitEvent(10))
        ;
      switch (event) {
        case KeyboardEvent ke -> {
          if (ke.key() == KeyboardEvent.Key.Y) {
            return true;
          } else if (ke.key() == KeyboardEvent.Key.N) {
            return false;
          }
        }
        case PointerEvent pe -> {
          if (pe.action() == PointerEvent.Action.POINTER_DOWN) {
            if (pe.location().x() >= width - 400
                && pe.location().x() <= width - 350
                && pe.location().y() >= height / 10
                && pe.location().y() <= height / 10 + 50) {
              return true;
            } else if (pe.location().x() >= width - 350
                && pe.location().x() <= width - 300
                && pe.location().y() >= height / 10
                && pe.location().y() <= height / 10 + 50) {
              return false;
            }
          }
        }
      }
      event = context.pollOrWaitEvent(10);
    }
  }
}
