package fr.uge.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StackList {
  private final List<StackTileToken> stackTileTokenList = new ArrayList<>();

  /**
   * Add a tile to the stack.
   *
   * @param stackTileToken the tile to add.
   */
  public void add(StackTileToken stackTileToken) {
    Objects.requireNonNull(stackTileToken);
    stackTileTokenList.add(stackTileToken);
  }

  /**
   * Remove a tile from the stack.
   *
   * @param stackTileToken the tile to remove.
   */
  public void remove(StackTileToken stackTileToken) {
    Objects.requireNonNull(stackTileToken);
    stackTileTokenList.remove(stackTileToken);
  }

  /**
   * Get the list of tiles in the stack.
   *
   * @return the list of tiles in the stack.
   */
  public List<StackTileToken> optionTileTokenList() {
    return List.copyOf(stackTileTokenList);
  }
}
