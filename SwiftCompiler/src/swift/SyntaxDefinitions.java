package swift;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import primitivedrawing.Commands.CircleCommand;
import primitivedrawing.Commands.Command;
import primitivedrawing.Commands.DrawToCommand;
import primitivedrawing.Commands.PositionCommand;
import primitivedrawing.Commands.RectCommand;
import structures.Variable;

public class SyntaxDefinitions {
	
	public static String start = "^\\s*";
	public static String identifier = "([a-zA-Z]([a-zA-Z0-9])*)";
	public static String type = identifier;
	public static String integer = "(([1-9]([0-9]*))|0)";
	public static String literal = "(" + integer + "|" + identifier + ")";
	
	public static String additionSymbol = "\\+";
	public static String additionSyntax = literal + "( ?)" + additionSymbol + "( ?)" + literal;
	public static String operAnd = "(" + literal + "|" + additionSyntax + ")";
	
	public static String lessThanSymbol = "\\<";
	public static String greaterThanSymbol = "\\>";
	public static String lessThan = literal + "( ?)" + lessThanSymbol + "( ?)" + literal;
	public static String greaterThan = literal + "( ?)" + greaterThanSymbol + "( ?)" + literal;
	public static String comparison = literal + "( ?)" + "(" + lessThan + "|" + greaterThan + ")" + "( ?)" + literal;
	
	public static String identifierAndTypeSyntax = identifier + ":( )?" + type;
	public static String variableDeclarationSyntax = start + "var " + "(" + identifierAndTypeSyntax + "|" + identifier + ")" + "(( )?)=(( )?)" + "(" + literal + "|" + additionSyntax + ")";
	public static String variableReassignmentSyntax = start + identifier + "( ?)" + "=" + "( ?)" + operAnd;
	
	public static String functionParametersSyntax = "\\(" + "(" + identifierAndTypeSyntax + "(" + ", " + identifierAndTypeSyntax + ")*" + ")*" + "\\)";
	public static String functionDeclarationSyntax = "func " + identifier + "(( )?)" + functionParametersSyntax + "(" + "((( )?)->(( )?))" + type + ")?" + "(( )?)" + "\\{" + "(( |\n)*)" + "\\}";
	
	public static String statementSyntax = "(" + variableDeclarationSyntax + "|" + variableReassignmentSyntax + ")";
	public static String whileLoopSyntax = start + "while\\s+(.+)\\s+\\{\\s*(.+)\\s*\\}";
}

abstract class Syntax {
	
	Scope scope;

	abstract Matcher evaluate(String code) throws Exception;
}

class VariableDeclarationSyntax extends Syntax {
	
	static Pattern pattern = Pattern.compile("^\\s*(\\w+)[ ]+=[ ]+(.*)");
	
	VariableDeclarationSyntax(Scope scope) {
		this.scope = scope;
	}

	Matcher evaluate(String code) throws Exception {
		Matcher m = pattern.matcher(code);
		if (m.find()) {
			String varName = m.group(1);
			String valueExpr = m.group(2);
			Object value = scope.run(valueExpr);
			scope.map.put(varName, new Variable(varName, value));
			return m;
		} else {
			return null;
		}
	}
}

class VariableValueSyntax extends Syntax {
	
	static Pattern pattern = Pattern.compile("^\\s*(\\b[a-zA-Z]\\w*)");
	
	VariableValueSyntax(Scope scope) {
		this.scope = scope;
	}

	Matcher evaluate(String code) throws Exception {
		Matcher m = pattern.matcher(code);
		if (m.find()) {
			scope.result = scope.getVariable(m.group(1)).getValue();
			return m;
		} else {
			return null;
		}
	}
}

class LiteralValueSyntax extends Syntax {
	
	static Pattern pattern = Pattern.compile("^\\s*((?:\\d+)|(?:\\\"[^\"]*\\\")|(?:true)|(?:false))");
	
	LiteralValueSyntax(Scope scope) {
		this.scope = scope;
	}

	Matcher evaluate(String code) throws Exception {
		Matcher m = pattern.matcher(code);
		if (m.find()) {
			scope.result = m.group(1);
			return m;
		} else {
			return null;
		}
	}
}

class OperatorSyntax extends Syntax {
	
	static Pattern pattern = Pattern.compile("^\\s*(.+)[ ]+([\\+\\-\\*\\/\\<\\>])[ ]+(.+)");
	
	OperatorSyntax(Scope scope) {
		this.scope = scope;
	}

