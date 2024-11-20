package fr.uge.animal.card;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

import fr.uge.animal.Animals;

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
  
  /**
   * Get variant name from file and turn them into TypeCard.
   * 
   * @param variant
   * @return
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
   * @throws IOException
   */
  public void addAllAnimalCard() throws IOException {
  	try(var reader = Files.newBufferedReader(Path.of("ressources/animalCard/animalCardDescription.txt"))) {
    	String line;
    	while ((line = reader.readLine()) != null) {
    		var part = line.split(" : ");
    		add(Animals.animalNameToEnums(part[1]), new AnimalCard(cardNameToType(part[0]), part[2]));
    	}
  	}
  }
}
