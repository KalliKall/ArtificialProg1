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
		Point2D p = new Point2D(9999999,999999);

		switch(node.getOri()) {
		case North:
			p = new Point2D(node.getRoomba().x, node.getRoomba().y+1);
		case East:
			p = new Point2D(node.getRoomba().x+1, node.getRoomba().y);
		case South:
			p = new Point2D(node.getRoomba().x, node.getRoomba().y-1);
		case West:
			p = new Point2D(node.getRoomba().x-1, node.getRoomba().y);
		}
		
		for(Point2D x : obstacles) {
			if(x.x == p.x && x.y == p.y) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isHome(StateNode node) {
		return node.getRoomba() == home;
	}
	
}

