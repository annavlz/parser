package nodes;
import main.Robot;

public 	class ConditionB implements RobotEvalNode {
	final String operation;
	final RobotEvalNode condL;
	final RobotEvalNode condR;
	
	public ConditionB(String oprt, RobotEvalNode condL, RobotEvalNode condR) {
		this.operation = oprt;
		this.condL = condL;
		this.condR = condR;
	}
	

	public String toString() {
		String str = operation + "(" + condL;
		if(condR != null){
			str += "," + condR;
		}
		str +=  ")";
		return str;
	}
	@Override
	public boolean evaluate(Robot robot) {
		boolean cond = false;
		switch(operation){
			case "and": cond = condL.evaluate(robot) && condR.evaluate(robot);
				break;
			case "or": cond = condL.evaluate(robot) || condR.evaluate(robot);
				break;
			case "not": cond = !condL.evaluate(robot);
				break;
		}
		return cond;
	}
	
}

