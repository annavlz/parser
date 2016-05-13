package nodes;
import main.Robot;

public class Statement implements RobotProgramNode {
	final RobotProgramNode child;
	public Statement(RobotProgramNode ch) {
		this.child = ch;
	}
	public String toString() {
		return child + ";";
	}
	public void execute(Robot robot) {
		this.child.execute(robot);		
	}
}
