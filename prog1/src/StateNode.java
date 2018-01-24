import java.util.List;


public class StateNode {
	public Ori ori;
	private List<Point2D> dirts;
	private Point2D roomba;
	private int pathCost;
	private List<StateNode> childs;
	private StateNode parent;
	private Status status;
	
	StateNode(List<Point2D> dirts, Point2D roomba, StateNode parent, Ori ori, int pathCost, Status status) {
		this.dirts = dirts;
		this.roomba = roomba;
		this.parent = parent;
		this.ori = ori;
		this.pathCost = pathCost;
		this.status = status;
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
	
	public Ori getOri() {
		return ori;
	}
	
	public void setOri(Ori ori ) {
		this.ori = ori;
	}
	
	public Point2D getRoomba() {
		return roomba;
	}
	
	public void setRoomba(Point2D roomba) {
		this.roomba = roomba;
	}
	
	public List<Point2D> getDirts() {
		return dirts;
	}
	
	public void setDirts(List<Point2D> dirts) {
		this.dirts = dirts;
	}
	
	public boolean isDirt(Point2D p) {	
		return dirts.contains(p);
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
	
	public enum Ori {
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
	
	public enum Status {
		Suck,
		Turn_Left,
		Turn_Right,
		Go,
		Turn_Off;
	}
}
