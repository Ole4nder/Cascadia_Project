package fr.uge.animal.card;

import fr.uge.animal.Animals;

import java.util.*;

public class AnimalCardImpl {
  private final Map<Animals, HashSet<AnimalCard>> animalMap = new EnumMap<>(Animals.class);

  /**
   * Add a new animal and new card.
   *
   * @param animal, the animal to add.
   * @param card, the card to add.
   */
  public void add(Animals animal, AnimalCard card) {
    Objects.requireNonNull(animal);
    Objects.requireNonNull(card);
    animalMap.computeIfAbsent(animal, _ -> new HashSet<>()).add(card);
  }

  /**
   * Return a copy of the map.
   *
   * @return Map<Animals, HashSet<AnimalCard>>
   */
  public Map<Animals, HashSet<AnimalCard>> animalMap() {
    return Map.copyOf(animalMap);
  }
}
