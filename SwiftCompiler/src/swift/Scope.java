package swift;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import structures.Variable;

public class Scope {

	ArrayList<Variable> vs = new ArrayList<>();
	
	public HashMap<String, Variable> map = new HashMap<>();

	public Object run(String code) throws Exception {
		String[] statements = code.split("( *)\n( *)");
		int i = 0;
		for (String statement : statements) {
			if (statement.matches(SyntaxDefinitions.whileLoopSyntax)) {
				return whileLoop(statement);
			} else if (statement.matches(SyntaxDefinitions.greaterThan)) {
				return greaterThan(statement);
			} else if (statement.matches(SyntaxDefinitions.lessThan)) {
				return lessThan(statement);
			} if (statement.matches(SyntaxDefinitions.additionSyntax)) {
				return addition(statement);
			} else if (statement.matches(SyntaxDefinitions.variableDeclarationSyntax)) {
				Variable v = variableDeclaration(statement);
				map.put(v.getName(), v);
				if (i == statements.length - 1) {
					return Integer.parseInt(v.getValue());
				}
			} else if (statement.matches(SyntaxDefinitions.variableReassignmentSyntax)) {
				Variable v = variableReassignment(statement);
				if (i == statements.length - 1) {
					return Integer.parseInt(v.getValue());
				}
			} else if (statement.matches(SyntaxDefinitions.identifier)) {
				if (i == statements.length - 1) {
					return Integer.parseInt(map.get(statement).getValue());
				}
			}
			i++;
		}
		return Integer.parseInt(code);
	}
	
	private RectCommand rectCommand(String statement) {
		
	}
	
	private int whileLoop(String statement) throws Exception {
		Pattern p = Pattern.compile(SyntaxDefinitions.lessThan);
		Matcher m = p.matcher(statement);
				
		String[] tokens = statement.replace(" ", "").split(SyntaxDefinitions.lessThan);
		String block = tokens[1].replace("{", "").replace("}", "");
		
		m.find();
				
		while (lessThan(m.group(0))) {
			run(block);
		}
		
		return (int) run(m.group(1));
	}

	public Variable getVariable(String name) {
		return map.get(name);
	}
	
	public boolean lessThan(String line) throws Exception {
		if (!line.matches(SyntaxDefinitions.lessThan)) {
			throw new Exception();
		}
		String[] tokens = line.replace(" ", "").split("\\<");
		return (int)run(tokens[0]) < (int)run(tokens[1]);
	}
	
	public boolean greaterThan(String line) throws Exception {
		if (!line.matches(SyntaxDefinitions.greaterThan)) {
			throw new Exception();
		}
		String[] tokens = line.replace(" ", "").split("\\>");
		return (int)run(tokens[0]) > (int)run(tokens[1]);
	}
	
	public int addition(String line) throws Exception {
		if (!line.matches(SyntaxDefinitions.additionSyntax)) {
			throw new Exception();
		}
		ArrayList<Integer> integers = new ArrayList<>();
		for (String token : line.replace(" ", "").split("\\+")) {
			Variable v = getVariable(token);
			if (v != null) {
				integers.add(Integer.parseInt(v.getValue()));
			} else {
				integers.add(Integer.parseInt(token));
			}
		}
		return MathMethods.reduce(integers);
	}
	
	public Variable variableDeclaration(String line) throws Exception {
		if (!line.matches(SyntaxDefinitions.variableDeclarationSyntax)) {
			throw new Exception();
		}
		
		String[] tokens = line.split(" ");
		Variable v = new Variable();
		v.setValue(tokens[3]);
		v.setName(tokens[1]);
		
		return v;
	}

	public Variable variableReassignment(String line) throws Exception {
		if (!line.matches(SyntaxDefinitions.variableReassignmentSyntax)) {
			throw new Exception();
		}
		String[] tokens = line.replace(" ", "").split("=");
		Variable v = map.get(tokens[0]);
		v.setValue(run(tokens[1]) + "");
		return v;
	}
	
}