	Matcher evaluate(String code) throws Exception {
		Matcher m = pattern.matcher(code);
		if (m.find()) {
			int lhs = Integer.parseInt(scope.run(m.group(1)).toString());
			String op = m.group(2);
			int rhs = Integer.parseInt(scope.run(m.group(3)).toString());
			switch (op) {
			case "+": scope.result = lhs + rhs; break;
			case "-": scope.result = lhs - rhs; break;
			case "*": scope.result = lhs * rhs; break;
			case "/": scope.result = lhs / rhs; break;
			case "<": scope.result = lhs < rhs; break;
			case ">": scope.result = lhs > rhs; break;
			default: throw new Exception("Unknown operator: " + op);
			}
			return m;
		} else {
			return null;
		}
	}
}

class WhileBlockSyntax extends Syntax {
	
	static Pattern pattern = Pattern.compile("^\\s*while[ ]+(.+)[ ]+\\{\\s*([^\\}]+)\\s*\\}");
	
	WhileBlockSyntax(Scope scope) {
		this.scope = scope;
	}

	Matcher evaluate(String code) throws Exception {
		Matcher m = pattern.matcher(code);
		if (m.find()) {
			String condition = m.group(1);
			String block = m.group(2);
			int i = 0;
			while (i < 100 && scope.run(condition).toString() == "true") {
				scope.result = scope.run(block);
				i++;
			}
			return m;
		} else {
			return null;
		}
	}
}

class IfBlockSyntax extends Syntax {
	
	static Pattern pattern = Pattern.compile("^\\s*if[ ]+(.+)[ ]+\\{\\s*([^\\}]+)\\s*\\}(?:\\s*else[ ]+\\{\\s*([^\\}]+)\\s*\\})?");
	
	IfBlockSyntax(Scope scope) {
		this.scope = scope;
	}

	Matcher evaluate(String code) throws Exception {
		Matcher m = pattern.matcher(code);
		if (m.find()) {
			String condition = m.group(1);
			String ifBlock = m.group(2);
			String elseBlock = m.group(3);
			if (scope.run(condition).toString() == "true") {
				scope.result = scope.run(ifBlock);
			} else if (elseBlock != null) {
				scope.result = scope.run(elseBlock);				
			}
			return m;
		} else {
			return null;
		}
	}
}


class RectCommandSyntax extends Syntax {
	
	static Pattern pattern = Pattern.compile("^\\s*rect[ ]+(.+)[ ]*,[ ]*(.+)");
	
	RectCommandSyntax(Scope scope) {
		this.scope = scope;
	}

	Matcher evaluate(String code) throws Exception {
		Matcher m = pattern.matcher(code);
		if (m.find()) {
			int width = Integer.parseInt(scope.run(m.group(1)).toString());
			scope.result = null;
			int height = Integer.parseInt(scope.run(m.group(2)).toString());
			scope.result = null;
			Command command = new RectCommand(width, height);
			command.execution();
			return m;
		} else {
			return null;
		}
	}
	
}

class MoveToCommandSyntax extends Syntax {
	
	static Pattern pattern = Pattern.compile("^\\s*move to[ ]+(.+)[ ]*,[ ]*(.+)");
	
	MoveToCommandSyntax(Scope scope) {
		this.scope = scope;
	}

	Matcher evaluate(String code) throws Exception {
		Matcher m = pattern.matcher(code);
		if (m.find()) {
			int x = Integer.parseInt(scope.run(m.group(1)).toString());
			scope.result = null;
			int y = Integer.parseInt(scope.run(m.group(2)).toString());
			scope.result = null;
			Command command = new PositionCommand(x, y);
			command.execution();
			return m;
		} else {
			return null;
		}
	}
	
}

class DrawToCommandSyntax extends Syntax {
	
	static Pattern pattern = Pattern.compile("^\\s*draw to[ ]+(.+)[ ]*,[ ]*(.+)");
	
	DrawToCommandSyntax(Scope scope) {
		this.scope = scope;
	}

	Matcher evaluate(String code) throws Exception {
		Matcher m = pattern.matcher(code);
		if (m.find()) {
			int x = Integer.parseInt(scope.run(m.group(1)).toString());
			scope.result = null;
			int y = Integer.parseInt(scope.run(m.group(2)).toString());
			scope.result = null;
			Command command = new DrawToCommand(x, y);
			command.execution();
			return m;
		} else {
			return null;
		}
	}
	
}


class CircleCommandSyntax extends Syntax {
	
	static Pattern pattern = Pattern.compile("^\\s*circle[ ]+(.+)");
	
	CircleCommandSyntax(Scope scope) {
		this.scope = scope;
	}

	Matcher evaluate(String code) throws Exception {
		Matcher m = pattern.matcher(code);
		if (m.find()) {
			int r = Integer.parseInt(scope.run(m.group(1)).toString());
			scope.result = null;
			Command command = new CircleCommand(r);
			command.execution();
			return m;
		} else {
			return null;
		}
	}
	
}





