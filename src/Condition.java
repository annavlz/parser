public 	class Condition implements RobotProgramNode {
	final String operation;
	Sensor sensor;
	final int number;
	Boolean bool;
	public Condition(String oprt, Sensor sen, int num) {
		this.operation = oprt;
		this.sensor = sen;
		this.number = num;
	}
	public String toString() {
		return operation + "(" + sensor + "," + number + ")";
	}
	@Override
	public void execute(Robot robot) {
		sensor.execute(robot);
		int value = sensor.getValue();
		switch(operation){
			case "lt": bool = value < number;
				break;
			case "gt": bool = value > number;
				break;
			case "eq": bool = value == number;
				break;
		}
	}
	
	public Boolean getBool() {
		return this.bool;
	}
}
