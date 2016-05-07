
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
		this.block.execute(robot);
	}
}
