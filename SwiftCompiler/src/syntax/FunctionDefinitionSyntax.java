package syntax;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import structures.Variable;
import swift.Scope;

/*
 * Class which evaluates code for the function definition.
 */

public class FunctionDefinitionSyntax extends Syntax {
	
	public static Pattern pattern = Pattern.compile("^\\s*fun[ ]+(\\w+)[ ]+((?:\\w+[ ]+)+)?" + Syntax.block);
	
	public FunctionDefinitionSyntax(Scope scope) {
		this.scope = scope;
	}

	public Matcher evaluate(String code) throws Exception {
		Matcher m = pattern.matcher(code);
		if (m.find()) {
			String name = m.group(1);
			String fun = m.group(0);
			scope.map.put(name, new Variable(name, fun));
			scope.result = null;
			return m;
		} else {
			return null;
		}
	}
	
}
