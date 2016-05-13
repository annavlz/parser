package nodes;
import main.Robot;

public 	class Loop implements RobotProgramNode {
	final RobotProgramNode block;
	public Loop (RobotProgramNode block) {
		this.block = block;
	}
	public String toString() {
		return "loop " + block;
	}
	@Override
	public void execute(Robot robot) {
		block.execute(robot);
	}
}
