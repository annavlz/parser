
public class Expression implements RobotProgramNode{
	final RobotProgramNode child;
	int value = 0;
	public Expression(RobotProgramNode ch){
		this.child = ch;
	}
	public String toString() {
		return "(" + child + ")";
	}
	public void execute (Robot robot){
		child.execute(robot);
		value += child.getValue();
	}
	
	public int getValue(){
		return this.value;
	}
}
