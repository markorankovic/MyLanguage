package syntax;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import swift.Scope;

public class VariableValueSyntax extends Syntax {
	
	public static Pattern pattern = Pattern.compile("^\\s*(\\b[a-zA-Z]\\w*)");
	
	public VariableValueSyntax(Scope scope) {
		this.scope = scope;
	}

	public Matcher evaluate(String code) throws Exception {
		Matcher m = pattern.matcher(code);
		if (m.find()) {
			scope.result = scope.getVariable(m.group(1)).getValue();
			return m;
		} else {
			return null;
		}
	}
}
