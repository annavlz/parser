public 	class Sensor implements RobotCalcNode {
	final String name;
	
	public Sensor(String name) {
		this.name = name;
	}
	public String toString() {
		return name;
	}
	@Override
	public int calculate(Robot robot) {
		int value = 0;
		switch (name){
			case "fuelLeft" : value = robot.getFuel();
								break;
			case "oppLR" : value = robot.getOpponentLR();
								break;
			case "oppFB" : value = robot.getOpponentFB();
								break;
			case "numBarrels" : value = robot.numBarrels();
								break;
			case "barrelLR" : value = robot.getClosestBarrelLR();
								break;
			case "barrelFB" : value = robot.getClosestBarrelFB();
								break;
			case "wallDist" : value = robot.getDistanceToWall();
								break;
		}
		return value;
	}
}
