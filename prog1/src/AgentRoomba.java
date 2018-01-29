import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AgentRoomba implements Agent {
	private Environment envi;
	private StateNode initNode;
	private Comparator<StateNode> comparator;
    PriorityQueue<StateNode> queue;
    private States states;
    private Stack<StateNode> nodeList = new Stack<StateNode>();
	

	/*
		init(Collection<String> percepts) is called once before you have to select the first action. Use it to find a plan. Store the plan and just execute it step by step in nextAction.
	*/

    public void init(Collection<String> percepts) {
    	List<Point2D> obstacles = new ArrayList<Point2D>();
    	List<Point2D> dirts = new ArrayList<Point2D>();
    	Point2D home = new Point2D();
    	Point2D size;
    	Orientation ori = Orientation.West;
  
    	
    	
    	/*
			Possible percepts are:
			- "(SIZE x y)" denoting the size of the environment, where x,y are integers
			- "(HOME x y)" with x,y >= 1 denoting the initial position of the robot
			- "(ORIENTATION o)" with o in {"NORTH", "SOUTH", "EAST", "WEST"} denoting the initial orientation of the robot
			- "(AT o x y)" with o being "DIRT" or "OBSTACLE" denoting the position of a dirt or an obstacle
			Moving north increases the y coordinate and moving east increases the x coordinate of the robots position.
			The robot is turned off initially, so don't forget to turn it on.
		*/
		Pattern perceptNamePattern = Pattern.compile("\\(\\s*([^\\s]+).*");
		for (String percept:percepts) {
			Matcher perceptNameMatcher = perceptNamePattern.matcher(percept);
			if (perceptNameMatcher.matches()) {
				String perceptName = perceptNameMatcher.group(1);
				if (perceptName.equals("HOME")) {
					Matcher m = Pattern.compile("\\(\\s*HOME\\s+([0-9]+)\\s+([0-9]+)\\s*\\)").matcher(percept);
					if (m.matches()) {
						home.x =Integer.parseInt(m.group(1));
						home.y = Integer.parseInt(m.group(2));
						System.out.println("Home is at " + m.group(1) + "," + m.group(2));
						
					}
				} else if (perceptName.equals("SIZE")) {
					Matcher m = Pattern.compile("\\(\\s*SIZE\\s+([0-9]+)\\s+([0-9]+)\\s*\\)").matcher(percept);
					if (m.matches()) {
						size = new Point2D(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)));
						System.out.println("size is " + m.group(1) + "," + m.group(2));
						for(int i = 0; i < size.x+2; i++) {
							Point2D p = new Point2D(i, 0);
							obstacles.add(p);
						}
						
						for(int i = 0; i < size.x+2; i++) {
							Point2D p = new Point2D(i, size.y+1);
							obstacles.add(p);
						}
						
						for(int i = 1; i < size.y+1; i++) {
							Point2D p = new Point2D(0, i);
							obstacles.add(p);
						}
						
						for(int i = 1; i < size.y+1; i++) {
							Point2D p = new Point2D(size.x+1, i);
							obstacles.add(p);
						}
						
					}
				} else if (perceptName.equals("ORIENTATION")) {
					Matcher m = Pattern.compile("\\(\\s*ORIENTATION\\s*NORTH\\s*\\)").matcher(percept);
					if (m.matches()) {
						ori = Orientation.North;
					}
					m = Pattern.compile("\\(\\s*ORIENTATION\\s*EAST\\s*\\)").matcher(percept);
					if (m.matches()) {
						ori = Orientation.East;
					}
					m = Pattern.compile("\\(\\s*ORIENTATION\\s*SOUTH\\s*\\)").matcher(percept);
					if (m.matches()) {
						ori = Orientation.South;
					}
					m = Pattern.compile("\\(\\s*ORIENTATION\\s*WEST\\s*\\)").matcher(percept);
					if (m.matches()) {
						ori = Orientation.West;
					}
					System.out.println("Ori is " + ori.toString());
				} else if (perceptName.equals("AT")) {
					Matcher m = Pattern.compile("\\(\\s*AT\\s*DIRT\\s+([0-9]+)\\s+([0-9]+)\\s*\\)").matcher(percept);
					if (m.matches()) {
						dirts.add(new Point2D(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2))));
						System.out.println("Dirt added at " + m.group(1) + "," + m.group(2));
					}
					m = Pattern.compile("\\(\\s*AT\\s*OBSTACLE\\s+([0-9]+)\\s+([0-9]+)\\s*\\)").matcher(percept);
					if (m.matches()) {
						obstacles.add(new Point2D(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2))));
						//System.out.println("Obstacle added at " + m.group(1) + "," + m.group(2));
					}
				} else {
					System.err.println("strange percept that does not match pattern: " + percept);
				}
			}
		}
		
		envi = new Environment(obstacles, home);
		states = new States(envi);
		initNode = new StateNode(
				dirts,
				home,
				null,
				ori,
				0,
				Status.TURN_ON,
				1
				);
		
		for(Point2D x : obstacles) {
			System.out.println("Obstacle is at: " + x.x + ", " + x.y);

		}
		
		
		System.out.println("Obstacle size is: " + obstacles.size());
		
		
		StateNode goalNode = findGoalState();
		nodeList = goalPath(nodeList, goalNode);
		
		/*System.out.println(goalNode.getRoomba().x + ", " + goalNode.getRoomba().y);
		
		System.out.println(goalNode.getParent().getRoomba().x + ", " + goalNode.getParent().getRoomba().y);
		
		System.out.println(goalNode.getDirts().size());*/
    }
    
    private Stack<StateNode> goalPath(Stack<StateNode> nodeList, StateNode goalNode) {
    	StateNode node = goalNode;
    	
    	while(node.getParent() != null) {
		System.out.println(node.getStatus());
    		nodeList.push(node);
    		node = node.getParent();
    	}
    	
    	nodeList.push(initNode);
    	
    	return nodeList;
    }
    
    private StateNode findGoalState() {
    	comparator = new UniformCostComparator();
        queue = new PriorityQueue<StateNode>(comparator);
        
        queue.add(initNode);
        
    	if(initNode.getGoal()) {
    		return initNode;
    	}
    	
    	StateNode node;
    	
        while(!queue.isEmpty()) {
        	StateNode head = queue.poll();
        	if(head.isDirt()) {
        		node = states.Suck(head);
        		queue.add(states.Suck(head));
        		//System.out.println(node.getRoomba().x + ", " + node.getRoomba().y);
        	}
        	else {
        		if(envi.isObstacle(head)) {
        			node = states.Turn_Left(head);
        			queue.add(node);
        			queue.add(states.Turn_Right(head));
            		//System.out.println("Obstacle");
            		//System.out.println(node.getOri().toString());
            		//System.out.println(node.getRoomba().x + ", " + node.getRoomba().y);
            		
        		}
        		else {
        			queue.add(states.Turn_Left(head));
        			queue.add(states.Turn_Right(head));
        			
        			node = states.Go(head);
                	if(node.getGoal()) {
                		return node;
                	}
                	
        			queue.add(node);
                	
             
                	
            		//System.out.println(node.getRoomba().x + ", " + node.getRoomba().y);
        		}
        	}
        }
        
        return null;
    }

    public String nextAction(Collection<String> percepts) {
		System.out.print("perceiving:");
		for(String percept:percepts) {
			System.out.print("'" + percept + "', ");
		}
		System.out.println("");
		if(nodeList.isEmpty()) {
			return "TURN_OFF";
		}
		else {
			StateNode node = nodeList.pop();
			System.out.println("popping");
			System.out.println(node.getStatus());
			return node.getStatus().toString();
		}
	}
}
