package fr.uge.option;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OptionList {
  private final List<OptionTileToken> optionTileTokenList = new ArrayList<>();

  public void add(OptionTileToken optionTileToken) {
    Objects.requireNonNull(optionTileToken);
    optionTileTokenList.add(optionTileToken);
  }

  public void remove(OptionTileToken optionTileToken) {
    Objects.requireNonNull(optionTileToken);
    optionTileTokenList.remove(optionTileToken);
  }

  public List<OptionTileToken> optionTileTokenList() {
    return List.copyOf(optionTileTokenList);
  }
}
