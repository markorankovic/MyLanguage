package swift;

import java.util.ArrayList;
import java.util.HashMap;

import structures.Variable;

public class Scope {

	ArrayList<Variable> vs = new ArrayList<>();
	
	HashMap<String, Variable> map = new HashMap<>();

	public int run(String code) throws Exception {
		String[] statements = code.split("( *)\n( *)");
		int i = 0;
		for (String statement : statements) {
			if (statement.matches(SyntaxDefinitions.variableDeclarationSyntax)) {
				Variable v = variableDeclaration(statement);
				map.put(v.getName(), v);
				if (i == statements.length - 1) {
					return Integer.parseInt(v.getValue());
				}
			} else if (statement.matches(SyntaxDefinitions.variableReassignmentSyntax)) {
				Variable v = variableReassignment(statement);
				if (i == statements.length - 1) {
					System.out.println(v.getValue());
					return Integer.parseInt(v.getValue());
				}
			} else if (statement.matches(SyntaxDefinitions.identifier)) {
				if (i == statements.length - 1) {
					return Integer.parseInt(map.get(statement).getValue());
				}
			}
			i++;
		}
		throw new Exception();
	}
	
	public Variable getVariable(String name) {
		return map.get(name);
	}
	
	public int addition(String line) throws Exception {
		if (!line.matches(SyntaxDefinitions.additionSyntax)) {
			throw new Exception();
		}
		
		ArrayList<Integer> integers = new ArrayList<>();

		for (String token : line.replace(" ", "").split("\\+")) {
			integers.add(Integer.parseInt(token));
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
		v.setValue(tokens[1]);
		return v;
	}
	
}
