
public enum Orientation {
	North,
	East,
	South,
	West;
	public Orientation getNext() {
		return values()[(ordinal()+1) % values().length];
	}
	public Orientation getPrev() {
		return values()[(ordinal()+3) % values().length];
	}
}
