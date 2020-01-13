package syntax;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import swift.Scope;

/*
 * Class which evaluates code for the while loop.
 */

public class WhileBlockSyntax extends Syntax {
	
	public static Pattern pattern = Pattern.compile("^\\s*while[ ]+(.+)[ ]+" + Syntax.block);
	
	public WhileBlockSyntax(Scope scope) {
		this.scope = scope;
	}

	public Matcher evaluate(String code) throws Exception {
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
