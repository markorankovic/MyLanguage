package syntax;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import primitivedrawing.Commands.Command;
import primitivedrawing.Commands.TriangleCommand;
import swift.Scope;

/*
 * Class which evaluates code for the triangle command.
 */

public class TriangleCommandSyntax extends Syntax {
	
	public static Pattern pattern = Pattern.compile("^\\s*triangle[ ]+(.+)[ ]*,[ ]*(.+)");
	
	public TriangleCommandSyntax(Scope scope) {
		this.scope = scope;
	}

	public Matcher evaluate(String code) throws Exception {
		Matcher m = pattern.matcher(code);
		if (m.find()) {
			int width = Integer.parseInt(scope.run(m.group(1)).toString());
			scope.result = null;
			int height = Integer.parseInt(scope.run(m.group(2)).toString());
			scope.result = null;
			Command command = new TriangleCommand(width, height);
			command.execution();
			return m;
		} else {
			return null;
		}
	}
	
}