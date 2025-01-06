package fr.uge.bag;

import fr.uge.animal.Animals;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class AnimalsTokenBag {
  private static final int MAX_ANIMALS = 100;
  private final List<Animals> tokenBag = new ArrayList<>();

  /**
   * Add a new animal.
   *
   * @param animal, the animal to add.
   */
  public void add(Animals animal) {
    Objects.requireNonNull(animal);
    if (tokenBag.size() == MAX_ANIMALS) {
      throw new IllegalStateException("The bag is full.");
    }
    tokenBag.add(animal);
  }

  /**
   * Remove an animal.
   *
   * @param index the animal position to remove.
   */
  public Animals remove(int index) {
    if (index < 0 || index >= tokenBag.size()) {
      throw new IllegalArgumentException("Index out of bound.");
    }
    return tokenBag.remove(index);
  }

  /**
   * Return a copy of the current list.
   *
   * @return List of animals
   */
  public List<Animals> tokenBag() {
    return List.copyOf(tokenBag);
  }

  /** Draw animals from the bag. */
  public void shuffle() {
    Collections.shuffle(tokenBag);
  }

  /**
   * Draw animals from the bag.
   *
   * @return List of animals to draw
   */
  public List<Animals> drawAnimal(int maxAnimalDrawn) {
    List<Animals> animals = new ArrayList<>(tokenBag.subList(0, maxAnimalDrawn));
    tokenBag.subList(0, maxAnimalDrawn).clear();
    return animals;
  }

  /** Create all animal tokens for the game. */
  // TODO change place of method ?
  public void addAllToken() {
    for (int i = 0; i < 20; i++) {
      add(Animals.BEAR);
      add(Animals.ELK);
      add(Animals.SALMON);
      add(Animals.FOX);
      add(Animals.HAWK);
    }
    shuffle();
  }
}
