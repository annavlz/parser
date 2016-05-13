package nodes;

import java.util.List;
import main.Robot;

public class Prog implements RobotProgramNode {
	final List<RobotProgramNode> children;
	public Prog(List<RobotProgramNode> children) {
		this.children = children;
	}
	public String toString() {
		String prog = "";
		for(RobotProgramNode node : children){
			prog += (node + " ");
		}
		return prog;
	}
	public void execute(Robot robot) {
		for(RobotProgramNode node : children){
			node.execute(robot);
		}			
	}
}
