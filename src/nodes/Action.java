package nodes;
import main.Robot;

public class Action implements RobotProgramNode {
	final String act;
	final Expression exp;
	

	public Action(String act, Expression exp) {
		this.act = act;
		this.exp = exp;
	}
	
	public String toString() {
		String str = act + "";
		if(exp != null){
			str += exp;
		}
		return str;
	}

	public void execute(Robot robot) {
		switch(act) {
		case "move" : 
					if(exp != null){
						for(int i = 0; i < exp.calculate(robot); i++){
							robot.move();
						}	
					}
					else {
						robot.move();
					}
					break;
		case "turnL" : robot.turnLeft();
					break;
		case "turnR" : robot.turnRight();
					break;
		case "takeFuel" : robot.takeFuel();
					break;
		case "wait" : 			
					if(exp != null){
						for(int i = 0; i < exp.calculate(robot); i++){
							robot.idleWait();
						}	
					}
					else {
					robot.idleWait();
					}
					break;
		case "shieldOn" : robot.setShield(true);
					break;
		case "shieldOff" : robot.setShield(false);
					break;
		case "turnAround" : robot.turnAround();
		}					
	}
}
