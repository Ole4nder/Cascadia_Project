package fr.uge.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StackList {
  private final List<StackTileToken> stackTileTokenList = new ArrayList<>();

  public void add(StackTileToken stackTileToken) {
    Objects.requireNonNull(stackTileToken);
    stackTileTokenList.add(stackTileToken);
  }

  public void remove(StackTileToken stackTileToken) {
    Objects.requireNonNull(stackTileToken);
    stackTileTokenList.remove(stackTileToken);
  }

  public List<StackTileToken> optionTileTokenList() {
    return List.copyOf(stackTileTokenList);
  }
}
