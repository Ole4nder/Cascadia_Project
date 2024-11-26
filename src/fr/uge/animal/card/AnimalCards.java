package fr.uge.animal.card;

import fr.uge.animal.Animals;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

/**
 * Represents the implementation of the AnimalCard interface.
 */
public class AnimalCards {
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
   * @return Map of key animals and value a set of animal cards.
   */
  public Map<Animals, HashSet<AnimalCard>> animalMap() {
    return Map.copyOf(animalMap);
  }
  
  /**
   * Get variant name from file and turn them into TypeCard.
   * 
   * @param variant the variant name
   * @return TypeCard
   */
  private TypeCard cardNameToType(String variant) {
  	return switch(variant) {
	  	case "A" -> TypeCard.A;
	  	case "B" -> TypeCard.B;
	  	case "C" -> TypeCard.C;
	  	case "D" -> TypeCard.D;
	  	case "Familial" -> TypeCard.FAMILIAL;
	  	case "Intermediate" -> TypeCard.INTERMEDIATE;
			default -> throw new IllegalArgumentException("Unexpected value: " + variant);
  	};
  }
  
  /**
   * Add all AnimalCard from the game.
   * 
   * @throws IOException nor found the file
   */
  public void addAllAnimalCard() throws IOException {
  	try(var reader = Files.newBufferedReader(Path.of("src/resources/animalCard/animalCardDescription.txt"))) {
    	String line;
    	while ((line = reader.readLine()) != null) {
    		var part = line.split(" : ");
    		add(Animals.animalNameToEnums(part[1]), new AnimalCard(cardNameToType(part[0]), part[2]));
    	}
  	}
  }
}
