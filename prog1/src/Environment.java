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
	
	
	public boolean isObstacle(StateNode node) {	

		switch(node.ori) {
		case North:
			if(obstacles.contains(new Point2D(node.getRoomba().x, node.getRoomba().y+1))) {
				return true;
			}
			else {
				return false;
			}
		case East:
			if(obstacles.contains(new Point2D(node.getRoomba().x+1, node.getRoomba().y))) {
				return true;
			}
			else {
				return false;
			}
		case South:
			if(obstacles.contains(new Point2D(node.getRoomba().x, node.getRoomba().y-1))) {
				return true;
			}
			else {
				return false;
			}
		case West:
			if(obstacles.contains(new Point2D(node.getRoomba().x-1, node.getRoomba().y))) {
				return true;
			}
			else {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean isHome(StateNode node) {
		return node.getRoomba() == home;
	}
	
}

