public 	class Operation implements RobotProgramNode {
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
	public void execute(Robot robot) {

	}
}

