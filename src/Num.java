public 	class Num implements RobotCalcNode {
	final int number;

	public Num(int num) {
		this.number = num;
	}
	public String toString() {
		return number + "";
	}
	@Override
	public int calculate (Robot robot) {
		return this.number;
	}
}

