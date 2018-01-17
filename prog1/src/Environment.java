import java.util.List;

public class Environment {
	private List<Point2D> obstacles;
	private List<Point2D> dirts;
	private Point2D roomba;
	private Point2D home;
	Environment(List<Point2D> obstacles, List<Point2D> dirts, Point2D roomba, Point2D home) {
		this.obstacles = obstacles;
		this.dirts = dirts;
		this.roomba = roomba;
		this.home = home;
	}
	
	public void setRoomba(Point2D p) {
		roomba = p;
	}
	
	public Point2D getRoomba() {
		return roomba;
	}
	
	public Point2D getHome() {
		return home;
	}
	
	public List<Point2D> getObstacles() {
		return obstacles;
	}
	
	public List<Point2D> getDirts() {
		return dirts;
	}
	
	public boolean isObstacle(Point2D p) {	
		return obstacles.contains(p);
	}
	
	public boolean isDirt(Point2D p) {	
		return dirts.contains(p);
	}
	
	public void removeDirt(Point2D p) {
		dirts.remove(p);
	}
	
}

