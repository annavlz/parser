
public class Expression implements RobotCalcNode{
	final RobotCalcNode child;

	public Expression(RobotCalcNode ch){
		this.child = ch;
	}
	public String toString() {
		return "(" + child + ")";
	}
	public int calculate (Robot robot){
		return child.calculate(robot);
	}
}
