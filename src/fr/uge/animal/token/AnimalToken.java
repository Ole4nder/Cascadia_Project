package fr.uge.animal.token;

import fr.uge.animal.Animals;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class AnimalToken {
  private final List<Animals> tokenList = new ArrayList<>();

  /**
   * Add a new animal.
   *
   * @param animal, the animal to add.
   */
  public void add(Animals animal) {
    Objects.requireNonNull(animal);

    tokenList.add(animal);
  }

  /**
   * Remove an animal.
   *
   * @param animal, the animal to remove.
   */
  public void remove(Animals animal) {
    Objects.requireNonNull(animal);
    tokenList.remove(animal);
  }

  /**
   * Return a copy of the current list.
   *
   * @return List<Animals>
   */
  public List<Animals> tokenList() {
    return List.copyOf(tokenList);
  }
  
  /**
   * Create all animal tokens.
   */
  public void addAllToken() {
  	for (int i = 0; i < 20; i++) {
  		add(Animals.BEAR);
  		add(Animals.ELK);
  		add(Animals.SALMON);
  		add(Animals.FOX);
  		add(Animals.HAWK);
  	}
  	Collections.shuffle(tokenList);
  }
}
