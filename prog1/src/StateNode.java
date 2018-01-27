import java.util.List;


public class StateNode {
	public Orientation ori;
	public Status status;
	private List<Point2D> dirts;
	private Point2D roomba;
	private int pathCost;
	private List<StateNode> childs;
	private StateNode parent;
	private int number;
	private boolean isGoal;
	
	StateNode(List<Point2D> dirts, Point2D roomba, StateNode parent, Orientation ori, int pathCost, Status status, int number) {
		this.dirts = dirts;
		this.roomba = roomba;
		this.parent = parent;
		this.ori = ori;
		this.pathCost = pathCost;
		this.status = status;
		this.number = number;
		this.isGoal = false;
	}
	
	public boolean getGoal() {
		return isGoal;
	}
	
	public void setGoal(boolean b) {
		this.isGoal = b;
	}
	
	public int getPathCost() {
		return pathCost;
	}
	
	public void setPathCost(int cost ) {
		this.pathCost = cost;
	}
	
	public StateNode getParent() {
		return parent;
	}
	
	public Orientation getOri() {
		return ori;
	}
	
	public void setOri(Orientation ori ) {
		this.ori = ori;
	}
	
	public Point2D getRoomba() {
		return roomba;
	}
	
	public void setRoomba(int x, int y) {
		this.roomba.x = x;
		this.roomba.y = y;
	}
	
	public List<Point2D> getDirts() {
		return dirts;
	}
	
	public void setDirts(List<Point2D> dirts) {
		this.dirts = dirts;
	}
	
	public boolean isDirt() {	
		return dirts.contains(roomba);
	}
	
	public void addChild(StateNode child) {
		childs.add(child);
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public int getNumber() {
		return number;
	}
	
}
