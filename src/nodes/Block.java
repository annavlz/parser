package nodes;
import main.Robot;
import java.util.List;

public 	class Block implements RobotProgramNode {
	final List<RobotProgramNode> stmts;
	public Block(List<RobotProgramNode> stmts) {
		this.stmts = stmts;
	}
	public String toString() {
		String block = "{";
		for(RobotProgramNode stmt : stmts){
			block += " " + stmt;
		}
		block += " }";
		return block;
	}
	@Override
	public void execute(Robot robot) {
		for(RobotProgramNode stmt : stmts){
			stmt.execute(robot);	
		}
	}
}

