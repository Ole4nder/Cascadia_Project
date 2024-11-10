package fr.uge.animal.card;

import fr.uge.animal.Animals;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

public class AnimalCardImpl {
	private final Map<Animals, HashSet<AnimalCard>> animalMap = new HashMap<>();
	
	/**
	 * Add a new animal and new card.
	 * @param animal, the animal to add.
	 * @param card, the card to add.
	 */
	public void add(Animals animal, AnimalCard card) {
		Objects.requireNonNull(animal);
		Objects.requireNonNull(card);
		
		animalMap.computeIfAbsent(animal, t -> new HashSet<>()).add(card);
	}
	
	/**
	 * Return a copy of the map.
	 * @return Map<Animals, HashSet<AnimalCard>>
	 */
	public Map<Animals, HashSet<AnimalCard>> animalMap() {
		return Map.copyOf(animalMap);
	}
}
