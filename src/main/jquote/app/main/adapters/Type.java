package app.main.adapters;

/**
 * app.main.adapters Created by Pierre-Alexandre Adamski on 22/05/16.
 */
public enum Type {
	TRIANGLE,
	RECTANGLE,
	TRAPEZE(),
	CIRCLE,
	ARC;

	@Override
	public String toString() {
		return name().toLowerCase();
	}
}
