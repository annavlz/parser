/**
 * Interface for all nodes that can be executed,
 * including the top level program node
 */

interface RobotProgramNode {
	
	public void execute(Robot robot);
	
	class Statement implements RobotProgramNode {
		final RobotProgramNode child;
		public Statement(RobotProgramNode ch) {
			child = ch;
		}
		public String toString() {
			return child + ";";
		}
		@Override
		public void execute(Robot robot) {
			// TODO Auto-generated method stub
			
		}
	}
	class Action implements RobotProgramNode {
		final String act;
		public Action(String act) {
			this.act = act;
		}
		public String toString() {
			return act + ";";
		}
		@Override
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
	
	class Loop implements RobotProgramNode {
		final Block block;
		public Loop(Block block) {
			this.block = block;
		}
		public String toString() {
			return "loop " + block;
		}
		@Override
		public void execute(Robot robot) {
			// TODO Auto-generated method stub
			
		}
	}
	
	class Block implements RobotProgramNode {
		final Statement stmt;
		public Block(Statement stmt) {
			this.stmt = stmt;
		}
		public String toString() {
			return "{" + stmt + "}";
		}
		@Override
		public void execute(Robot robot) {
			// TODO Auto-generated method stub
			
		}
	}

	
}
