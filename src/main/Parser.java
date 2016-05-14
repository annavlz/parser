package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.*;
import javax.swing.JFileChooser;
import nodes.*;


/**
 * The parser and interpreter. The top level parse function, a main method for
 * testing, and several utility methods are provided. You need to implement
 * parseProgram and all the rest of the parser.
 */
public class Parser {

	/**
	 * Top level parse method, called by the World
	 */
	static RobotProgramNode parseFile(File code) {
		Scanner scan = null;
		try {
			scan = new Scanner(code);

			// the only time tokens can be next to each other is
			// when one of them is one of (){},;
			scan.useDelimiter("\\s+|(?=[{}(),;])|(?<=[{}(),;])");
			RobotProgramNode n = parseProgram(scan); // You need to implement this!!!

			scan.close();
			return n;
		} catch (FileNotFoundException e) {
			System.out.println("Robot program source file not found");
		} catch (ParserFailureException e) {
			System.out.println("Parser error:");
			System.out.println(e.getMessage());
			scan.close();
		}
		return null;
	}
	
	/** For testing the parser without requiring the world */

	public static void main(String[] args) {
		if (args.length > 0) {
			for (String arg : args) {
				File f = new File(arg);
				if (f.exists()) {
					System.out.println("Parsing '" + f + "'");
					RobotProgramNode prog = parseFile(f);
					System.out.println("Parsing completed ");
					if (prog != null) {
						System.out.println("================\nProgram:");
						System.out.println(prog);
					}
					System.out.println("=================");
				} else {
					System.out.println("Can't find file '" + f + "'");
				}
			}
		} else {
			while (true) {
				JFileChooser chooser = new JFileChooser(".");// System.getProperty("user.dir"));
				int res = chooser.showOpenDialog(null);
				if (res != JFileChooser.APPROVE_OPTION) {
					break;
				}
				RobotProgramNode prog = parseFile(chooser.getSelectedFile());
				System.out.println("Parsing completed");
				if (prog != null) {
					System.out.println("Program: \n" + prog);
				}
				System.out.println("=================");
			}
		}
		System.out.println("Done");
	}

	// Useful Patterns

	static Pattern NUMPAT = Pattern.compile("-?\\d+"); // ("-?(0|[1-9][0-9]*)");
	static Pattern OPENPAREN = Pattern.compile("\\(");
	static Pattern CLOSEPAREN = Pattern.compile("\\)");
	static Pattern OPENBRACE = Pattern.compile("\\{");
	static Pattern CLOSEBRACE = Pattern.compile("\\}");

	/**
	 * PROG ::= STMT+
	 */
	static RobotProgramNode parseProgram(Scanner s) {
		List<RobotProgramNode> children = new ArrayList<RobotProgramNode>();
		while(s.hasNext()){
			RobotProgramNode stmt = parseStatement(s);
			children.add(stmt);
		}
		return new Prog(children);
	}
	static RobotProgramNode parseStatement(Scanner s) {
		if (s.hasNext("move|turnL|turnR|takeFuel|wait|shieldOn|shieldOff|turnAround")){
			return new Statement(parseAction(s));
		}
		else if (s.hasNext("loop")) {
			return parseLoop(s);
		}
		else if (s.hasNext("if")) {
			return parseIf(s);
		}
		else if (s.hasNext("while")){
			return parseWhile(s);
		}
		else {
			fail("Can't read", s);
			return null;
		}
	}
	static RobotProgramNode parseAction(Scanner s) {
		String act;
		Expression exp;
		act = s.next();
		if(s.hasNext("\\(")){
			require("\\(", "Expecting (", s);
			exp = parseExpression(s);
			require("\\)", "Expecting )", s);
		}
		else{
			exp = null;
		}
		require(";", "Expecting ;", s);
		return new Action(act, exp);
				
	}
	
	static Expression parseExpression (Scanner s) {
		RobotCalcNode ch;
		if(s.hasNext("-?[1-9][0-9]*|0")){
			ch = new Num(s.nextInt());
		}
		else if(s.hasNext("fuelLeft|oppLR|oppFB|numBarrels|barrelLR|barrelFB|wallDist")){
			ch = new Sensor (s.next());
		}
		else if(s.hasNext("add|sub|mul|div")){
			ch = parseOperator(s);
		}
		else {
			fail("Can't read", s);
			ch = null;
		}
		return new Expression(ch);
	}
	
	static Operation parseOperator (Scanner s) {
		String op;
		Expression exL;
		Expression exR;
		op = s.next();
		require("\\(", "Expecting (", s);
		exL = parseExpression(s);
		require(",", "Expecting ,", s);
		exR = parseExpression(s);
		require("\\)", "Expecting )", s);
		return new Operation(op, exL, exR);
	}
	
	
	static RobotProgramNode parseLoop(Scanner s){
		Block block;
		require("loop", "Expecting loop", s);
		block = parseBlock(s);
		return new Loop(block);				
	}
	
