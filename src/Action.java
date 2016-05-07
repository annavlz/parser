public class Action implements RobotProgramNode {
	final String act;
	public Action(String act) {
		this.act = act;
	}
	public String toString() {
		return act;
	}

	public void execute(Robot robot) {
		switch(act) {
		case "move" : robot.move();
					break;
		case "turnL" : robot.turnLeft();
					break;
		case "turnR" : robot.turnRight();
					break;
		case "takeFuel" : robot.takeFuel();
					break;
		case "wait" : robot.idleWait();
					break;
		}					
	}
}
