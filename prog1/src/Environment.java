import java.util.List;

public class Environment {
	private List<Point2D> obstacles;
	private Point2D home;
	Environment(List<Point2D> obstacles, Point2D home) {
		this.obstacles = obstacles;
		this.home = home;
	}
	
	
	public Point2D getHome() {
		return home;
	}
	
	public List<Point2D> getObstacles() {
		return obstacles;
	}
	
	
	public boolean isObstacle(Point2D p) {	
		return obstacles.contains(p);
	}
	
}

