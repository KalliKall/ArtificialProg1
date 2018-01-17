
public class States {
	private Ori ori;
	States() {
		ori = Ori.North;
	}
	
	public void Turn_Right() {
		ori = ori.getNext();
	}
	
	public void Turn_Left() {
		ori = ori.getPrev();
	}
	
	public void Go() {
	}
	
	private enum Ori {
		North,
		East,
		South,
		West;
		public Ori getNext() {
			return values()[(ordinal()+1) % values().length];
		}
		public Ori getPrev() {
			return values()[(ordinal()+4) % values().length];
		}
	}
}
