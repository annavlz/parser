public 	class Operation implements RobotCalcNode {
	final String op;
	final Expression exL;
	final Expression exR;

	public Operation(String op, Expression exL, Expression exR) {
		this.op = op;
		this.exL = exL;
		this.exR = exR;
	}
	public String toString() {
		return op + "(" + exL + "," + exR + ")";
	}
	@Override
	public int calculate(Robot robot) {
		int value = 0;
		switch (op) {
			case "add" : value = exL.calculate(robot) + exR.calculate(robot);
					break;
			case "sub" : value = exL.calculate(robot) - exR.calculate(robot);
					break;
			case "mul" : value = exL.calculate(robot) * exR.calculate(robot);
					break;
			case "div" : value = exL.calculate(robot) / exR.calculate(robot);
					break;
		}
		return value;
	}
}

