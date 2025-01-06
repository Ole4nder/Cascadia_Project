package fr.uge.stack;

import fr.uge.animal.Animals;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class StackOverpopulation {
  private final Map<Animals, Long> overpopulation = new HashMap<>();

  /**
   * Add an animal to the overpopulation map.
   *
   * @param animal the animal to add.
   */
  public void add(Animals animal) {
    Objects.requireNonNull(animal);
    overpopulation.merge(animal, 1L, Long::sum);
  }

  /**
   * Remove an animal from the overpopulation map. If the count becomes 0 or less, removes the
   * animal.
   *
   * @param animal the animal to remove.
   */
  public void remove(Animals animal) {
    Objects.requireNonNull(animal);
    overpopulation.computeIfPresent(animal, (_, count) -> count > 1 ? count - 1 : null);
  }

  /**
   * Check if there is an overpopulation of animals.
   *
   * @return 4 if there is an overpopulation of 4, 3 if there is an overpopulation of 3, 0
   *     otherwise.
   */
  public int asOverpopulation() {
    if (overpopulation.values().stream().anyMatch(count -> count > 3)) {
      return 4; // Overpopulation of 4 reset token on option
    }
    if (overpopulation.values().stream().anyMatch(count -> count == 3)) {
      return 3; // Overpopulation of 3 check player want reset option
    }
    return 0; // No overpopulation
  }

  public Animals getAnimalOverpopulation(int value) {
    return overpopulation.entrySet().stream()
        .filter(entry -> entry.getValue() >= value)
        .map(Map.Entry::getKey)
        .findFirst()
        .orElse(null);
  }

  public void reset() {
    overpopulation.clear();
  }

  /**
   * Return a copy of the current map.
   *
   * @return Map of animals
   */
  public Map<Animals, Long> overpopulation() {
    return Map.copyOf(overpopulation);
  }
}
