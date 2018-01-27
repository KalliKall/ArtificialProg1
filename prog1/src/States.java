import java.util.ArrayList;
import java.util.List;

public class States {
	
	private List<StateNode> nodes;
	private Environment envi;
	States(Environment envi) {
		this.nodes = new ArrayList<StateNode>();
		this.envi = envi;
	}
	
	public StateNode Turn_Right(StateNode parent) {
		StateNode child = createNode(parent);
		
		child.setOri(parent.ori.getNext());
		child.setStatus(Status.Turn_Right);
		child.setPathCost(parent.getPathCost()+1);
		nodes.add(child);
		
		return child;
	}
	
	public StateNode Turn_Left(StateNode parent) {
		StateNode child = createNode(parent);
		
		child.setOri(parent.ori.getPrev());
		child.setStatus(Status.Turn_Left);
		child.setPathCost(parent.getPathCost()+1);
		nodes.add(child);
		
		return child;
	}
	
	public StateNode Go(StateNode parent) {
		StateNode child = createNode(parent);
		
		Point2D roomba = parent.getRoomba();
		switch(child.ori) {
		case North:
			child.setRoomba(roomba.x, roomba.y+1);
		case East:
			child.setRoomba(roomba.x+1, roomba.y);
		case South:
			child.setRoomba(roomba.x, roomba.y-1);
		case West:
			child.setRoomba(roomba.x-1, roomba.y);
		}
		
		child.setStatus(Status.Go);
		child.setPathCost(parent.getPathCost()+1);
		nodes.add(child);
		
		if(envi.isHome(child) && child.getDirts().isEmpty()) {
			child.setGoal(true);
		}
		
		return child;
		
	}
	
	public StateNode Suck(StateNode parent) {
		StateNode child = createNode(parent);
		
		List<Point2D> temp = parent.getDirts();
		
		if(child.isDirt()) {
			temp.remove(child.getRoomba());
		}
		child.setDirts(temp);
		child.setStatus(Status.Suck);
		child.setPathCost(parent.getPathCost()+1);
		nodes.add(child);
			
		return child;
 	}
	
	private StateNode createNode(StateNode parent) {
		StateNode child = new StateNode(
				parent.getDirts(),
				parent.getRoomba(),
				parent,
				parent.getOri(),
				parent.getPathCost(),
				parent.getStatus(),
				nodes.size()+1
				);
		
		parent.addChild(child);
		return child;
	}
	
}
