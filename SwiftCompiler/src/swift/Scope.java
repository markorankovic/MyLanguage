package swift;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import primitivedrawing.Commands.CircleCommand;
import primitivedrawing.Commands.ClearCommand;
import primitivedrawing.Commands.RectCommand;
import structures.Variable;

public class Scope {

	Syntax[] syntax = {
		new RectCommandSyntax(this),
		new WhileBlockSyntax(this),
		new VariableDeclarationSyntax(this),
		new OperatorSyntax(this),
		new LiteralValueSyntax(this),
		new VariableValueSyntax(this),
	};

	ArrayList<Variable> vs = new ArrayList<>();
	
	public HashMap<String, Variable> map = new HashMap<>();
	
	public Object result = null;
	
	public Object run(String code) throws Exception {
		
		String trimmedCode = code.trim();
		if (trimmedCode.isEmpty()) {
			return result;
		}
		
		for (Syntax s : syntax) {
			Matcher m = s.evaluate(trimmedCode);
			if (m != null) {
				String remainingCode = trimmedCode.substring(m.group(0).length());
				return run(remainingCode);
			}
		}
		
		throw new Exception("Could not compile: " + trimmedCode);
	}
	
	public Variable getVariable(String name) {
		return map.get(name);
	}

//	public Object run(String code) throws Exception {
//		String[] statements = code.split("( *)\n( *)");
//		int i = 0;
//		for (String statement : statements) {
//			if (statement.matches(CommandDefinitions.clearCommand)) {
//				ClearCommand c = clearCommand(statement);
//				if (i == statements.length - 1) {
//					return c;
//				}
//
//			} else if (statement.matches(CommandDefinitions.circleCommand)) {
//				CircleCommand c = circleCommand(statement);
//				if (i == statements.length - 1) {
//					return c;
//				}
//
//			} else if (statement.matches(CommandDefinitions.rectCommand)) {
//				RectCommand r = rectCommand(statement);
//				if (i == statements.length - 1) {
//					return r;
//				}
//
//			} else if (statement.matches(SyntaxDefinitions.whileLoopSyntax)) {
//				int w = whileLoop(statement);
//				if (i == statements.length - 1) {
//					return w;
//				}
//
//			} else if (statement.matches(SyntaxDefinitions.greaterThan)) {
//				boolean b = greaterThan(statement);
//				if (i == statements.length - 1) {
//					return b;
//				}
//
//			} else if (statement.matches(SyntaxDefinitions.lessThan)) {
//				boolean b = lessThan(statement);
//				if (i == statements.length - 1) {
//					return b;
//				}
//			} else if (statement.matches(SyntaxDefinitions.additionSyntax)) {
//				int a = addition(statement);
//				if (i == statements.length - 1) {
//					return a;
//				}
//			} else if (statement.matches(SyntaxDefinitions.variableReassignmentSyntax)) {
//				Variable v = variableReassignment(statement);
//				if (i == statements.length - 1) {
//					return Integer.parseInt(v.getValue());
//				}
//			} else if (statement.matches(SyntaxDefinitions.identifier)) {
//				if (i == statements.length - 1) {
//					return Integer.parseInt(map.get(statement).getValue());
//				}
//			}
//			i++;
//		}
//		return Integer.parseInt(code);
//	}
	
//	private RectCommand rectCommand(String statement) throws Exception {
//		RectCommand rectCommand = new RectCommand(Driver.pd.commandFrame.dcp);
//		ArrayList<String> arguments = new ArrayList<>();
//		String[] tokens = (statement.replace(" ", "").split(" "))[0].split(",");
//		String arg1 = run(tokens[0].replace("rect", "")) + "";
//		String arg2 = run(tokens[1].replace(",", "")) + "";
//		arguments.add(arg1);
//		arguments.add(arg2);
//		rectCommand.arguments = arguments;
//		Driver.pd.commandFrame.dcp.runCommand(rectCommand);
//		return rectCommand;
//	}
//	
//	private CircleCommand circleCommand(String statement) throws Exception {
//		CircleCommand circleCommand = new CircleCommand(Driver.pd.commandFrame.dcp);
//		ArrayList<String> arguments = new ArrayList<>();
//		String[] tokens = (statement.split(" "));
//		String arg1 = run(tokens[1]) + "";
//		arguments.add(arg1);
//		circleCommand.arguments = arguments;
//		Driver.pd.commandFrame.dcp.runCommand(circleCommand);
//		return circleCommand;
//	}
//	
//	private ClearCommand clearCommand(String statement) throws Exception {
//		if (!statement.matches(CommandDefinitions.clearCommand)) {
//			throw new Exception();
//		}
//		ClearCommand clearCommand = new ClearCommand(Driver.pd.commandFrame.dcp);
//		Driver.pd.commandFrame.dcp.runCommand(clearCommand);
//		return clearCommand;
//	}
//	
//	private int whileLoop(String statement) throws Exception {
//		Pattern p = Pattern.compile(SyntaxDefinitions.lessThan);
//		Matcher m = p.matcher(statement);
//				
//		String[] tokens = statement.replace(" ", "").split(SyntaxDefinitions.lessThan);
//		String block = tokens[1].replace("{", "").replace("}", "");
//		
//		m.find();
//		
//		while (lessThan(m.group(0))) {
//			run(block);
//		}
//		
//		return (int) run(m.group(1));
//	}
//
//	
//	public boolean lessThan(String line) throws Exception {
//		if (!line.matches(SyntaxDefinitions.lessThan)) {
//			throw new Exception();
//		}
//		String[] tokens = line.replace(" ", "").split("\\<");
//		return (int)run(tokens[0]) < (int)run(tokens[1]);
//	}
//	
//	public boolean greaterThan(String line) throws Exception {
//		if (!line.matches(SyntaxDefinitions.greaterThan)) {
//			throw new Exception();
//		}
//		String[] tokens = line.replace(" ", "").split("\\>");
//		return (int)run(tokens[0]) > (int)run(tokens[1]);
//	}
//	
//	public int addition(String line) throws Exception {
//		if (!line.matches(SyntaxDefinitions.additionSyntax)) {
//			throw new Exception();
//		}
//		ArrayList<Integer> integers = new ArrayList<>();
//		for (String token : line.replace(" ", "").split("\\+")) {
//			Variable v = getVariable(token);
//			if (v != null) {
//				integers.add(Integer.parseInt(v.getValue()));
//			} else {
//				integers.add(Integer.parseInt(token));
//			}
//		}
//		return MathMethods.reduce(integers);
//	}
//	
//	public Variable variableDeclaration(String line) throws Exception {
//		if (!line.matches(SyntaxDefinitions.variableDeclarationSyntax)) {
//			throw new Exception();
//		}
//		Variable v = new Variable();
//		
//		String[] tokens = line.replace("var ", "").replace(" ", "").split("=");
//		v.setValue(run(tokens[1]) + "");
//		v.setName(tokens[0]);
//		map.put(v.getName(), v);
//		
//		return v;
//	}
//
//	public Variable variableReassignment(String line) throws Exception {
//		if (!line.matches(SyntaxDefinitions.variableReassignmentSyntax)) {
//			throw new Exception();
//		}
//		String[] tokens = line.replace(" ", "").split("=");
//		Variable v = map.get(tokens[0]);
//		v.setValue(run(tokens[1]) + "");
//		return v;
//	}
	
}
