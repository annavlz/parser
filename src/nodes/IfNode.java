package nodes;
import main.Robot;

public 	class IfNode implements RobotProgramNode {
	final RobotEvalNode condition;
	final RobotProgramNode block;
	final RobotProgramNode elseBlock;

	
	public IfNode(RobotEvalNode cond, RobotProgramNode block, RobotProgramNode elseBlock) {
		this.condition = cond;
		this.block = block;
		this.elseBlock = elseBlock;
	}
	public String toString() {
		String str = "if("+ condition + ")" + block;
		if(elseBlock != null){
			str += " else " + elseBlock; 
		}
		return str;
	}
	@Override
	public void execute(Robot robot) {
		if(condition.evaluate(robot)){
			System.out.println("if true");
			block.execute(robot);
		} 
		else {
			System.out.println("if false");
			elseBlock.execute(robot);
		}


	}
}
