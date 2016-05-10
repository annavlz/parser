

/**
 * Interface for all nodes that can be executed,
 * including the top level program node
 */

interface RobotProgramNode {
	int value = 0;
	public void execute(Robot robot);
	
}

