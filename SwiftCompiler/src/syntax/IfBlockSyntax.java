package syntax;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import swift.Scope;

public class IfBlockSyntax extends Syntax {
	
	public static Pattern pattern = Pattern.compile("^\\s*if[ ]+(.+)[ ]+" + Syntax.block + "(?:\\s*else[ ]+" + Syntax.block + ")?");
	
	public IfBlockSyntax(Scope scope) {
		this.scope = scope;
	}

	public Matcher evaluate(String code) throws Exception {
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