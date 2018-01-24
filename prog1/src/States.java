
public class States {
	private Ori ori;
	private Environment envi;
	States(Environment envi) {
		ori = Ori.North;
		this.envi = envi;
	}
	
	public void Turn_Right() {
		switch(ori) {
		case North:
			ori = Ori.East;
		case East:
			ori = Ori.South;
		case South:
			ori = Ori.West;
		case West:
			ori = Ori.North;
		}
	}
	
	public void Turn_Left() {
		switch(ori) {
		case North:
			ori = Ori.West;
		case East:
			ori = Ori.North;
		case South:
			ori = Ori.East;
		case West:
			ori = Ori.South;
		}
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
	}
}
