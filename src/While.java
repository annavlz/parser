public 	class While implements RobotProgramNode {
	final Condition condition;
	final Block block;
	Boolean bool;
	
	public While(Condition cond, Block block) {
		this.condition = cond;
		this.block = block;
	}
	public String toString() {
		return "while("+ condition + ")" + block;
	}
	@Override
	public void execute(Robot robot) {
		condition.execute(robot);
		bool = condition.getBool();
		while (bool){
			block.execute(robot);
		}
	}
}
