package syntax;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import swift.Scope;

/*
 * Class which evaluates code for literals.
 */

public class LiteralValueSyntax extends Syntax {
	
	public static Pattern pattern = Pattern.compile("^\\s*((?:\\d+)|(?:\\\"[^\"]*\\\")|(?:true)|(?:false))");
	
	public LiteralValueSyntax(Scope scope) {
		this.scope = scope;
	}

	public Matcher evaluate(String code) throws Exception {
		Matcher m = pattern.matcher(code);
		if (m.find()) {
			scope.result = m.group(1);
			return m;
		} else {
			return null;
		}
	}
}
