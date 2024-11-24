package fr.uge.animal.card;

import java.util.Objects;

/***
 * Represents an animal card with a type and a description.
 * @param type
 * @param description
 */
public record AnimalCard(TypeCard type, String description) {
	/**
	 * Create a new AnimalCard with type and description.
	 *
	 * @param type, the type of the card.
	 * @param description, the description of the card.
	 */
	public AnimalCard {
		Objects.requireNonNull(description);
		Objects.requireNonNull(type);
	}
}
