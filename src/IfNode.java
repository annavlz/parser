public 	class IfNode implements RobotProgramNode {
	final RobotEvalNode condition;
	final RobotProgramNode block;

	
	public IfNode(RobotEvalNode cond, RobotProgramNode block) {
		this.condition = cond;
		this.block = block;
	}
	public String toString() {
		return "if("+ condition + ")" + block;
	}
	@Override
	public void execute(Robot robot) {
		if(condition.evaluate(robot)){
			block.execute(robot);
		}
	}
}
