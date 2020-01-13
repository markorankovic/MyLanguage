package syntax;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import swift.Scope;

/*
 * Class which evaluates code for the operator.
 */

public class OperatorSyntax extends Syntax {
	
	public static Pattern pattern = Pattern.compile("^\\s*(.+)[ ]+([\\+\\-\\*\\/\\<\\>])[ ]+(.+)");
	
	public OperatorSyntax(Scope scope) {
		this.scope = scope;
	}

	public Matcher evaluate(String code) throws Exception {
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
