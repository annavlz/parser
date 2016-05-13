public 	class ConditionI implements RobotEvalNode {
	final String operation;
	final Expression expL;
	final Expression expR;
	
	public ConditionI(String oprt, Expression expL, Expression expR) {
		this.operation = oprt;
		this.expL = expL;
		this.expR = expR;
	}
	

	public String toString() {
		return operation + "(" + expL + "," + expR + ")";
	}
	@Override
	public boolean evaluate(Robot robot) {
		boolean cond = false;
		switch(operation){
			case "lt": cond = expL.calculate(robot) < expR.calculate(robot);
				break;
			case "gt": cond = expL.calculate(robot) > expR.calculate(robot);
				break;
			case "eq": cond = expL.calculate(robot) == expR.calculate(robot);
				break;
		}
		return cond;
	}
	
}
