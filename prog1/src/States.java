
public class States {
	private Ori ori;
	private Environment envi;
	States(Environment envi) {
		ori = Ori.North;
		this.envi = envi;
	}
	
	public void Turn_Right() {
		ori = ori.getNext();
	}
	
	public void Turn_Left() {
		ori = ori.getPrev();
	}
	
	public void Go() {
		Point2D roomba = envi.getRoomba();
		switch(ori) {
		case North:
			envi.setRoomba(roomba.x, roomba.y+1);
		case East:
			envi.setRoomba(roomba.x+1, roomba.y);
		case South:
			envi.setRoomba(roomba.x, roomba.y-1);
		case West:
			envi.setRoomba(roomba.x-1, roomba.y);
		}
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
			return values()[(ordinal()+3) % values().length];
		}
	}
}
