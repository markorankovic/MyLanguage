package syntax;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import structures.Variable;
import swift.Scope;

/*
 * Class which evaluates code for the variable declaration.
 */

public class VariableDeclarationSyntax extends Syntax {
	
	public static Pattern pattern = Pattern.compile("^\\s*(\\w+)[ ]+=[ ]+(.*)");
	
	public VariableDeclarationSyntax(Scope scope) {
		this.scope = scope;
	}

	public Matcher evaluate(String code) throws Exception {
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