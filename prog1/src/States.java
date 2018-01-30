import java.util.ArrayList;
import java.util.List;

public class States {
	
	private Environment envi;
	States(Environment envi) {
		this.envi = envi;
	}
	
	public StateNode Turn_Right(StateNode parent) {
		StateNode child = createNode(parent);
		
		child.setOri(parent.ori.getNext());
		child.setStatus(Status.TURN_RIGHT);
		child.setPathCost(parent.getPathCost()+1);
		
		return child;
	}
	
	public StateNode Turn_Left(StateNode parent) {
		StateNode child = createNode(parent);
		
		child.setOri(parent.ori.getPrev());
		child.setStatus(Status.TURN_LEFT);
		child.setPathCost(parent.getPathCost()+1);
		
		return child;
	}
	
	public StateNode Go(StateNode parent) {
		StateNode child = createNode(parent);
		Point2D roomba = child.getRoomba();
		switch(child.getOri()) {
		case North:
			child.setRoomba(roomba.x, roomba.y+1);
			break;
		case East:
			child.setRoomba(roomba.x+1, roomba.y);
			break;
		case South:
			child.setRoomba(roomba.x, roomba.y-1);
			break;
		case West:
			child.setRoomba(roomba.x-1, roomba.y);
			break;
		}
		
		child.setStatus(Status.GO);
		child.setPathCost(parent.getPathCost()+1);
		
		if(envi.isHome(child) && child.getDirts().size() == 0) {
			child.setGoal(true);
		}
		
		return child;
		
	}
	
	public StateNode Suck(StateNode parent) {
		StateNode child = createNode(parent);
		
		List<Point2D> temp = new ArrayList<Point2D>();
		for(Point2D x : child.getDirts()) {
			if(child.getRoomba().x != x.x && child.getRoomba().y != x.y) {
				temp.add(x);
			}
		}
		
		child.setDirts(temp);
		child.setStatus(Status.SUCK);
		child.setPathCost(parent.getPathCost()+1);
			
		return child;
 	}
	
	private StateNode createNode(StateNode parent) {
		Point2D roomba = new Point2D(parent.getRoomba().x, parent.getRoomba().y);
		List<Point2D> dirts = new ArrayList<Point2D>();
		dirts.addAll(parent.getDirts());
		Orientation ori = parent.getOri();
		Status stat = parent.getStatus();
		int path = parent.getPathCost();
		
		StateNode child = new StateNode(
				dirts,
				roomba,
				parent,
				ori,
				path,
				stat,
				parent.getNumber()+1
				);
		
		return child;
	}
	
}