	static Block parseBlock(Scanner s){
		List<RobotProgramNode> stmts = new ArrayList<RobotProgramNode>();
		require("\\{", "Expecting {", s);
		do {
			stmts.add(parseStatement(s));
		} while(!s.hasNext("\\}"));		
		require("\\}", "Expecting",s);
		return new Block(stmts);
	}
	
	static RobotProgramNode parseIf(Scanner s) {
		RobotProgramNode block;
		RobotProgramNode elseBlock;
		RobotEvalNode cond;
		require("if", "Expecting if", s);
		require("\\(","Expecting (", s);
		if(s.hasNext("lt|gt|eq")){
			cond = parseConditionI(s);
		}
		else if(s.hasNext("and|or|not")){
			cond = parseConditionB(s);
		}
		else {
			fail("Can't read", s);
			cond = null;
		}
		require("\\)","Expecting )", s);
		block = parseBlock(s);
		if(s.hasNext("else")){
			require("else", "Expecting else", s);
			elseBlock = parseBlock(s);
		}
		else {
		elseBlock = null;
		}
		
		return new IfNode(cond, block, elseBlock);
	}
	
	static RobotEvalNode parseConditionI (Scanner s) {
		String oprt;
		Expression expL;
		Expression expR;
		oprt = s.next();
		require("\\(","Expecting (", s);
		expL = parseExpression(s);
		require(",","Expecting ,", s);
		expR = parseExpression(s);
		require("\\)","Expecting )", s);
		return new ConditionI(oprt, expL, expR);
	}
	
	static RobotEvalNode parseConditionB (Scanner s) {
		String oprt;
		RobotEvalNode condL;
		RobotEvalNode condR = null;
		oprt = s.next();
		require("\\(","Expecting (", s);
		if (s.hasNext("gt|lt|eq")) {		
			condL = parseConditionI(s);
		}
		else if(s.hasNext("and|or|not")){
			condL = parseConditionB(s);
		}
		else {
			fail("Can't read", s);
			condL = null;
		}
		if(s.hasNext(",")){
			require(",","Expecting ,", s);
			if (s.hasNext("gt|lt|eq")) {		
				condR = parseConditionI(s);
			}
			else if(s.hasNext("and|or|not")){
				condR = parseConditionB(s);
			}
			else {
				fail("Can't read", s);
				condR = null;
			}
		}
		else {
			condR = null;
		}
		require("\\)","Expecting )", s);
		return new ConditionB(oprt, condL, condR);
	}
	

	static RobotProgramNode parseWhile(Scanner s) {
		RobotProgramNode block;
		RobotEvalNode cond;
		require("while", "Expecting while", s);
		require("\\(","Expecting (", s);
		if(s.hasNext("lt|gt|eq")){
			cond = parseConditionI(s);
		}
		else if(s.hasNext("and|or|not")){
			cond = parseConditionB(s);
		}
		else {
			fail("Can't read", s);
			cond = null;
		}
		require("\\)","Expecting )", s);
		block = parseBlock(s);
		
		return new While(cond, block);
	}

	/**
	 * Report a failure in the parser.
	 */
	static void fail(String message, Scanner s) {
		String msg = message + "\n   @ ...";
		for (int i = 0; i < 5 && s.hasNext(); i++) {
			msg += " " + s.next();
		}
		throw new ParserFailureException(msg + "...");
	}

	/**
	 * Requires that the next token matches a pattern if it matches, it consumes
	 * and returns the token, if not, it throws an exception with an error
	 * message
	 */
	static String require(String p, String message, Scanner s) {
		if (s.hasNext(p)) {
			return s.next();
		}
		fail(message, s);
		return null;
	}

	static String require(Pattern p, String message, Scanner s) {
		if (s.hasNext(p)) {
			return s.next();
		}
		fail(message, s);
		return null;
	}

	/**
	 * Requires that the next token matches a pattern (which should only match a
	 * number) if it matches, it consumes and returns the token as an integer if
	 * not, it throws an exception with an error message
	 */
	static int requireInt(String p, String message, Scanner s) {
		if (s.hasNext(p) && s.hasNextInt()) {
			return s.nextInt();
		}
		fail(message, s);
		return -1;
	}

	static int requireInt(Pattern p, String message, Scanner s) {
		if (s.hasNext(p) && s.hasNextInt()) {
			return s.nextInt();
		}
		fail(message, s);
		return -1;
	}

	/**
	 * Checks whether the next token in the scanner matches the specified
	 * pattern, if so, consumes the token and return true. Otherwise returns
	 * false without consuming anything.
	 */
	static boolean checkFor(String p, Scanner s) {
		if (s.hasNext(p)) {
			s.next();
			return true;
		} else {
			return false;
		}
	}

	static boolean checkFor(Pattern p, Scanner s) {
		if (s.hasNext(p)) {
			s.next();
			return true;
		} else {
			return false;
		}
	}

}

// You could add the node classes here, as long as they are not declared public (or private)
