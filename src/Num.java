public 	class Num implements RobotProgramNode {
	final int number;

	public Num(int num) {
		this.number = num;
	}
	public String toString() {
		return number + "";
	}
	@Override
	public void execute(Robot robot) {

	}
	
	public int getNum() {
		return this.number;
	}
}

