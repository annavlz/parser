public 	class While implements RobotProgramNode {
	final  RobotEvalNode condition;
	final RobotProgramNode block;
	
	public While(RobotEvalNode cond, RobotProgramNode block) {
		this.condition = cond;
		this.block = block;
	}
	public String toString() {
		return "while("+ condition + ")" + block;
	}
	@Override
	public void execute(Robot robot) {
		while (condition.evaluate(robot)){
			block.execute(robot);
		}
	}
}
