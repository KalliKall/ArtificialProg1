import java.util.List;

public class States {
	private Environment envi;
	States(Environment envi) {
		this.envi = envi;
	}
	
	public void Turn_Right(List<StateNode> nodes, StateNode parent) {
		StateNode child = new StateNode(
				parent.getDirts(),
				parent.getRoomba(),
				parent,
				parent.getOri(),
				parent.getPathCost()
				);
		
		child.setOri(parent.ori.getNext());
		child.setPathCost(parent.getPathCost()+1);
		nodes.add(child);
	}
	
	public void Turn_Left(List<StateNode> nodes, StateNode parent) {
		StateNode child = new StateNode(
				parent.getDirts(),
				parent.getRoomba(),
				parent,
				parent.getOri(),
				parent.getPathCost()
				);
		
		child.setOri(parent.ori.getPrev());
		child.setPathCost(parent.getPathCost()+1);
		nodes.add(child);
	}
	
	public void Go() {
		
		/*
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
		*/
	}
	
	public void Suck() {
		/*
		Point2D roomba = envi.getRoomba();
		if(envi.isDirt(roomba)) {
			envi.removeDirt(roomba);
		}
		*/
 	}
	
}
