public 	class If implements RobotProgramNode {
	final Condition condition;
	final Block block;
	Boolean bool;
	
	public If(Condition cond, Block block) {
		this.condition = cond;
		this.block = block;
	}
	public String toString() {
		return "if("+ condition + ")" + block;
	}
	@Override
	public void execute(Robot robot) {
		condition.execute(robot);
		bool = condition.getBool();
		if(bool){
			block.execute(robot);
		}
	}
}
