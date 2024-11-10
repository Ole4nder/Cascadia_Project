package fr.uge.animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AnimalToken {
	private final List<Animals> tokenList = new ArrayList<>();
	
	/**
	 * Add a new animal.
	 * @param animal, the animal to add.
	 */
	public void add(Animals animal) {
		Objects.requireNonNull(animal);
		
		tokenList.add(animal);
	}
	
	/**
	 * Remove an animal.
	 * @param animal, the animal to remove.
	 */
	public void remove(Animals animal) {
		Objects.requireNonNull(animal);
		
		tokenList.remove(animal);
	}
	
	/**
	 * Return a copy of the current list.
	 * @return List<Animals>
	 */
	public List<Animals> tokenList() {
		return List.copyOf(tokenList);
	}
}
