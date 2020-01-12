package swift;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	static Pattern pattern = Pattern.compile("^\\s*(\\w+)\\s+=\\s+(.*)");
	
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
	
	static Pattern pattern = Pattern.compile("\\s*(\\b[a-zA-Z]\\w*)");
	
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
	
	static Pattern pattern = Pattern.compile("^\\s*(.+)\\s+\\+\\s+(.+)");
	
	OperatorSyntax(Scope scope) {
		this.scope = scope;
	}

	Matcher evaluate(String code) throws Exception {
		Matcher m = pattern.matcher(code);
		if (m.find()) {
			int lhs = Integer.parseInt(scope.run(m.group(1)).toString());
			int rhs = Integer.parseInt(scope.run(m.group(2)).toString());
			scope.result = lhs + rhs;
			return m;
		} else {
			return null;
		}
	}
}


